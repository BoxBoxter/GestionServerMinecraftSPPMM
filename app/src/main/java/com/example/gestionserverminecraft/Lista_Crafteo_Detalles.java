package com.example.gestionserverminecraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Lista_Crafteo_Detalles extends AppCompatActivity {

    Intent i;
    TextView descripcio;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__crafteo__detalles);
        descripcio = findViewById(R.id.descripcio);
        imageView = findViewById(R.id.imageView);

        i = getIntent();

        BaseDades bd;
        String id = String.valueOf(i.getLongExtra("id_crafteo", 0));
        bd = new BaseDades(getApplicationContext());
        bd.obreBaseDades();
        Cursor c = bd.obtenirCrafteoDetall(id);
        c.moveToFirst();
        ArrayList<HashMap<String, String>> llista = new ArrayList<HashMap<String, String>>();
        while (!c.isAfterLast()) {
            /*HashMap<String, String> map = new HashMap<String, String>();
            map.put("id_fk", c.getString(0));
            map.put("descripcio", c.getString(1));
            map.put("image", String.valueOf(c.getBlob(2)));
            llista.add(map);*/
            //------------
            descripcio.setText(c.getString(1));
            byte[] byteArray = c.getBlob(2);
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, 100, 100, false));
//
//            Bitmap bmp = BitmapFactory.decodeByteArray(pelicula.getFoto(),0,pelicula.getFoto().length);
//            mImatge.setImageBitmap(Bitmap.createScaledBitmap(bmp, 100, 100, false));
            c.moveToNext();

        }


        bd.tanca();
    }
}
