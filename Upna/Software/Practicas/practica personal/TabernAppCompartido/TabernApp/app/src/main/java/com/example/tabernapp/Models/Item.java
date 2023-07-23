package com.example.tabernapp.Models;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;

/**
 * Whenever an id attributed is not associated to any machine,
 * its value defaults to -1.
 *
 * For boolean types, value defaults to false
 */
@ParseClassName("Item")
public class Item extends ParseObject {

    private String nombre;
    private Tipo tipo;

    // Constructor
    public Item(){}

    public Item(String nombre, Tipo tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
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

    public Tipo getTipo() {
        return Tipo.values()[getInt("tipo")];
    }

    public void setTipo(Tipo tipo) {
        put("tipo", tipo.getValor());
    }

    public double getPrecioUd() {
        try {
            return fetchIfNeeded().getDouble("precioUd");
        } catch (ParseException e) {
            e.printStackTrace();
            return 0.0;
        }
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

    public Tipo getTipoCategoria() { return this.tipo; }

    public String getNombreCategoria() { return this.nombre; }

    // Java language methods
    @Override
    public String toString() {
        return getNombre();
    }
}
