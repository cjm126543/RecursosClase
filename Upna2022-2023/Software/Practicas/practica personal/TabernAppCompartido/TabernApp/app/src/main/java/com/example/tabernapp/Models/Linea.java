package com.example.tabernapp.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Linea")
public class Linea extends ParseObject {

    // Constructor
    public Linea(Item aItem, int cantidad) {
        setItem(aItem);
        setCantidad(cantidad);
    }

    // Getters and setters
    public Item getItem() {
        return (Item) getParseObject("item");
    }

    public void setItem(Item aItem) {
        put("item", aItem);
    }

    public int getCantidad() {
        return getInt("cantidad");
    }

    public void setCantidad(int cantidad) {
        put("cantidad", cantidad);
    }

    // Class methods
    public void addItemToPedido(Pedido pedido, Linea linea) {
        new LineasPedido(pedido, linea); //TODO Deberia asociarse a una variable?
    }
}
