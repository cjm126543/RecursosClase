package com.example.tabernapp.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Direccion")
public class Direccion extends ParseObject {

    // Attributes
    String nombre;
    String calle;
    String cPostal;

    // Constructor
    public Direccion(String nom, String calle, String cp) {
        setNombre(nom);
        setCalle(calle);
        setcPostal(cp);
    }

    // Getters and setters
    public String getNombre() {
        return getString("nombre");
    }

    public void setNombre(String nombre) {
        put("nombre", nombre);
    }

    public String getCalle() {
        return getString("calle");
    }

    public void setCalle(String calle) {
        put("calle", calle);
    }

    public String getcPostal() {
        return getString("cPostal");
    }

    public void setcPostal(String cPostal) {
        put("cPostal", cPostal);
    }

    // Java language methods
    @Override
    public String toString() {
        return this.getNombre() + " " + this.getCalle();
    }
}
