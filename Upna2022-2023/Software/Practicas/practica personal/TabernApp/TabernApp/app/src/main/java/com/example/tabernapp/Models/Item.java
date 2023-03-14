package com.example.tabernapp.Models;

import android.annotation.SuppressLint;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * Whenever an id attributed is not associated to any machine,
 * its value defaults to -1.
 *
 * For boolean types, value defaults to false
 */
public class Item {

    // Attributes
    private String nombre;
    private Tipo tipo;
    private double precioUd;
    private int stock;
    private int cantPreparando;
    private int idHorno, idArcon, idSeccionAlmacen;
    private boolean preparando, enUso;

    // Constructor
    public Item(String nombre, Tipo tipo, double precioUd, int stock) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precioUd = precioUd;
        this.stock = stock;
        this.cantPreparando = -1;
        this.idHorno = -1;
        this.idArcon = -1;
        this.idSeccionAlmacen = -1;
        this.preparando = false;
        this.enUso = false;
    }

    // Getters and setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public double getPrecioUd() {
        return precioUd;
    }

    public void setPrecioUd(double precioUd) {
        this.precioUd = precioUd;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCantPreparando() {
        return cantPreparando;
    }

    public void setCantPreparando(int cantPreparando) {
        this.cantPreparando = cantPreparando;
    }

    public int getIdHorno() {
        return idHorno;
    }

    public void setIdHorno(int idHorno) {
        this.idHorno = idHorno;
    }

    public int getIdArcon() {
        return idArcon;
    }

    public void setIdArcon(int idArcon) {
        this.idArcon = idArcon;
    }

    public int getIdSeccionAlmacen() {
        return idSeccionAlmacen;
    }

    public void setIdSeccionAlmacen(int idSeccionAlmacen) {
        this.idSeccionAlmacen = idSeccionAlmacen;
    }

    public boolean isPreparando() {
        return preparando;
    }

    public void setPreparando(boolean preparando) {
        this.preparando = preparando;
    }

    public boolean isEnUso() {
        return enUso;
    }

    public void setEnUso(boolean enUso) {
        this.enUso = enUso;
    }

    // Java language methods
    @Override
    public String toString() {
        return this.getNombre();
    }
}
