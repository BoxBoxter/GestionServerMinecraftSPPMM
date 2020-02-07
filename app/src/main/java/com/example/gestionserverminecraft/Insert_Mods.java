package com.example.gestionserverminecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insert_Mods extends AppCompatActivity implements View.OnClickListener{

    BaseDades bd;
    private EditText editNom;
    private EditText editVersio;
    private Button afegir;
    private Button enrrera;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert__mods);

        editNom = findViewById(R.id.insertModNombre);
        editVersio = findViewById(R.id.insertModVersion);
        afegir = findViewById(R.id.btnInsertMod);
        enrrera = findViewById(R.id.btnVolverInsertMod);

        bd = new BaseDades(this);
        mp = MediaPlayer.create(this, R.raw.introducirsonido);
    }

    @Override
    public void onClick(View v) {
        if (v == afegir)
        {
            mp.start();
            bd = new BaseDades(this);
            bd.obreBaseDades();
            if (bd.insereixMod(editNom.getText().toString(),
                    editVersio.getText().toString()) != -1) {
                Toast.makeText(this, "Afegit correctament",
                        Toast.LENGTH_SHORT).show();
                editNom.setText(" ");
                editVersio.setText(" ");
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
