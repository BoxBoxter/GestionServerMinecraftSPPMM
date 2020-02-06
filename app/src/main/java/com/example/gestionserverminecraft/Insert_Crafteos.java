package com.example.gestionserverminecraft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static android.provider.MediaStore.Images.*;
import static android.provider.MediaStore.Images.Media.*;

public class Insert_Crafteos extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    BaseDades bd;
    private Spinner spinnerMod;
    private EditText editNom;
    private Button afegir;
    private Button enrrera;
    private SimpleAdapter adapter;
    private TextView t;

    //ImageView foto_gallery;
    private final int GALLERY_REQUEST_CODE = 1;
    private byte[] bitmapmap;
    private Bitmap imatge_bitmap;
    private Button imagen;

    //Descripcio
    private EditText descripcio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert__crafteos);

        spinnerMod = findViewById(R.id.spinner_mods);
        editNom = findViewById(R.id.insertCrafteoNombre);
        afegir = findViewById(R.id.btnInsertCrafteo);
        enrrera = findViewById(R.id.btnVolverInsertCrafteo);
        imagen = findViewById(R.id.buttonImagen);
        descripcio = findViewById(R.id.editDescripcio);

        bd = new BaseDades(this);

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
        spinnerMod.setOnItemSelectedListener(this);
        //Implemento el adapter con el contexto, layout, listaFrutas
        adapter = new SimpleAdapter(getApplicationContext(), llista, R.layout.activity_llista_nom_mod, new String[]{"id", "nom"}, new int[]{R.id.id_mod, R.id.nom_mod});
        //Cargo el spinner con los datos
        spinnerMod.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner_mods:
                t = view.findViewById(R.id.id_modNom);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onClick(View v) {
        if (v == afegir) {
            bd = new BaseDades(this);
            bd.obreBaseDades();
            long id = bd.insereixCrafteo(editNom.getText().toString(), t.getText().toString());

            if (id != -1) {
                if (bd.insereixCrafteoDetall(id, descripcio.getText().toString(), bitmapmap) != -1) {
                    Toast.makeText(this, "Afegit correctament",
                            Toast.LENGTH_SHORT).show();
                    editNom.setText(" ");
                    descripcio.setText(" ");
                    t.setText(" ");
                } else {
                    Toast.makeText(this, "Error a l’afegir",
                            Toast.LENGTH_SHORT).show();
                }
            }
            bd.tanca();
        }
        if (v == imagen) {
            recullDeGaleria();
        }
        if (v == enrrera) {
            finish();
        }
    }

    private void recullDeGaleria() {
        Intent gallery = new Intent(Intent.ACTION_PICK, INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, GALLERY_REQUEST_CODE);
        //Create an Intent with action as ACTION_PICK
        //Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        gallery.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        gallery.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        // Launching the Intent
        startActivityForResult(gallery, GALLERY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Result code is RESULT_OK only if the user selects an Image
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY_REQUEST_CODE) {//data.getData return the content URI for the selected Image
                Uri selectedImage = data.getData();
                String[] filePathColumn = {DATA};
                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();
                //Get the column index of MediaStore.Images.Media.DATA
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                //Gets the String value in the column
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                // Transormarem una imatge a bitmap i seguidament a bytes per a guardar-ho a l'objecte i a la BBDD
                imatge_bitmap = BitmapFactory.decodeFile(imgDecodableString);

                ByteArrayOutputStream blob = new ByteArrayOutputStream();
                //imatge_bitmap.compress(Bitmap.CompressFormat.JPEG, 0 /* Ignored for PNGs */, blob);
                bitmapmap = blob.toByteArray();
                //mImatge.setImageBitmap(imatge_bitmap);
            }
        }
    }
}
