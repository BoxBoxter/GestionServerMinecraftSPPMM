package com.example.gestionserverminecraft;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // DECLARAMOS LAS VARIABLES BUTTON PARA CADA FUNCION DE LA APP
    private Button listadoMods;
    private Button insertMods;
    private Button insertCrafteos;
    private Button updateMods;
    private Button updateCrafteos;
    private Button deleteMods;
    private Button deleteCrafteos;

    private Button cambiarFondo;
    private ConstraintLayout cl;
    private int a = 0;
    private ArrayList<Drawable> background = new ArrayList<Drawable>();

    private Button creditos;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DECLARAMOS QUE BUTTON PERTENCE A QUAL
        listadoMods = findViewById(R.id.listadomodsbutton);
        listadoMods.setOnClickListener(this);

        insertMods = findViewById(R.id.insertmodsbutton);
        insertMods.setOnClickListener(this);
        insertCrafteos = findViewById(R.id.insertcrafteosbutton);
        insertCrafteos.setOnClickListener(this);
        updateMods = findViewById(R.id.updatemodsbutton);
        updateMods.setOnClickListener(this);
        updateCrafteos = findViewById(R.id.updatecrafteobutton);
        updateCrafteos.setOnClickListener(this);
        deleteMods = findViewById(R.id.deletemodsbutton);
        deleteMods.setOnClickListener(this);
        deleteCrafteos = findViewById(R.id.deletecrafteosbutton);
        deleteCrafteos.setOnClickListener(this);

        background.add(getDrawable(R.drawable.background1));
        background.add(getDrawable(R.drawable.background2));
        background.add(getDrawable(R.drawable.background3));
        background.add(getDrawable(R.drawable.background4));
        background.add(getDrawable(R.drawable.background5));

        cambiarFondo = findViewById(R.id.btnCambiarFondo);
        cambiarFondo.setOnClickListener(this);

        cl = findViewById(R.id.activity_main);
        cl.setBackground(background.get(carregar()));

        creditos = findViewById(R.id.btnCreditos);
        creditos.setOnClickListener(this);
    }

    private int carregar() {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        int it = prefs.getInt("Background",0);
        return it;
    }


    @Override
    public void onClick(View v) {
        if (v == listadoMods) {
            i = new Intent(this, Lista_Mods.class);
            startActivity(i, null);
        } else if (v == insertMods) {
            i = new Intent(this, Insert_Mods.class);
            startActivity(i, null);
        } else if (v == insertCrafteos) {
            i = new Intent(this, Insert_Crafteos.class);
            startActivity(i, null);
        } else if (v == updateMods) {
            i = new Intent(this, Update_Mods.class);
            startActivity(i, null);
        } else if (v == updateCrafteos) {
            i = new Intent(this, Update_Crafteos.class);
            startActivity(i, null);
        } else if (v == deleteMods) {
            i = new Intent(this, Delete_Mods.class);
            startActivity(i, null);
        } else if (v == deleteCrafteos) {
            i = new Intent(this, Delete_Crafteos.class);
            startActivity(i, null);
        } else if (v == cambiarFondo) {
            //a = (int) (Math.random() * 5);
            a++;
            if (a == 5) a = 0;
            cl.setBackground(background.get(a));
        } else if (v == creditos) {
            i = new Intent(this, Creditos.class);
            startActivity(i, null);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        guardar();
    }

    private void guardar() {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putInt("Background", a);
        prefsEditor.commit();
    }
}
