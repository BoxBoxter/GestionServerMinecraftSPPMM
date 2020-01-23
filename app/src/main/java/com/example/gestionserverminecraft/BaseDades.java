package com.example.gestionserverminecraft;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BaseDades {

    //ATTRIBUTOS DE MODS
    public static final String Mod_ID = "_id_mod";
    public static final String Mod_Nom = "nom_mod";
    public static final String Mod_Version = "version_mod";

    public static final String Mod_Tag = "BaseDades";

    public static final String Mod_NomBD = "BDMods";
    public static final String Mod_NomTaula = "mods";

    public static final int VERSIO_MOD = 1;

    public static final String BD_CREATE_MODS = "create table " + Mod_NomTaula + "( "
            + Mod_ID + " integer primary key autoincrement, " + Mod_Nom + " text not null, " + Mod_Version + " text not null);";
    private final Context context;
    private BaseDadesHelp ajuda;
    private SQLiteDatabase bd;

    //ATRIBUTOS DE CRAFTEOS
    public static final String Crafteo_ID = "_id_crafteo";
    public static final String Mod_ID_FK = "_id_mod";
    public static final String Crafteo_Nom = "nom_crafteo";

    public static final String Crafteo_NomBD = "BDMods";
    public static final String Crafteo_NomTaula = "crafteos";
    public static final int VERSIO_CRAFTEO = 1;
    public static final String BD_CREATE_CRAFTEO = "create table " + Crafteo_NomTaula + "( "
            + Crafteo_ID + " integer primary key autoincrement, " + Mod_ID_FK + " integer,"
            + " FOREIGN KEY (" + Mod_ID_FK + ") REFERENCES " + Mod_NomTaula + "(" + Mod_ID + ") " + Crafteo_Nom + " text not null);";


    public BaseDades(Context con) {
        this.context = con;
        ajuda = new BaseDadesHelp(context);
    }

    //OBRIR BASE DE DADES
    public BaseDades obreBaseDades() throws SQLException {
        bd = ajuda.getWritableDatabase();
        return this;
    }

    //TANCA BASE DE DADES
    public void tanca() {
        ajuda.close();
    }

    //Insereix un insereixMod
    public long insereixMod(String nom, String version) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(Mod_Nom, nom);
        initialValues.put(Mod_Version, version);
        return bd.insert(BD_TAULA ,null, initialValues);
    }
}
