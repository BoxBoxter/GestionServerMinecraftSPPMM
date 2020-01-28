package com.example.gestionserverminecraft;


import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Lista_Mods extends AppCompatActivity {

    SimpleAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__mods);

        listView = findViewById(R.id.lista_mods);
        llistaMods();
    }
    public void llistaMods() {
        BaseDades bd;
        bd = new BaseDades(getApplicationContext());
        bd.obreBaseDades();
        Cursor c = bd.obtenirTotsMods();
        c.moveToFirst();
        ArrayList<HashMap<String, String>> llista = new ArrayList<HashMap<String, String>>();
        while (!c.isAfterLast()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", c.getString(0));
            map.put("nom", c.getString(1));
            map.put("versio", c.getString(2));
            llista.add(map);
            c.moveToNext();
        }

        bd.tanca();
        adapter = new SimpleAdapter(getApplicationContext(), llista, R.layout.activity_llista__mods__item, new String[]{"id", "nom", "versio"}, new int[]{R.id.id_crafteo, R.id.id_modFK, R.id.nom_crafteo});
        listView.setAdapter(adapter);
    }
}