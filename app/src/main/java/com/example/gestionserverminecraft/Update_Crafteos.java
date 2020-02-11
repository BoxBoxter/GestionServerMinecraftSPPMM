package com.example.gestionserverminecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
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

public class Update_Crafteos extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    //HECHO
    private Button btnUpdateCrafteo;
    private BaseDades bd;
    private EditText editID;
    private EditText editNom;
    ///---------
    private Spinner modificarModSpinner;
    private SimpleAdapter adapter;
    private TextView t;
    //---------
    private Spinner modificarCrafteoSpinner;
    private SimpleAdapter adapter2;
    private TextView a;

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__crafteos);

        btnUpdateCrafteo = findViewById(R.id.btnUpdateCrafteo);
        editID = findViewById(R.id.updateCrafteoNombre);
        editNom = findViewById(R.id.updateCrafteoNombre);
        modificarModSpinner = findViewById(R.id.modificarModSpinner);
        modificarCrafteoSpinner = findViewById(R.id.modificarCrafteoSpinner);
        mp = MediaPlayer.create(this, R.raw.introducirsonido);

        mostraSpinnerMods();
    }

    @Override
    public void onClick(View v) {
        if (v == btnUpdateCrafteo) {

            long id, id_fk;
            bd = new BaseDades(this.getApplicationContext());
            bd.obreBaseDades();
            if (editNom.getText().toString().isEmpty()) {
                Toast.makeText(this, "Error: Elements buits",
                        Toast.LENGTH_SHORT).show();
            } else {
                mp.start();
                id = Long.parseLong(a.getText().toString());
                id_fk = Long.parseLong(t.getText().toString());

                boolean result = bd.actualitzarCrafteo(id, editNom.getText().toString(), id_fk);
                if (result) {
                    Toast.makeText(this, "Element modificat",
                            Toast.LENGTH_SHORT).show();
                    editNom.setText("");
                    mostraSpinnerModsCrafteo();
                } else
                    Toast.makeText(this, "No s’ha pogut modificar l’element",
                            Toast.LENGTH_SHORT).show();
            }
            bd.tanca();
        } else {
            finish();
        }
    }

    public void mostraSpinnerMods() {
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
        modificarModSpinner.setOnItemSelectedListener(this);
        //Implemento el adapter con el contexto, layout, listaFrutas
        adapter = new SimpleAdapter(getApplicationContext(), llista, R.layout.activity_llista_nom_mod, new String[]{"id", "nom"}, new int[]{R.id.id_modNom, R.id.nom_mod});
        //Cargo el spinner con los datos
        modificarModSpinner.setAdapter(adapter);
    }

    public void mostraSpinnerModsCrafteo() {
        BaseDades bd;
        bd = new BaseDades(getApplicationContext());
        bd.obreBaseDades();


        Cursor c = bd.obtenirCrafteo(t.getText().toString());
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
        modificarCrafteoSpinner.setOnItemSelectedListener(this);
        //Implemento el adapter con el contexto, layout, listaFrutas
        adapter2 = new SimpleAdapter(getApplicationContext(), llista2, R.layout.activity_llista_nom_crafteo, new String[]{"id", "nom"}, new int[]{R.id.id_crafteolista, R.id.nom_crafteolista});
        //Cargo el spinner con los datos
        modificarCrafteoSpinner.setAdapter(adapter2);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.modificarModSpinner:
                t = view.findViewById(R.id.id_modNom);
                mostraSpinnerModsCrafteo();

                break;
            case R.id.modificarCrafteoSpinner:
                a = view.findViewById(R.id.id_crafteolista);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
