package com.example.gestionserverminecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Llista_Mods_Item extends AppCompatActivity implements View.OnClickListener {

    private int idMod;
    private LinearLayout linear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista__mods__item);

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Hola",
                Toast.LENGTH_SHORT).show();
        /*idMod = R.id.id_crafteo;
        Intent i = new Intent(this, Lista_Crafteo.class);
        i.putExtra("id_mod", idMod);
        startActivity(i);*/
    }
}
