package com.example.testListMVCMenuFloat.Modelos;

public class InterestPoint {

    String nombre;
    double latitud;
    double longitud;

    public InterestPoint(){
    }

    public double getLatitud() {
        return  this.latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud=latitud;
    }

    public double getLongitud() {
        return this.longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.getNombre()+" "+this.getLatitud()+" "+this.getLongitud();
    }
}