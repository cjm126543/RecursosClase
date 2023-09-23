package com.example.tabernapp.Models;

public enum Tipo {
    // Enumerate values
    PAN(0),
    REPOSTERIA(1),
    LACTEO(2),
    REFRESCO(3),
    SNACK(4),
    CAFE(5),
    EXTRA(6);

    // Attributes
    private final int valor;

    // Constructor
    private Tipo(int valor) {
        this.valor = valor;
    }

    // Getter
    public int getValor() {
        return valor;
    }

}
