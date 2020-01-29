package com.example.gestionserverminecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Insert_Crafteos extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    BaseDades bd;
    private Spinner spinnerMod;
    private EditText editNom;
    private Button afegir;
    private Button enrrera;
    private SimpleAdapter adapter;
    private TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert__crafteos);

        spinnerMod = findViewById(R.id.spinner_mods);
        editNom = findViewById(R.id.insertCrafteoNombre);
        afegir = findViewById(R.id.btnInsertCrafteo);
        enrrera = findViewById(R.id.btnVolverInsertCrafteo);

        bd = new BaseDades(this);

        mostraSpinner();
    }
    public void mostraSpinner()
    {
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
            //map.put("versio", c.getString(2));
            llista.add(map);
            c.moveToNext();
        }
        bd.tanca();
        spinnerMod.setOnItemSelectedListener(this);
        //Implemento el adapter con el contexto, layout, listaFrutas
        adapter = new SimpleAdapter(getApplicationContext(), llista, R.layout.activity_llista_nom_mod, new String[]{"id", "nom"}, new int[]{R.id.id_mod, R.id.nom_mod});
        //Cargo el spinner con los datos
        spinnerMod.setAdapter(adapter);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spinner_mods:
                //Almaceno el nombre de la fruta seleccionada
                t = view.findViewById(R.id.id_mod);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    @Override
    public void onClick(View v) {
        if (v == afegir)
        {
            bd = new BaseDades(this);
            bd.obreBaseDades();
            if (bd.insereixCrafteo(editNom.getText().toString(),
                    t.getText().toString()) != -1) {
                Toast.makeText(this, "Afegit correctament",
                        Toast.LENGTH_SHORT).show();
                editNom.setText(" ");
                t.setText(" ");
            } else {
                Toast.makeText(this, "Error a l’afegir",
                        Toast.LENGTH_SHORT).show();
            }
            bd.tanca();
        } if (v == enrrera)
        {
            finish();
        }
    }
}
