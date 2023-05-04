package com.example.tabernapp.Models;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ParseClassName("Pedido")
public class Pedido extends ParseObject {

    // Attributes
    private int totalArticulos;
    private double totalPrecio;
    private Direccion direccionEntrega;

    // Constructor
    public Pedido() {
        put("totalPrecio", 0.0);
    }

    // Getters and setters
    public int getTotalArticulos() {
        return getInt("totalArticulos");
    }

    public void setTotalArticulos(int totalArticulos) {
        put("totalArticulos", totalArticulos);
    }

    public double getTotalPrecio() {
        return getDouble("totalPrecio");
    }

    public void setTotalPrecio(HashMap<Item, Integer> articulos) {
        double total = 0.0;
        for (Map.Entry<Item, Integer> e: articulos.entrySet()) {
            total += e.getKey().getPrecioUd() * e.getValue();
        }
        put("totalPrecio", total);
    }

    public Direccion getDireccionEntrega() {
        return (Direccion) getParseObject("direccionEntrega");
    }

    public void setDireccionEntrega(Direccion direccionEntrega) {
        put("direccionEntrega", direccionEntrega);
    }

    public int getCantidadArticulo(Item anItem) {
        final List<List<ParseObject>> listaLineas = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Lineas");
        query.whereEqualTo("item", anItem);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    // no hay error
                    listaLineas.add(objects);
                } else {
                    // hay error
                }
            }
        });
        List<ParseObject> lineas = new ArrayList<ParseObject>();
        int cantidad = 0;
        for (ParseObject linea: lineas) {
            Linea l = (Linea) linea;
            cantidad += l.getCantidad();
        }
        return cantidad;
    } // TODO conseguir total de repeticiones de item

    /*TODO NO PODEMOS TENER UN HASHMAP, TRABAJAR CON DB*/

    /**
     * Retrieve all items of the basket as a hashmap
     * @return HashMap with pair {item, quantity} for
     * each item in the basket.
     */
    public HashMap<Item, Integer> getAllArticulos() {
        final List<List<ParseObject>> listaLineas = new ArrayList<>();
        HashMap<Item, Integer> cestaArticulos = new HashMap<Item, Integer>();

        // pedir todas las lineas del pedido
        ParseQuery<ParseObject> query = ParseQuery.getQuery("LineasPedido");
        query.whereEqualTo("linea", this);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    // no hay error
                    listaLineas.add(objects);
                } else {
                    // hay error
                }
            }
        });

        // Pedir todos los items de las lineas
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Linea");
        List<ParseObject> lineas = new ArrayList<ParseObject>();
        lineas = listaLineas.get(0);
        for (ParseObject linea: lineas) {
            query2.getInBackground(linea.getObjectId(), (object, e) -> {
                if (e == null) {
                    // no hay error
                    Linea obj = (Linea) object;
                    cestaArticulos.put(obj.getItem(), obj.getCantidad());
                } else {
                    // hay errorS
                }
            });
        }
        return cestaArticulos;
    }

    /**
     * TODO REFACTORIZAR FUNCIONES PROPIAS PARA PODER MANIPULAR CLASES "LineasPedido" y "ArticuloPedido"
     * TODO EN VEZ DE USAR EL HASHMAP.
     */

    // Class methods
    /**
     * Adds new item to basket.
     * @param articulo Item added to our basket
     * @param cantidad Quantity of the added item
     */
    public void addArticulo(Item articulo, int cantidad) {
        //this.cantidadArticulos.put(articulo, cantidad);
        Linea l = new Linea(articulo, cantidad);
        l.addItemToPedido(this, l);
    }

    /**
     * Removes from the basket the indicated Item.
     * @param key Item pretended to be removed from the basket
     * @return Null if there's no coincidence, the Item passed
     * as an argument otherwise.
     */
    public Item removeArticulo(Item key) {
/*        if (!cantidadArticulos.containsKey(key)) {
            return null;
        }
        cantidadArticulos.remove(key);
        return key;*/

        // Temp values used for fetch db's objects
        final Item[] val = {key};
        final Linea[] l = new Linea[1];

        // Query searching for Linea containing the desired Item
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Linea");
        query.whereEqualTo("item", key);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override   //TODO SE PODRIA HACER CON getInBackground? Como conseguimos el ID del obj
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    // No hay error
                    l[0] = (Linea) objects.get(0);
                } else {
                    val[0] = null;
                }
            }
        });

        // Query deleting the fetched Linea in LineasPedido
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("LineasPedido");
        query2.whereEqualTo("linea", l);
        query2.whereEqualTo("pedido", this);
        query2.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    // No hay error
                    objects.get(0).deleteInBackground(new DeleteCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null) val[0] = null;
                        }
                    });
                }
            }
        });

        // Finally, we delete the Linea from Linea as it no longer exists
        l[0].deleteInBackground(new DeleteCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) val[0] = null;
            }
        });

        return val[0];
    }

    /**
     * Clears the hashmap in order to start a new basket.
     */
    public void removeAll() {
        /*cantidadArticulos.clear();*/
        final List<Linea>[] lineas = new List[]{new ArrayList<>()};
        ParseQuery<ParseObject> query = ParseQuery.getQuery("LineasPedido");
        query.whereEqualTo("pedido", this);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    // no hay error
                    lineas[0] = (List<Linea>)(List<?>) objects;
                    for (int i = 0; i < objects.size(); ++i) {
                        objects.get(i).deleteInBackground(new DeleteCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    // no hay error
                                } else {
                                    // hay error
                                }
                            }
                        });
                    }
                } else {
                    // hay error
                }
            }
        });

        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Linea");
        for (Linea linea : lineas[0]) {
            query2.getInBackground(linea.getObjectId(), (object, e) -> {
                if (e == null) {
                    // no hay error
                    object.deleteInBackground(e2 -> {
                        if (e2 != null) {
                            // hay error
                        }
                    });
                } else {
                    // hay error
                }
            });
        }
    }
}
