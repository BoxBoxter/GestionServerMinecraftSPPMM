package com.example.gestionserverminecraft;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BaseDades {

    //ATTRIBUTOS DE MODS
    public static final String Mod_ID = "_id_mod";
    public static final String Mod_Nom = "nom_mod";
    public static final String Mod_Version = "version_mod";
    public static final String TAG = "BaseDades";
    public static final String NomBD = "BDGestioServerMinecraft";
    public static final String Mod_NomTaula = "mods";
    public static final int VERSIO = 1;
    public static final String BD_CREATE_MODS = "create table " + Mod_NomTaula + "( "
            + Mod_ID + " integer primary key autoincrement, " + Mod_Nom + " text not null, " + Mod_Version + " text not null);";
    private final Context context;
    private BaseDadesHelp ajuda;
    private SQLiteDatabase bd;

    //ATRIBUTOS DE CRAFTEOS
    public static final String Crafteo_ID = "_id_crafteo";
    public static final String Crafteo_Mod_ID_FK = "_id_mod";
    public static final String Crafteo_Nom = "nom_crafteo";

    public static final String Crafteo_NomTaula = "crafteos";
    public static final String BD_CREATE_CRAFTEO = "create table " + Crafteo_NomTaula + "( "
            + Crafteo_ID + " integer primary key autoincrement, "
            + Crafteo_Nom + " text not null, "
            + Crafteo_Mod_ID_FK + " integer, "
            + "FOREIGN KEY ("+ Crafteo_Mod_ID_FK +") REFERENCES " + Mod_NomTaula + "("+Mod_ID+") ON DELETE CASCADE);";



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

    //INSEREIX MOD
    public long insereixMod(String nom, String version) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(Mod_Nom, nom);
        initialValues.put(Mod_Version, version);
        return bd.insert(Mod_NomTaula ,null, initialValues);
    }

    //INSEREIX CRAFTEO
    public long insereixCrafteo(String nom, String modFK) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(Crafteo_Nom, nom);
        initialValues.put(Crafteo_Mod_ID_FK, modFK);
        return bd.insert(Crafteo_NomTaula ,null, initialValues);
    }

    //ESBORRA MOD
    public boolean esborraMod(long IDFila) {
        return bd.delete(Mod_NomTaula, Mod_ID + " = " + IDFila, null) > 0;
    }

    //ESBORRA CRAFTEO
    public boolean esborraCrafteo(long IDFila) {
        return bd.delete(Crafteo_NomTaula, Crafteo_ID + " = " + IDFila, null) > 0;
    }
/*
    //RETORNA MOD
    public Cursor obtenirMod(long IDFila) throws SQLException {
        Cursor mCursor = bd.query(true, Mod_NomTaula, new String[] {Mod_ID,
                        Mod_Nom,Mod_Version},Mod_ID + " = " + IDFila, null, null, null, null,
                null);
        if(mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }*/
    /*
    //RETORNA CRAFTEO
    public Cursor obtenirCrafteo(long IDFila) throws SQLException {
        Cursor mCursor = bd.query(true, Crafteo_NomTaula, new String[] {Crafteo_ID,
                        Crafteo_Mod_ID_FK,Crafteo_Nom},Crafteo_ID + " = " + IDFila, null, null, null, null,
                null);
        if(mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }*/

    //RETORNA TOTS MODS
    public Cursor obtenirTotsMods() {
        return bd.query(Mod_NomTaula, new String[] {Mod_ID, Mod_Nom, Mod_Version},
                null,null, null, null, null);
    }

    //RETORNA TOTS CRAFTEOS
    public Cursor obtenirTotsCrafteos(String IDFila) {
        return bd.query(Crafteo_NomTaula, new String[] {Crafteo_ID, Crafteo_Mod_ID_FK, Crafteo_Nom},
                Crafteo_Mod_ID_FK + " = " + IDFila,null, null, null, null);
    }

    //MODIFICA CONTACTE
    public boolean actualitzarMod(long IDFila, String nom, String version) {
        ContentValues args = new ContentValues();
        args.put(Mod_Nom, nom);
        args.put(Mod_Version, version);
        return bd.update(Mod_NomTaula, args, Mod_ID + " = " + IDFila, null) > 0;
    }
    //MODIFICA CONTACTE
    public boolean actualitzarCrafteo(long IDFila, String nom, long modFK) {
        ContentValues args = new ContentValues();
        args.put(Crafteo_Nom, nom);
        args.put(Crafteo_Mod_ID_FK, modFK);
        return bd.update(Crafteo_NomTaula, args, Crafteo_ID + " = " + IDFila, null) > 0;
    }

}
