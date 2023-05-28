package com.example.tabernapp.Models;

import android.util.Log;
import android.widget.Toast;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Linea")
public class Linea extends ParseObject {

    // Constructor
    public Linea(){}

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
        LineasPedido lp = new LineasPedido();
        lp.setLinea(linea);
        lp.setPedido(pedido);
        lp.saveInBackground(e -> {
            if (e == null) {
                // correcto
                Log.v("Saved", "Saved object successfully\n");
            } else {
                // error
                Log.e("NoSaved", "Error trying to save object: " + lp.getObjectId() + "\n");
            }
        });
    }
}
