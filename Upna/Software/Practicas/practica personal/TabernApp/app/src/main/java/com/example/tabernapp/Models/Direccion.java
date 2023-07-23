package com.example.tabernapp.Models;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;

@ParseClassName("Direccion")
public class Direccion extends ParseObject {

    // Constructor
    public Direccion() {
    }

    // Getters and setters
    public String getNombre() {
        try {
            return fetchIfNeeded().getString("nombre");
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
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
