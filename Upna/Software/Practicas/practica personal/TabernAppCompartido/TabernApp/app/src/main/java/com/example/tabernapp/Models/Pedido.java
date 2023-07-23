package com.example.tabernapp.Models;

import android.util.Log;
import android.widget.Toast;

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

    // Constructor
    public Pedido(){}

    // Getters and setters
    public String getCliente() { return getString("nombreCli"); }

    public void setCliente() { put("nombreCli", "Carlos"); }

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
        this.saveInBackground();
    }

    public Direccion getDireccionEntrega() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Pedido");
        Pedido p = this;
        try {
            p = (Pedido) query.get(this.getObjectId());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (Direccion) p.getParseObject("direccionEntrega");
    }

    public void setDireccionEntrega(Direccion direccionEntrega) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Pedido");
        try {
            Pedido ped = (Pedido) query.get(this.getObjectId());
            ped.put("direccionEntrega", direccionEntrega);
            ped.save();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
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
                    Log.v("Found", "Objects: " + objects.toString() + " was found\n");
                } else {
                    // hay error
                    Log.e("NoFound", "Objects were not found\n");
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
    }

    /**
     * Retrieve all items of the basket as a hashmap
     * @return HashMap with pair {item, quantity} for
     * each item in the basket.
     */
    public HashMap<Item, Integer> getAllArticulos() {
        List<LineasPedido> listaLineasPedido = new ArrayList<>();
        HashMap<Item, Integer> cestaArticulos = new HashMap<Item, Integer>();

        // pedir todas las lineas del pedido
        ParseQuery<ParseObject> query = ParseQuery.getQuery("LineasPedido");
        query.whereEqualTo("pedido", this);
        try {
            listaLineasPedido = (List<LineasPedido>)(List<?>) query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Pedir todos los items de las lineas
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Linea");
        for (LineasPedido lineaPed : listaLineasPedido) {
            try {
                Linea l = (Linea) query2.get(lineaPed.getLinea().getObjectId());
                cestaArticulos.put(l.getItem(), l.getCantidad());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return cestaArticulos;
    }

    // Class methods
    /**
     * Adds new item to basket.
     * @param articulo Item added to our basket
     * @param cantidad Quantity of the added item
     */
    public void addArticulo(Item articulo, int cantidad) {
        Linea l = new Linea();
        l.setItem(articulo);
        l.setCantidad(cantidad);
        l.saveInBackground(e -> {
            if (e == null) {
                // correcto
                Log.v("Saved", "Object saved successfully\n");
            } else {
                // error
                Log.e("NoSaved", "Object could not be saved\n");
            }
        });
        l.addItemToPedido(this, l);
    }

    /**
     * Removes from the basket the indicated Item.
     * @param key Item pretended to be removed from the basket
     * @return Null if there's no coincidence, the Item passed
     * as an argument otherwise.
     */
    public Item removeArticulo(Item key) {
        // Temp values used for fetch db's objects
        final Item[] val = {key};
        final Linea[] l = new Linea[1];

        // Query searching for Linea containing the desired Item
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Linea");
        query.whereEqualTo("item", key);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    // No hay error
                    l[0] = (Linea) objects.get(0);
                    Log.v("Found", "Object: " + l[0].getObjectId() + " found\n");
                } else {
                    val[0] = null;
                    Log.e("NoFound", "Object was not found\n");
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
        List<LineasPedido> lineas = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("LineasPedido");
        query.whereEqualTo("pedido", this);
        try {
            lineas = (List<LineasPedido>)(List<?>) query.find();
            for (LineasPedido lineaP: lineas) {
                lineaP.deleteInBackground();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Linea");
        for (LineasPedido lineaP : lineas) {
            query2.getInBackground(lineaP.getLinea().getObjectId(), (object, e) -> {
                if (e == null) {
                    // no hay error
                    Log.v("Found", "Object: " + object.getObjectId() + " was found\n");
                    object.deleteInBackground(e2 -> {
                        if (e2 != null) {
                            // hay error
                            Log.e("NoDeleted", "Object: " + object.getObjectId() + " could not be removed\n");
                        }
                    });
                } else {
                    // hay error
                    Log.e("NoFound", "Object was not found\n");
                }
            });
        }
        this.put("totalPrecio", 0.0);
        this.saveInBackground();
    }
}
