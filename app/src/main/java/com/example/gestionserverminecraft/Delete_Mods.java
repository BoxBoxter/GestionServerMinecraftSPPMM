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

public class Delete_Mods extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    //HECHO: TODO: MOSTRAR UN TEXT VIEW CON LA INFORMACION DEL MOD

    Button btnDeleteMod;
    BaseDades bd;
    private Spinner borrarSpinnerMod;
    private SimpleAdapter adapter;
    private TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__mods);
        btnDeleteMod = findViewById(R.id.btnDeleteMod);
        borrarSpinnerMod = findViewById(R.id.borrarSpinnerMod);

        mostraSpinner();
    }

    public void mostraSpinner() {
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
        borrarSpinnerMod.setOnItemSelectedListener(this);
        //Implemento el adapter con el contexto, layout, listaFrutas
        adapter = new SimpleAdapter(getApplicationContext(), llista, R.layout.activity_llista_nom_mod, new String[]{"id", "nom"}, new int[]{R.id.id_modNom, R.id.nom_mod});
        //Cargo el spinner con los datos
        borrarSpinnerMod.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        if (v == btnDeleteMod) {
            bd = new BaseDades(this.getApplicationContext());
            bd.obreBaseDades();
            long id = Long.parseLong(t.getText().toString());
            boolean result = bd.esborraMod(id);
            if (result) {
                Toast.makeText(this, "Element esborrat",
                        Toast.LENGTH_SHORT).show();
                mostraSpinner();
            } else {
                Toast.makeText(this, "No s’ha pogut esborrar l’element",
                        Toast.LENGTH_SHORT).show();
            }
            bd.tanca();
        }
        else {
            finish();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.borrarSpinnerMod:
                t = view.findViewById(R.id.id_modNom);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
