package com.example.tabernapp.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("LineasPedido")
public class LineasPedido extends ParseObject {

    // Constructor
    public LineasPedido(Pedido unPedido, Linea unaLinea) {
        setPedido(unPedido);
        setLinea(unaLinea);
    }

    // Getters and Setters
    public Pedido getPedido() {
        return (Pedido) getParseObject("pedido");
    }

    public void setPedido(Pedido aPedido) {
        put("pedido", aPedido);
    }

    public Linea getLinea() {
        return (Linea) getParseObject("linea");
    }

    public void setLinea(Linea aLinea) {
        put("linea", aLinea);
    }
}
