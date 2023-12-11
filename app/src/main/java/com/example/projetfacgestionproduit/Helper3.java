package com.example.projetfacgestionproduit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helper3 extends SQLiteOpenHelper {

    public static final String DB="computer.dbb";
    public static final int VER=4;
    String query;
    public Helper3(@Nullable Context context) {
        super(context, DB, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase dbb) {
        query="create table Prototypage(id integer primary key,category text)";
        dbb.execSQL(query);
    }
    public void deleteAllProducts() {
        // Supprimer tous les produits de la table "cartes"
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Prototypage", null, null);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbb, int oldVersion, int newVersion) {
        query="drop table if exists Prototypage";
        dbb.execSQL(query);
        onCreate(dbb);
    }
}
