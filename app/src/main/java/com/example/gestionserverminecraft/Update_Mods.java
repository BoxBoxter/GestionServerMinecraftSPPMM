package com.example.gestionserverminecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_Mods extends AppCompatActivity implements View.OnClickListener{

    private Button btnUpdateMod;
    private BaseDades bd;
    private EditText editID;
    private EditText editNom;
    private EditText editVersio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__mods);
        btnUpdateMod = findViewById(R.id.btnUpdateMod);
        editID = findViewById(R.id.updateModId);
        editNom = findViewById(R.id.updateModNombre);
        editVersio = findViewById(R.id.updateModVersion);
    }

    @Override
    public void onClick(View v) {
        if (v == btnUpdateMod) {
            long id;
            bd = new BaseDades(this.getApplicationContext());
            bd.obreBaseDades();
            id = Long.parseLong(editID.getText().toString());
            boolean result = bd.actualitzarMod(id,
                    editNom.getText().toString(), editVersio.getText().toString());
            if (result) {
                Toast.makeText(this, "Element modificat",
                        Toast.LENGTH_SHORT).show();
                editVersio.setText("");
                editID.setText("");
                editNom.setText("");
            }
            else
                Toast.makeText(this, "No s’ha pogut modificar l’element",
                        Toast.LENGTH_SHORT).show();
            bd.tanca();
        }
        else
        {
            finish();
        }
    }
}
