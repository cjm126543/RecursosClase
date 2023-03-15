package com.example.tabernapp.Models;

import androidx.annotation.NonNull;

public class Direccion {

    // Attributes
    String nombre;
    String calle;
    String cPostal;

    // Constructor
    public Direccion(String nom, String calle, String cp) {
        this.nombre = nom;
        this.calle = calle;
        this.cPostal = cp;
    }

    // Getters and setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getcPostal() {
        return cPostal;
    }

    public void setcPostal(String cPostal) {
        this.cPostal = cPostal;
    }

    // Java language methods
    @Override
    public String toString() {
        return this.getNombre() + " " + this.getCalle();
    }
}
