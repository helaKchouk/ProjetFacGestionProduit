package com.example.projetfacgestionproduit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import static com.example.projetfacgestionproduit.SharedHelper.sha256;

import com.example.projetfacgestionproduit.ListeClients;

public class ADMIN extends AppCompatActivity {


    EditText _txtLogin,_txtPassword;
    Button _btnConnection;
    SQLiteDatabase db;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        LinearLayout linearLayout=findViewById(R.id.layout);
        AnimationDrawable animationDrawable= (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        _txtLogin = (EditText) findViewById(R.id.txtLogin);
        _txtPassword = (EditText) findViewById(R.id.txtPassword);
        _btnConnection = (Button) findViewById(R.id.btnConnection);

        // Création de la base de données ou ouverture de connexion
        db = openOrCreateDatabase("ComptesWeb",MODE_PRIVATE,null);
        // Création de la table "users"
        db.execSQL("CREATE TABLE IF NOT EXISTS USERS (login varchar primary key, password varchar);");
        // si la table "users" est vide alors ajouter l'utilisateur admin avec mot de passe "123"
        SQLiteStatement s = db.compileStatement("select count(*) from users;");
        long c = s.simpleQueryForLong();
        if (c==0){
            db.execSQL("insert into users (login, password) values (?,?)", new String[] {"admin", sha256("123")});
        }
        _btnConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strLogin = _txtLogin.getText().toString();
                String strPwd = _txtPassword.getText().toString();
                // Créer un curseur pour récupérer le résultat de la requête select
                Cursor cur = db.rawQuery("select password from users where login =?", new String[] {strLogin});
                try {
                    cur.moveToFirst();
                    String p = cur.getString(0);
                    if (p.equals(sha256(strPwd))){
                        Toast.makeText(getApplicationContext(),"Bienvenue " + strLogin, Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), ListeClients.class);
                        startActivity(i);
                    }else{
                        _txtLogin.setText("");
                        _txtPassword.setText("");
                        Toast.makeText(getApplicationContext(),"Echec de connexion",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    _txtLogin.setText("");
                    _txtPassword.setText("");
                    Toast.makeText(getApplicationContext(),"Utilisateur Inexistant",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

    }
}