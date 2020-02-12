package com.example.gestionserverminecraft;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Lista_Mods extends AppCompatActivity{

    SimpleAdapter adapter;
    ListView listView;
    TextView id_mod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__mods);

        listView = findViewById(R.id.lista_mods);
        id_mod = findViewById(R.id.id_mod);
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
        adapter = new SimpleAdapter(getApplicationContext(), llista, R.layout.activity_llista_mods_item, new String[]{"id", "nom", "versio"}, new int[]{R.id.id_mod, R.id.nom_mod, R.id.versio_mod});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), Lista_Crafteos.class);
                TextView textId = view.findViewById(R.id.id_mod);
                long idMod = Long.parseLong(textId.getText().toString());
                i.putExtra("id_mod", idMod);
                startActivity(i, null);
            }
        });
    }
}