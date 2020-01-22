package com.example.gestionserverminecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // DECLARAMOS LAS VARIABLES BUTTON PARA CADA FUNCION DE LA APP
    private Button listadoMods;
    private Button insertMods;
    private Button insertCrafteos;
    private Button updateMods;
    private Button updateCrafteos;
    private Button deleteMods;
    private Button deleteCrafteos;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DECLARAMOS QUE BUTTON PERTENCE A QUAL
        listadoMods = findViewById(R.id.listadomodsbutton);
        insertMods = findViewById(R.id.insertmodsbutton);
        insertCrafteos = findViewById(R.id.insertcrafteosbutton);
        updateMods = findViewById(R.id.updatemodsbutton);
        updateCrafteos = findViewById(R.id.updatecrafteobutton);
        deleteMods = findViewById(R.id.deletemodsbutton);
        deleteCrafteos = findViewById(R.id.deletecrafteosbutton);
    }

    @Override
    public void onClick(View v) {
        /*if (v == btnActualitzar) {
            long id;
            bd = new DBInterface(this.getApplicationContext());
            bd.obre();
            id = Long.parseLong(editID.getText().toString());
            boolean result = bd.actualitzarContacte(id,
                    editNom.getText().toString(), editEmail.getText().toString());
            if (result)
                Toast.makeText(this, "Element modificat",
                        Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "No s’ha pogut modificar l’element",
                        Toast.LENGTH_SHORT).show();
            bd.tanca();
            finish();
        }*/
    }
}
