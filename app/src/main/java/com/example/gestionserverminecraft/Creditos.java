package com.example.gestionserverminecraft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

public class Creditos extends AppCompatActivity implements LlistaFragment.VinsListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);

        // Cercam el fragment de llista (present a les dues orientacions)
        LlistaFragment frgLlista=(LlistaFragment) getSupportFragmentManager().findFragmentById(R.id.FrgLlistat);
        frgLlista.setVinsListener(this); // assignam la resposta a través de la interfície (onViSeleccionat)
    }

    @Override
    public void onViSeleccionat(String credit) {
        // Mirar l'orientació
        boolean horitzontal=getResources().getBoolean(R.bool.modeHoritzontal);
        if(horitzontal) {

            // estam en horitzontal
            ((DetallFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.FrgDetall)).mostrarDetall(credit);


            /*
            Toast.makeText(this, "horizontal",
                    Toast.LENGTH_SHORT).show();

             */
        }
        else {

            //notificar(credit);
            // estam en vertical
            Intent i = new Intent(this, DetallActivity.class);
            i.putExtra("nom", credit);
            startActivity(i);

            /*
            Toast.makeText(this, "vertical",
                    Toast.LENGTH_SHORT).show();

             */
        }
    }



/*
    @Override
    public void onViSeleccionat(String vi) {
        // Mirar l'orientació

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            System.out.println("adwqd");
            ((DetallFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.FrgDetall)).mostrarDetall(vi);
        } else {
            Intent i = new Intent(this, DetallActivity.class);
            i.putExtra("nomvi", vi);
            startActivity(i);
        }

    }

 */


}

