package com.example.gestionserverminecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Lista_Crafteos_Detalles extends AppCompatActivity {

    SimpleAdapter adapter;
    ListView listView;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__crafteos__detalles);
        listView = findViewById(R.id.lista_crafteo_detalles);
        i = getIntent();
        llistaCrafteos();


    }

    public void llistaCrafteos() {
        BaseDades bd;
        long id = i.getLongExtra("id_mod", 0);
        bd = new BaseDades(getApplicationContext());
        bd.obreBaseDades();
        Cursor c = bd.obtenirCrafteoDetall(id);
        c.moveToFirst();
        ArrayList<HashMap<String, String>> llista = new ArrayList<HashMap<String, String>>();
        while (!c.isAfterLast()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", c.getString(0));
            map.put("descripcion", c.getString(1));
            map.put("imagen", c.getString(2));
            llista.add(map);
            c.moveToNext();
        }

        bd.tanca();
        adapter = new SimpleAdapter(getApplicationContext(), llista, R.layout.activity_lista__crafteo__detalles__item, new String[]{"id", "descripcion"}, new int[]{R.id.id_crafteoFk, R.id.descripcio});
        listView.setAdapter(adapter);
    }
}
