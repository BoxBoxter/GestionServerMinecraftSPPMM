package com.example.gestionserverminecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Lista_Crafteos extends AppCompatActivity {

    SimpleAdapter adapter;
    ListView listView;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__crafteos);
        listView = findViewById(R.id.lista_crafteos);
        i = getIntent();
        llistaCrafteos();


    }
    public void llistaCrafteos(){
        BaseDades bd;
        String id = String.valueOf(i.getLongExtra("id_mod", 0));
        bd = new BaseDades(getApplicationContext());
        bd.obreBaseDades();
        Cursor c = bd.obtenirCrafteo(id);
        c.moveToFirst();
        ArrayList<HashMap<String, String>> llista = new ArrayList<HashMap<String, String>>();
        while (!c.isAfterLast()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", c.getString(0));
            map.put("id_modFK", c.getString(1));
            map.put("nom_crafteo", c.getString(2));
            llista.add(map);
            c.moveToNext();
        }

        bd.tanca();
        adapter = new SimpleAdapter(getApplicationContext(), llista, R.layout.activity_llista__crafteos__item, new String[]{"id", "id_modFK", "nom_crafteo"}, new int[]{R.id.id_crafteo, R.id.id_modFk, R.id.nom_crafteo});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), Lista_Crafteo_Detalles.class);
                TextView textId = view.findViewById(R.id.id_crafteo);
                long idCrafteo = Long.parseLong(textId.getText().toString());
                i.putExtra("id_crafteo", idCrafteo);
                startActivity(i, null);
            }
        });
    }

}
