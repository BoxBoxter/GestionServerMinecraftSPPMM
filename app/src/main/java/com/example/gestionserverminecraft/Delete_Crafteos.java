package com.example.gestionserverminecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete_Crafteos extends AppCompatActivity implements View.OnClickListener {

    Button deletecrafteosbutton;
    BaseDades bd;
    EditText editID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__crafteos);

        deletecrafteosbutton = findViewById(R.id.btnDeleteCrafteo);
        editID = findViewById(R.id.deleteCrafteoId);
    }

    @Override
    public void onClick(View v) {
        if (v == deletecrafteosbutton) {
            bd = new BaseDades(this.getApplicationContext());
            bd.obreBaseDades();
            long id = Long.parseLong(editID.getText().toString());
            boolean result = bd.esborraCrafteo(id);
            if (result) {
                Toast.makeText(this, "Element esborrat",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No s’ha pogut esborrar l’element",
                        Toast.LENGTH_SHORT).show();
            }
            bd.tanca();
        } else {
            finish();
        }

    }
}
