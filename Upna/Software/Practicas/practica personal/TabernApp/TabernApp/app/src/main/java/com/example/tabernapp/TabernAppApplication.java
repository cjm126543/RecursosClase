package com.example.tabernapp;

import android.app.Application;

import com.example.tabernapp.Models.Direccion;
import com.example.tabernapp.Models.Item;
import com.example.tabernapp.Models.Pedido;
import com.example.tabernapp.Models.Tipo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TabernAppApplication extends Application {

    // Lists and arrays that contains the app
    private List<Item> catalogo = new ArrayList<>();
    private List<Item> categoria = new ArrayList<>();
    private List<Direccion> direcciones = new ArrayList<>();
    private Pedido cesta = new Pedido();
    Tipo[] tipo = {Tipo.PAN, Tipo.REPOSTERIA, Tipo.LACTEO, Tipo.REFRESCO, Tipo.SNACK, Tipo.CAFE, Tipo.EXTRA};

    @Override
    public void onCreate() {
        super.onCreate();
        startCatalogue();
    }

    // Getters
    public List<Item> getCatalogo() {
        return catalogo;
    }
    public List<Item> getCategoria() { return categoria; }
    public List<Direccion> getDirecciones() { return direcciones; }
    public Pedido getCesta() { return cesta; }

    // Class methods
    /**
     * Initializes the catalogue instantiating new values for the model.
     */
    public void startCatalogue() {
        String[] nombres = {"Pan", "Reposteria", "Lacteo", "Refresco", "Snack", "Cafe", "Extra"};
        for (int i = 0; i < nombres.length; ++i) {
            catalogo.add(new Item(nombres[i], tipo[i],0.00, 0));
        }
    }

    /**
     * Initializes the specified category instantiating new values for
     * the model.
     * @param selectedCategory The category pretended to assign values to
     */
    public void startCategory(Tipo selectedCategory) {
        // Clean the actual category
        int pos = selectedCategory.getValor();
        categoria.removeAll(categoria);

        // Start random for stock and ready products
        Random r = new Random();

        String[][] nombreItem = {
                {"Chapata", "Campesina", "Integral", "Hogaza"},
                {"Croissant", "Napolitana", "Galleta", "Alfajor"},
                {"Leche entera", "Leche de avellana", "Leche sin lactosa", "Leche de soja"},
                {"Coca-Cola", "Nestea", "Fanta de limón", "Tónica"},
                {"Patatas", "Doritos", "Pipas", "Cortezas"},
                {"Cafe molido", "Cafe descafeinado", "Granos de cafe", "Nescafe"},
                {"Vinagretas", "Embutidos", "Ensaladas", "Chuches"}
        };

        double[][] precio = {
                {1.00, 1.20, 1.50, 2.00},
                {0.75, 1.25, 1.00, 1.75},
                {1.20, 2.00, 2.20, 2.15},
                {2.50, 2.50, 2.25, 1.75},
                {0.85, 0.90, 1.10, 0.50},
                {1.00, 1.25, 1.10, 0.99},
                {2.10, 5.40, 2.35, 0.60}
        };

        int[][] stock = new int[7][4];
        int[][] preparando = new int[7][4];
        for (int i = 0;  i < 7; ++i) {
            for (int j = 0; j < 4; ++j) {
                stock[i][j] = r.nextInt(20);
                preparando[i][j] = r.nextInt(10);
            }
        }

        for (int i = 0; i < nombreItem[pos].length; ++i) {
            categoria.add(new Item(nombreItem[pos][i], tipo[pos], precio[pos][i], 0));
        }
    }

    /**
     * Adds a new direction to the application list.
     * @param dir Direction to add
     * @return True if direction doesn't exist and it's added, false otherwise
     */
    public boolean addDirection(Direccion dir) {
        if (!direcciones.contains(dir)) {
            direcciones.add(dir);
            return true;
        }
        return false;
    }

    /**
     * Removes a given direction.
     * @param dir Direction to delete
     * @return The desired deleted direction, null if it's not in the collection
     */
    public Direccion removeDirection(Direccion dir) {
        if (!direcciones.contains(dir)) return null;
        direcciones.remove(dir);
        return dir;
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
