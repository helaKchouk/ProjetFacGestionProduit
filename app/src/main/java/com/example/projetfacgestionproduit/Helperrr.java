package com.example.projetfacgestionproduit;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helperrr extends SQLiteOpenHelper {

    public static final String DB="computer.dbbb";
    public static final int VER=4;
    String query;
    public Helperrr(@Nullable Context context) {
        super(context, DB, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase dbbb) {
        query="create table ModuleCapteurs(id integer primary key,category text)";
        dbbb.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbbb, int oldVersion, int newVersion) {
        query="drop table if exists ModuleCapteurs";
        dbbb.execSQL(query);
        onCreate(dbbb);
    }
    public void deleteAllProducts() {
        // Supprimer tous les produits de la table "cartes"
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("ModuleCapteurs", null, null);
        db.close();
    }
    public  Produit getOneProduct(int id)  //cette méthode fait retourner un produit selon son id
    { SQLiteDatabase dbbb=getWritableDatabase();
        Cursor c=dbbb.query("ModuleCapteurs",new String[]{"_id","nom"},"_id=?",new String[]{String.valueOf(id)},null,null,null);   //tableau de type string fih les nom des colonnes que nous veulon séléectionée, el parametre ely ba3dou c select from produit where l'id égal l'id souhaité
//e5er parametre c un tableau de type string ely bch n7otou fih la valeur qui doit etre affecter lel id ely howa ely ma7tout fel parametre mta3 cette methode
        //maintenant on est sur le cursor fih ken enregistrement w hal enregistrement représente le produit ely selectonnih selon son id
        c.moveToFirst();
        Produit P=new Produit(c.getInt(0),c.getString(1));
        //récupere l'id de ce produit la 7atina zero car l'id a été selectionné en premier lieu l'index de _id c zero
        return P;
    }



    public void deleteProduct(int id){ //l'id mta3 produit ely nhabou nfas5ouh
        SQLiteDatabase dbbb=getWritableDatabase();
        dbbb.delete("ModuleCapteurs","_id=?",new String[]{String.valueOf(id)});
        dbbb.close();

    }
}
