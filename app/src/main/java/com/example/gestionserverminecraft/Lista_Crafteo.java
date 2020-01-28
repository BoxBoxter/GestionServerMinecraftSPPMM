package com.example.gestionserverminecraft;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class Lista_Crafteo extends AppCompatActivity {

    SimpleAdapter adapter;
    ListView listView;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__crafteo);

        listView = findViewById(R.id.lista_crafteo);
        i = getIntent();
        llistaCrafteo();
    }
    public void llistaCrafteo() {
        BaseDades bd;
        bd = new BaseDades(getApplicationContext());
        bd.obreBaseDades();
        Cursor c = bd.obtenirTotsCrafteos(i.getStringExtra("id_mod"));
        c.moveToFirst();
        ArrayList<HashMap<String, String>> llista = new ArrayList<HashMap<String, String>>();
        while (!c.isAfterLast()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", c.getString(0));
            map.put("mod_id", c.getString(1));
            map.put("nom", c.getString(2));
            llista.add(map);
            c.moveToNext();
        }

        bd.tanca();
        adapter = new SimpleAdapter(getApplicationContext(), llista, R.layout.activity_llista__crafteo__item, new String[]{"id", "mod_id", "nom"}, new int[]{R.id.id_crafteo, R.id.id_modFK, R.id.nom_crafteo});
        listView.setAdapter(adapter);
    }
}