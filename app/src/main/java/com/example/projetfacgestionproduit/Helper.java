package com.example.projetfacgestionproduit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.annotation.Nullable;

import java.security.MessageDigest;

public class Helper extends SQLiteOpenHelper {




    public Helper(@Nullable Context context) {
        super(context, "productManager", null, 9);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE client(_id INTEGER PRIMARY KEY, nom TEXT, prenom TEXT)");


    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS client");
        onCreate(db);
    }

    public void insertClient(Client C) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom", C.getNom());
        cv.put("prenom", C.getPrenom());
        db.insert("client", null, cv);
        db.close();
    }



    public void updateClient(Client C) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom", C.getNom());
        cv.put("prenom", C.getPrenom());
        db.update("client", cv, "_id=?", new String[]{String.valueOf(C.getId())});
        db.close();
    }

    public void deleteClient(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("client", "_id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Cursor getAllClients() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM client", null);
    }

    public Client getOneClient(int id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query("client", new String[]{"_id", "nom", "prenom"}, "_id=?", new String[]{String.valueOf(id)}, null, null, null);
        c.moveToFirst();
        Client C = new Client(c.getInt(0), c.getString(1), c.getString(2));
        c.close();
        return C;
    }
    public void deleteAllClients() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("client", null, null);
        db.close();
    }

    public boolean isValidUser(String login, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM client WHERE nom=? AND prenom=?", new String[]{login, password});
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        return isValid;
    }


}
