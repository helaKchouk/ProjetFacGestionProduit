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

public class Produit3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Helper3 helper3;
    SQLiteDatabase sqLiteDatabase;
    Spinner spinner;
    String[]name={"Plaque Cuivre 7*10cm Simple Face","Boitier de protection pour Arduino UNO Transparent","Boitier pour Raspberry Pi 3","Plaque d'essai 400PTS","Stylo d'impression 3D"};
    Button button,button2,Retour;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produit3);
        LinearLayout linearLayout=findViewById(R.id.layout11);
        AnimationDrawable animationDrawable= (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        helper3=new Helper3(Produit3.this);
        findid();
        insertData();
        Retour.setOnClickListener(v -> {
            Intent k= new Intent(Produit3.this, familleProduit.class);
            startActivity(k);
        });
    }

    private void insertData() {
//when click submit insert data into database
        button.setOnClickListener(v -> {
            ContentValues contentValues=new ContentValues();
            contentValues.put("category",spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString());
            sqLiteDatabase=helper3.getWritableDatabase();
            Long rec=sqLiteDatabase.insert("Prototypage",null,contentValues);
            if(rec!=null)
            {
                Toast.makeText(Produit3.this,"Le produit a été inséré",Toast.LENGTH_LONG).show();
            }
            else
            {Toast.makeText(Produit3.this,"nn",Toast.LENGTH_LONG).show();}
        });
        //new display data
        button2.setOnClickListener(v -> startActivity(new Intent(Produit3.this,DisplayData3.class)));

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