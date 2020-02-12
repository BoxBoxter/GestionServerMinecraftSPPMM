package com.example.gestionserverminecraft;

import android.media.MediaPlayer;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class DetallFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // layout de la llista
        return inflater.inflate(R.layout.fragment_detall, container, false);
    }

    public void mostrarDetall(String credits) {
        TextView nombre = getView().findViewById(R.id.nombreTextView);
        TextView edad = getView().findViewById(R.id.edadTextView);
        TextView titulo = getView().findViewById(R.id.tituloFunciones);
        TextView funciones = getView().findViewById(R.id.funcionesTextView);

        ImageView skin = getView().findViewById(R.id.skinImageView);

        if (credits.equals("Fabio")){

            nombre.setText("Fabio Cardoso");
            edad.setText("21 años");
            funciones.setText("Crear Base de Datos, funcionalidad de la aplicación, Selects, Inserts, Updates y Deletes de Mods y Crafteos, añadir imagenes a la Base de Datos, crear los Sppiners...");

            skin.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.skin1, null));
        }
        else if (credits.equals("Pep Jesús")){
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