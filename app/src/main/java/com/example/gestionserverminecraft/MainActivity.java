package com.example.gestionserverminecraft;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // DECLARAMOS LAS VARIABLES BUTTON PARA CADA FUNCION DE LA APP
    private Button listadoMods;
    private Button insertMods;
    private Button insertCrafteos;
    private Button updateMods;
    private Button updateCrafteos;
    private Button deleteMods;
    private Button deleteCrafteos;

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
        }
    }
}
