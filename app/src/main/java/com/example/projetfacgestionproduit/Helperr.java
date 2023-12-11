package com.example.projetfacgestionproduit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helperr extends SQLiteOpenHelper {

    public static final String DB="computer.db";
    public static final int VER=5;
    String query;
    public Helperr(@Nullable Context context) {
        super(context, DB, null, VER);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        query="create table cartes(id integer primary key,category text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        query="drop table if exists cartes";
        db.execSQL(query);
        onCreate(db);
    }
    public void deleteAllProducts() {
        // Supprimer tous les produits de la table "cartes"
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("cartes", null, null);
        db.close();
    }
}
