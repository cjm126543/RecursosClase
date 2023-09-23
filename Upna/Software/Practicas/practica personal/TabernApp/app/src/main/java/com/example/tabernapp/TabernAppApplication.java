package com.example.tabernapp;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.tabernapp.Models.Direccion;
import com.example.tabernapp.Models.Item;
import com.example.tabernapp.Models.Linea;
import com.example.tabernapp.Models.LineasPedido;
import com.example.tabernapp.Models.Pedido;
import com.example.tabernapp.Models.Tipo;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TabernAppApplication extends Application {

    // Lists and arrays that contains the app
    private List<Item> catalogo = new ArrayList<>();
    private List<List<Item>> categoria = new ArrayList<>();
    private List<Direccion> direcciones = new ArrayList<>();
    Pedido cesta;

    @Override
    public void onCreate() {
        super.onCreate();

        // Create all desired entities in the database
        ParseObject.registerSubclass(Direccion.class);
        ParseObject.registerSubclass(Item.class);
        ParseObject.registerSubclass(Pedido.class);
        ParseObject.registerSubclass(Linea.class);
        ParseObject.registerSubclass(LineasPedido.class);


        // Establish connection to database
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build());

        startCatalogue();
        for (int i = 0; i < catalogo.size(); ++i) categoria.add(null);
        startCategory();
        cesta = new Pedido();
        cesta.setCliente();
        cesta.saveInBackground(e -> {
            if (e == null) {
                // guardado
                Log.v("Saved", "Object was saved successfully\n");
            } else {
                // error
                Toast.makeText(this, "ERROR: " + e.getMessage() + "\n", Toast.LENGTH_SHORT).show();
                Log.e("NoSaved", "Object could not be saved\n");
            }
        });
    }

    // Lists and arrays that contains the app
    Tipo[] tipo = {Tipo.PAN, Tipo.REPOSTERIA, Tipo.LACTEO, Tipo.REFRESCO, Tipo.SNACK, Tipo.CAFE, Tipo.EXTRA};

    // Getters
    public List<Item> getCatalogo() {
        return catalogo;
    }
    public List<Item> getCategoria(int tipo) {
        return categoria.get(tipo);
    }
    public List<Direccion> getDirecciones() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Direccion");
        try {
            return (List<Direccion>)(List<?>) query.find();
        } catch (ParseException e) {
            Toast.makeText(this, "ERROR: " + e.getMessage() + "\n", Toast.LENGTH_SHORT).show();
            Log.e("NoFound", e.getMessage() + "\n");
        }
        return null;
    }
    public Pedido getCesta() { return cesta; }

    // Class methods
    /**
     * Initializes the catalogue instantiating new values for the model.
     */
    public void startCatalogue() {
        String[] nombres = {"Pan", "Reposteria", "Lacteo", "Refresco", "Snack", "Cafe", "Extra"};
        for (int i = 0; i < nombres.length; ++i) {
            catalogo.add(new Item(nombres[i], tipo[i]));
        }
    }

    /**
     * Initializes all the categories instantiating new values for
     * the model.
     */
    public void startCategory() {
        for (int i = 0; i < 7; ++i) {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Item");
            query.whereEqualTo("tipo", i);
            int finalI = i;
            query.findInBackground((objects, e) -> {
                if (e == null) {
                    categoria.set(finalI, (List<Item>)(List<?>) objects);
                    Log.v("Found", "Objects: " + objects.toString() + " were found\n");
                } else {
                    // error
                    Toast.makeText(this, "ERROR: " + e.getMessage() + "\n", Toast.LENGTH_SHORT).show();
                    Log.e("NoFound", e.getMessage() + "\n");
                }
            });
        }
    }

    /**
     * Removes a given direction.
     * @param dir Direction to delete
     * @return The desired deleted direction, null if it's not in the collection
     */
    public Direccion removeDirection(Direccion dir) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Direccion");
        boolean exit;
        try {
            Direccion d = (Direccion) query.get(dir.getObjectId());
            d.delete();
            exit = true;
            Log.v("Deleted", "Direccion removed succesfully\n");
        } catch (ParseException e) {
            Toast.makeText(this, "ERROR:" + e.getMessage() + "\n", Toast.LENGTH_SHORT).show();
            Log.e("NoDeleted", e.getMessage() + "\n");
            exit = false;
        }
        if (!exit) return null;
        else return dir;
    }

    /**
     * Adds a new item to the basket with its quantity. Also updates the desired quantity
     * if the passed item already exists.
     * @param articulo Item to be added or updated
     * @param cantidad Quantity of the desired item
     */
    public void updateCesta(Item articulo, int cantidad) {
        cesta.addArticulo(articulo, cantidad);
    }

    /**
     * Deletes an item from the basket if there are no instances of it.
     * @param articulo Item to be deleted
     * @return the deleted item, null if there is no coincidence.
     */
    public Item removeFromCesta(Item articulo) {
        return cesta.removeArticulo(articulo);
    }

    /**
     * Clears the basket.
     */
    public void removeAllFromCesta() {
        cesta.removeAll();
    }
}
