package com.example.gestionserverminecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insert_Crafteos extends AppCompatActivity implements View.OnClickListener{

    BaseDades bd;
    private EditText editModFK;
    private EditText editNom;
    private Button afegir;
    private Button enrrera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert__crafteos);

        editModFK = findViewById(R.id.insertCrafteoIdMod);
        editNom = findViewById(R.id.insertCrafteoNombre);
        afegir = findViewById(R.id.btnInsertCrafteo);
        enrrera = findViewById(R.id.btnVolverInsertCrafteo);

        bd = new BaseDades(this);

    }

    @Override
    public void onClick(View v) {
        if (v == afegir)
        {
            bd = new BaseDades(this);
            bd.obreBaseDades();
            if (bd.insereixCrafteo(editNom.getText().toString(),
                    editModFK.getText().toString()) != -1) {
                Toast.makeText(this, "Afegit correctament",
                        Toast.LENGTH_SHORT).show();
                editNom.setText(" ");
                editModFK.setText(" ");
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
