package com.example.tabernapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tabernapp.Models.Item;
import com.example.tabernapp.Models.Tipo;

import java.util.ArrayList;
import java.util.List;

public class CategoriaActivity extends AppCompatActivity {

    // View components
    TabernAppApplication app;

    // Application data manipulation structures
    List<Item> catalogue = new ArrayList<>();
    List<Item> category = new ArrayList<>();
    Item selectedItem;
    Bundle bundle;
    int pos;
    int cardSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoria_view);

        // Retrieve selected item on catalogue
        app = (TabernAppApplication) getApplicationContext();
        catalogue = app.getCatalogo();
        bundle = getIntent().getExtras();
        pos = bundle.getInt("listPosition");
        selectedItem = catalogue.get(pos);

        displayCategory(selectedItem.getTipoCategoria());

        // Click a card image button
        CardView card1 = (CardView) findViewById(R.id.card1);
        CardView card2 = (CardView) findViewById(R.id.card2);
        CardView card3 = (CardView) findViewById(R.id.card3);
        CardView card4 = (CardView) findViewById(R.id.card4);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardSelected = 0;
                // Instance (if not done it yet) the item in the basket
                int cantidad;
                if (!app.getCesta().getAllArticulos().containsKey(category.get(0))) {
                    cantidad = 0;
                } else {
                    cantidad = app.getCesta().getCantidadArticulo(category.get(0));
                }
                DialogFragment newFragment = new InputCantidadDialog(CategoriaActivity.this);
                Bundle args = new Bundle();
                args.putInt("prevQuant", cantidad);
                args.putInt("tipo", pos);
                newFragment.setArguments(args);
                newFragment.show(getSupportFragmentManager(), "pedir_cantidad");
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardSelected = 1;
                // Instance (if not done it yet) the item in the basket
                int cantidad = 0;
                if (!app.getCesta().getAllArticulos().containsKey(category.get(1))) {
                    cantidad = 0;
                } else {
                    cantidad = app.getCesta().getCantidadArticulo(category.get(1));
                }
                DialogFragment newFragment = new InputCantidadDialog(CategoriaActivity.this);
                Bundle args = new Bundle();
                args.putInt("prevQuant", cantidad);
                args.putInt("tipo", pos);
                newFragment.setArguments(args);
                newFragment.show(getSupportFragmentManager(), "pedir_cantidad");
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardSelected = 2;
                // Instance (if not done it yet) the item in the basket
                int cantidad;
                if (!app.getCesta().getAllArticulos().containsKey(category.get(2))) {
                    cantidad = 0;
                } else {
                    cantidad = app.getCesta().getCantidadArticulo(category.get(2));
                }
                DialogFragment newFragment = new InputCantidadDialog(CategoriaActivity.this);
                Bundle args = new Bundle();
                args.putInt("prevQuant", cantidad);
                args.putInt("tipo", pos);
                newFragment.setArguments(args);
                newFragment.show(getSupportFragmentManager(), "pedir_cantidad");
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardSelected = 3;
                // Instance (if not done it yet) the item in the basket
                int cantidad;
                if (!app.getCesta().getAllArticulos().containsKey(category.get(3))) {
                    cantidad = 0;
                } else {
                    cantidad = app.getCesta().getCantidadArticulo(category.get(3));
                }
                DialogFragment newFragment = new InputCantidadDialog(CategoriaActivity.this);
                Bundle args = new Bundle();
                args.putInt("prevQuant", cantidad);
                args.putInt("tipo", pos);
                newFragment.setArguments(args);
                newFragment.show(getSupportFragmentManager(), "pedir_cantidad");
            }
        });
    }

    // Custom activity methods
    /**
     * Displays the specified category in the layout.
     * @param categoryType category to implement
     */
    private void displayCategory(Tipo categoryType) {
        TextView card1Title, card2Title, card3Title, card4Title;
        TextView card1Stock, card2Stock, card3Stock, card4Stock;
        TextView card1Ready, card2Ready, card3Ready, card4Ready;
        TextView title;
        ImageView header, logo;
        ImageButton imgBtn1, imgBtn2, imgBtn3, imgBtn4;

        // Retrieve all category values
        category = app.getCategoria(categoryType.getValor());

        // Logo image manipulation
        logo = (ImageView) findViewById(R.id.imgvCategoria);
        int lg = getResources().getIdentifier("@drawable/" + categoryType.name().toLowerCase()
                                                + "_logo", null, getPackageName());
        Drawable lgImg = getResources().getDrawable(lg);
        logo.setImageDrawable(lgImg);

        // Category text manipulation
        title = (TextView) findViewById(R.id.textCategoria);
        title.setText(categoryType.name().toLowerCase());

        // Product name manipulation
        card1Title = (TextView) findViewById(R.id.textElem1);
        card2Title = (TextView) findViewById(R.id.textElem2);
        card3Title = (TextView) findViewById(R.id.textElem3);
        card4Title = (TextView) findViewById(R.id.textElem4);

        card1Title.setText(category.get(0).toString());
        card2Title.setText(category.get(1).toString());
        card3Title.setText(category.get(2).toString());
        card4Title.setText(category.get(3).toString());

        // Product stock manipulation
        card1Stock = (TextView) findViewById(R.id.textInv1);
        card2Stock = (TextView) findViewById(R.id.textInv2);
        card3Stock = (TextView) findViewById(R.id.textInv3);
        card4Stock = (TextView) findViewById(R.id.textInv4);

        card1Stock.setText("Inventario: " + Integer.toString(category.get(0).getStock()));
        card2Stock.setText("Inventario: " + Integer.toString(category.get(1).getStock()));
        card3Stock.setText("Inventario: " + Integer.toString(category.get(2).getStock()));
        card4Stock.setText("Inventario: " + Integer.toString(category.get(3).getStock()));

        // Product number of items ready manipulation
        card1Ready = (TextView) findViewById(R.id.textPrep1);
        card2Ready = (TextView) findViewById(R.id.textPrep2);
        card3Ready = (TextView) findViewById(R.id.textPrep3);
        card4Ready = (TextView) findViewById(R.id.textPrep4);

        int val1 = category.get(0).getCantPreparando();
        int val2 = category.get(1).getCantPreparando();
        int val3 = category.get(2).getCantPreparando();
        int val4 = category.get(3).getCantPreparando();

        if (val1 != -1) card1Ready.setText("Preparacion: " + Integer.toString(val1));
        if (val2 != -1) card2Ready.setText("Preparacion: " + Integer.toString(val2));
        if (val3 != -1) card3Ready.setText("Preparacion: " + Integer.toString(val3));
        if (val4 != -1) card4Ready.setText("Preparacion: " + Integer.toString(val4));

        // Header image manipulation
        header = (ImageView) findViewById(R.id.imgvCabecera);
        int hdr = getResources().getIdentifier("@drawable/" +
                categoryType.name().toLowerCase() + "_cabecera", null, getPackageName());
        Drawable hdrImg = getResources().getDrawable(hdr);
        header.setImageDrawable(hdrImg);

        // Cards image manipulation
        imgBtn1 = (ImageButton) findViewById(R.id.imgb1);
        imgBtn2 = (ImageButton) findViewById(R.id.imgb2);
        imgBtn3 = (ImageButton) findViewById(R.id.imgb3);
        imgBtn4 = (ImageButton) findViewById(R.id.imgb4);

        int crd1 = getResources().getIdentifier("@drawable/" +
                categoryType.name().toLowerCase() + "_card1", null, getPackageName());
        int crd2 = getResources().getIdentifier("@drawable/" +
                categoryType.name().toLowerCase() + "_card2", null, getPackageName());
        int crd3 = getResources().getIdentifier("@drawable/" +
                categoryType.name().toLowerCase() + "_card3", null, getPackageName());
        int crd4 = getResources().getIdentifier("@drawable/" +
                categoryType.name().toLowerCase() + "_card4", null, getPackageName());

        Drawable img1 = getResources().getDrawable(crd1);
        Drawable img2 = getResources().getDrawable(crd2);
        Drawable img3 = getResources().getDrawable(crd3);
        Drawable img4 = getResources().getDrawable(crd4);

        imgBtn1.setImageDrawable(img1);
        imgBtn2.setImageDrawable(img2);
        imgBtn3.setImageDrawable(img3);
        imgBtn4.setImageDrawable(img4);
    }

    /**
     * Returns the position of the selected card in order to update the baskets quantity
     * in the dialog.
     * @return integer representing the position mentioned
     */
    public int getCard() {
        return cardSelected;
    }
}