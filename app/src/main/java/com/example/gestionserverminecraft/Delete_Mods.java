package com.example.gestionserverminecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete_Mods extends AppCompatActivity implements View.OnClickListener {
    Button btnDeleteMod;
    BaseDades bd;
    EditText editID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__mods);
        btnDeleteMod = findViewById(R.id.btnDeleteMod);
        editID = findViewById(R.id.deleteModId);
    }

    @Override
    public void onClick(View v) {
        if (v == btnDeleteMod) {
            bd = new BaseDades(this.getApplicationContext());
            bd.obreBaseDades();
            long id = Long.parseLong(editID.getText().toString());
            boolean result = bd.esborraMod(id);
            if (result) {
                Toast.makeText(this, "Element esborrat",
                        Toast.LENGTH_SHORT).show();
                        editID.setText("");
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
}
