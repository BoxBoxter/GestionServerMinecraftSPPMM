package com.example.gestionserverminecraft;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;
import static com.example.gestionserverminecraft.BaseDades.BD_CREATE_CRAFTEO;
import static com.example.gestionserverminecraft.BaseDades.BD_CREATE_MODS;
import static com.example.gestionserverminecraft.BaseDades.NomBD;
import static com.example.gestionserverminecraft.BaseDades.VERSIO;

public class BaseDadesHelp extends SQLiteOpenHelper {
    BaseDadesHelp(Context con) {
        super(con, NomBD, null, VERSIO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(BD_CREATE_MODS);
            db.execSQL(BD_CREATE_CRAFTEO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int VersioAntiga, int VersioNova) {
        Log.w(TAG, "Actualitzant Base de dades de la versió" + VersioAntiga+
                " a " + VersioNova + ". Destruirà totes les dades");
        db.execSQL("DROP TABLE IF EXISTS " + NomBD);
        onCreate(db); }
}
