package com.example.gestionserverminecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Delete_Crafteos extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button deletecrafteosbutton;
    BaseDades bd;
    private SimpleAdapter adapter;
    private Spinner borrarSpinnerCrafteo;
    private TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__crafteos);

        deletecrafteosbutton = findViewById(R.id.btnDeleteCrafteo);
        borrarSpinnerCrafteo = findViewById(R.id.borrarSpinnerCrafteo);

        mostraSpinnerCrafteo();
    }

    @Override
    public void onClick(View v) {
        if (v == deletecrafteosbutton) {
            bd = new BaseDades(this.getApplicationContext());
            bd.obreBaseDades();
            long id = Long.parseLong(t.getText().toString());
            boolean result = bd.esborraCrafteo(id);
            if (result) {
                Toast.makeText(this, "Element esborrat",
                        Toast.LENGTH_SHORT).show();
                mostraSpinnerCrafteo();
            } else {
                Toast.makeText(this, "No s’ha pogut esborrar l’element",
                        Toast.LENGTH_SHORT).show();
            }
            bd.tanca();
        } else {
            finish();
        }
    }
    public void mostraSpinnerCrafteo() {
        BaseDades bd;
        bd = new BaseDades(getApplicationContext());
        bd.obreBaseDades();

        //String id = String.valueOf(t.getText());

        Cursor c = bd.obtenirTotsCrafteos();
        c.moveToFirst();
        ArrayList<HashMap<String, String>> llista2 = new ArrayList<HashMap<String, String>>();
        while (!c.isAfterLast()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", c.getString(0));
            map.put("nom", c.getString(2));
            //map.put("versio", c.getString(2));
            llista2.add(map);
            c.moveToNext();
        }
        bd.tanca();
        borrarSpinnerCrafteo.setOnItemSelectedListener(this);
        //Implemento el adapter con el contexto, layout, listaFrutas
        adapter = new SimpleAdapter(getApplicationContext(), llista2, R.layout.activity_llista_nom_crafteo, new String[]{"id", "nom"}, new int[]{R.id.id_crafteolista, R.id.nom_crafteolista});
        //Cargo el spinner con los datos
        borrarSpinnerCrafteo.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.borrarSpinnerCrafteo:
                t = view.findViewById(R.id.id_crafteolista);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
