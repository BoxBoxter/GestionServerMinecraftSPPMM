package com.example.gestionserverminecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_Crafteos extends AppCompatActivity implements View.OnClickListener{

    private Button btnUpdateCrafteo;
    private BaseDades bd;
    private EditText editID;
    private EditText editNom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__crafteos);

        btnUpdateCrafteo = findViewById(R.id.btnUpdateCrafteo);
        editID = findViewById(R.id.updateCrafteoNombre);
        editNom = findViewById(R.id.updateCrafteoNombre);
    }

    @Override
    public void onClick(View v) {
        if (v == btnUpdateCrafteo) {
            long id, id_fk;
            bd = new BaseDades(this.getApplicationContext());
            bd.obreBaseDades();
            id = Long.parseLong(editID.getText().toString());
            id_fk = Long.parseLong(String.valueOf(1));
            boolean result = bd.actualitzarCrafteo(id,
                    editNom.getText().toString(), id_fk);
            if (result)
                Toast.makeText(this, "Element modificat",
                        Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "No s’ha pogut modificar l’element",
                        Toast.LENGTH_SHORT).show();
            bd.tanca();
        } else {
            finish();
        }
    }
}
