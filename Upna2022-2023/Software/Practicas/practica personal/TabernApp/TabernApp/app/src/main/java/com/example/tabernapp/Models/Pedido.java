package com.example.tabernapp.Models;

import java.util.HashMap;
import java.util.Map;

public class Pedido {

    // Attributes
    private int totalArticulos;
    private double totalPrecio;
    private HashMap<Item, Integer> cantidadArticulos;
    private Direccion direccionEntrega;

    // Constructor
    public Pedido() {
        this.cantidadArticulos = new HashMap<>();
        this.totalPrecio = 0.0;
    }

    // Getters and setters
    public int getTotalArticulos() {
        return totalArticulos;
    }

    public void setTotalArticulos(int totalArticulos) {
        this.totalArticulos = totalArticulos;
    }

    public double getTotalPrecio() {
        return totalPrecio;
    }

    public void setTotalPrecio(HashMap<Item, Integer> articulos) {
        double total = 0.0;
        for (Map.Entry<Item, Integer> e: articulos.entrySet()) {
            total += e.getKey().getPrecioUd() * e.getValue();
        }
        this.totalPrecio = total;
    }

    public int getCantidadArticulo(Item key) {
        return cantidadArticulos.get(key);
    }

    public HashMap<Item, Integer> getAllArticulos() {
        return cantidadArticulos;
    }

    public void setCantidadArticulo(Item key, int cantidad) {
        cantidadArticulos.put(key, cantidad);
    }

    public Direccion getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(Direccion direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    // Class methods
    /**
     * Adds new item to basket.
     * @param articulo Item added to our basket
     * @param cantidad Quantity of the added item
     */
    public void addArticulo(Item articulo, int cantidad) {
        this.cantidadArticulos.put(articulo, cantidad);
    }

    /**
     * Removes from the basket the indicated Item.
     * @param key Item pretended to be removed from the basket
     * @return Null if there's no coincidence, the Item passed
     * as an argument otherwise.
     */
    public Item removeArticulo(Item key) {
        if (!cantidadArticulos.containsKey(key)) {
            return null;
        }
        cantidadArticulos.remove(key);
        return key;
    }

}
