package com.example.gestionserverminecraft;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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
        insertMods = findViewById(R.id.insertmodsbutton);
        insertCrafteos = findViewById(R.id.insertcrafteosbutton);
        updateMods = findViewById(R.id.updatemodsbutton);
        updateCrafteos = findViewById(R.id.updatecrafteobutton);
        deleteMods = findViewById(R.id.deletemodsbutton);
        deleteCrafteos = findViewById(R.id.deletecrafteosbutton);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.listadomodsbutton:
                i = new Intent(this, Lista_Mods.class);
                startActivity(i, null);
                break;
            case R.id.insertmodsbutton:
                i = new Intent(this, Insert_Mods.class);
                startActivity(i, null);
                break;
            case R.id.insertcrafteosbutton:
                i = new Intent(this, Insert_Crafteos.class);
                startActivity(i, null);
                break;
            case R.id.updatemodsbutton:
                i = new Intent(this, Update_Mods.class);
                startActivity(i, null);
                break;
            case R.id.updatecrafteobutton:
                i = new Intent(this, Update_Crafteos.class);
                startActivity(i, null);
                break;
            case R.id.deletemodsbutton:
                i = new Intent(this, Delete_Mods.class);
                startActivity(i, null);
                break;
            case R.id.deletecrafteosbutton:
                i = new Intent(this, Delete_Crafteos.class);
                startActivity(i, null);
                break;

        }

    }
}
