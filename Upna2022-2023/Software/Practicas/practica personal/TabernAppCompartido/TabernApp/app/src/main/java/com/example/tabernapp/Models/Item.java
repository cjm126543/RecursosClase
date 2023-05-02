package com.example.tabernapp.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Whenever an id attributed is not associated to any machine,
 * its value defaults to -1.
 *
 * For boolean types, value defaults to false
 */
@ParseClassName("Item")
public class Item extends ParseObject {

    // Constructor
    public Item(String nombre, Tipo tipo, double precioUd, int stock) {
        setNombre(nombre);
        setTipo(tipo);
        setPrecioUd(precioUd);
        setStock(stock);
        setCantPreparando(-1);
        setIdHorno(-1);
        setIdArcon(-1);
        setIdSeccionAlmacen(-1);
        setPreparando(false);
        setEnUso(false);
    }

    // Getters and setters
    public String getNombre() {
        return getString("nombre");
    }

    public void setNombre(String nombre) {
        put("nombre", nombre);
    }

    //TODO Checkear como meter un enum en db
    public Tipo getTipo() {
        return Tipo.values()[getInt("tipo")];
    }

    public void setTipo(Tipo tipo) {
        put("tipo", tipo.getValor());
    }

    public double getPrecioUd() {
        return getDouble("precioUd");
    }

    public void setPrecioUd(double precioUd) {
        put("precioUd", precioUd);
    }

    public int getStock() {
        return getInt("stock");
    }

    public void setStock(int stock) {
        put("stock", stock);
    }

    public int getCantPreparando() {
        return getInt("cantPreparando");
    }

    public void setCantPreparando(int cantPreparando) {
        put("cantPreparando", cantPreparando);
    }

    public int getIdHorno() {
        return getInt("idHorno");
    }

    public void setIdHorno(int idHorno) {
        put("idHorno", idHorno);
    }

    public int getIdArcon() {
        return getInt("idArcon");
    }

    public void setIdArcon(int idArcon) {
        put("idArcon", idArcon);
    }

    public int getIdSeccionAlmacen() {
        return getInt("idSeccionAlmacen");
    }

    public void setIdSeccionAlmacen(int idSeccionAlmacen) {
        put("idSeccionAlmacen", idSeccionAlmacen);
    }

    public boolean isPreparando() {
        return getBoolean("preparando");
    }

    public void setPreparando(boolean preparando) {
        put("preparando", preparando);
    }

    public boolean isEnUso() {
        return getBoolean("enUso");
    }

    public void setEnUso(boolean enUso) {
        put("enUso", enUso);
    }

    // Java language methods
    @Override
    public String toString() {
        return this.getNombre();
    }
}
