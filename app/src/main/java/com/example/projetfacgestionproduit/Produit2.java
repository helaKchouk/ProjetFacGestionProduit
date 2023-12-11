package com.example.projetfacgestionproduit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class Produit2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
   Helperrr helperrr;
    SQLiteDatabase sqLiteDatabase;
    Spinner spinner;
    String[]name={"Module Capteur De Lumière Ambiante TEMT6000","LM393 Module capteur de vitesse IR 7mm 4 pines","Capteur de Mouvement PIR HC-SR501","Module Détecteur D'obstacle IR Pour Arduino" +
            "Capteur Infrarouge TCRT5000","Capteur Suiveur de ligne IR KY-033"};
    Button button,button2,Retour;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produit2);
        LinearLayout linearLayout=findViewById(R.id.layout10);
        AnimationDrawable animationDrawable= (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        helperrr=new Helperrr(Produit2.this);
        findid();
        insertData();
        Retour.setOnClickListener(v -> {
            Intent c= new Intent(Produit2.this, familleProduit.class);
            startActivity(c);
        });
    }

    private void insertData() {
//when click submit insert data into database
        button.setOnClickListener(v -> {
            ContentValues contentValues=new ContentValues();
            contentValues.put("category",spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString());
            sqLiteDatabase=helperrr.getWritableDatabase();
            Long rec=sqLiteDatabase.insert("ModuleCapteurs",null,contentValues);
            if(rec!=null)
            {
                Toast.makeText(Produit2.this,"Le produit a été inséré",Toast.LENGTH_LONG).show();
            }
            else
            {Toast.makeText(Produit2.this,"nn",Toast.LENGTH_LONG).show();}
        });
        //new display data
        button2.setOnClickListener(v -> startActivity(new Intent(Produit2.this,DisplayData.class)));

    }

    private void findid() {
        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
        Retour=(Button)findViewById(R.id.Retour);
        spinner=(Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,name);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}