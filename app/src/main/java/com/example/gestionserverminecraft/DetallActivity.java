package com.example.gestionserverminecraft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

public class DetallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detall);

        TextView nombre = findViewById(R.id.nombreTextView);
        TextView edad = findViewById(R.id.edadTextView);
        TextView titulo = findViewById(R.id.tituloFunciones);
        TextView funciones = findViewById(R.id.funcionesTextView);

        ImageView skin = findViewById(R.id.skinImageView);

        Bundle extras = getIntent().getExtras();
        if (extras.getString("nom").equals("Fabio")){

            nombre.setText("Fabio Cardoso");
            edad.setText("21 años");
            funciones.setText("Crear Base de Datos, funcionalidad de la aplicación, Selects, Inserts, Updates y Deletes de Mods y Crafteos, añadir imagenes a la Base de Datos, crear los Sppiners...");

            skin.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.skin1, null));
        }
        else if (extras.getString("nom").equals("Pep Jesús")){
            nombre.setText("Pep Jesús Maroto");
            edad.setText("20 años");
            funciones.setText("Crear y organizar los Layouts, cambiar icono de la aplicación, quitar su título, añadir pantalla de carga, utilizar Shared Preferences, crear actividad con Fragment...");

            skin.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.skin2, null));
        }
        else {
            nombre.setText("Desenvolupament d'Aplicacions Multiplataforma");
            edad.setText("SPPMM - S2P");
            titulo.setText("Jaume Camps");
            funciones.setText("CIFP Pau Casesnoves\nCarrer Joan Miró, 22, 07300 Inca, Illes Balears");

            skin.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.android, null));
        }

    }

}
