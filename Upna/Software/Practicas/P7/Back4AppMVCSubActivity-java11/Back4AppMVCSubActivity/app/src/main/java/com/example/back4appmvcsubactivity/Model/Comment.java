package com.example.back4appmvcsubactivity.Model;

import androidx.annotation.NonNull;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Comment")
public class Comment extends ParseObject {

    public Comment() { }

    public String getTexto() { return getString("texto"); }
    public void setTexto(String texto) { put("texto", texto); }
    public InterestPoint getPunto() { return (InterestPoint) getParseObject("interestPoint"); }
    public void setPunto(InterestPoint aInterestPoint) { put("interestPoint", aInterestPoint); }

    @Override
    public String toString() {
        return this.getTexto();
    }
}
