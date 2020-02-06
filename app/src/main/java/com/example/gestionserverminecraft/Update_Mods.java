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

public class Update_Mods extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button btnUpdateMod;
    private Button btnVolverUpdateMod;
    private Spinner spinnerModUpdate;
    private SimpleAdapter adapter;
    private TextView t;
    private BaseDades bd;
    private EditText editNom;
    private EditText editVersio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__mods);
        btnUpdateMod = findViewById(R.id.btnUpdateMod);
        editNom = findViewById(R.id.updateModNombre);
        editVersio = findViewById(R.id.updateModVersion);
        spinnerModUpdate = findViewById(R.id.spinner_actualizarmods);
        btnVolverUpdateMod = findViewById(R.id.btnVolverUpdateMod);

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
        spinnerModUpdate.setOnItemSelectedListener(this);
        //Implemento el adapter con el contexto, layout, listaFrutas
        adapter = new SimpleAdapter(getApplicationContext(), llista, R.layout.activity_llista_nom_mod, new String[]{"id", "nom"}, new int[]{R.id.id_modNom, R.id.nom_mod});
        //Cargo el spinner con los datos
        spinnerModUpdate.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        if (v == btnUpdateMod) {
            //long id;
            bd = new BaseDades(this.getApplicationContext());
            bd.obreBaseDades();
            //id = Long.parseLong(t.getText().toString());
            boolean result = bd.actualitzarMod(t.getText().toString(),
                    editNom.getText().toString(), editVersio.getText().toString());
            if (result) {
                Toast.makeText(this, "Element modificat",
                        Toast.LENGTH_SHORT).show();
                editVersio.setText("");
                editNom.setText("");
                mostraSpinner();
            } else {
                Toast.makeText(this, "No s’ha pogut modificar l’element",
                        Toast.LENGTH_SHORT).show();
            }
            bd.tanca();
        }
        if (v == btnVolverUpdateMod) {
            finish();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner_actualizarmods:

                t = view.findViewById(R.id.id_modNom);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
