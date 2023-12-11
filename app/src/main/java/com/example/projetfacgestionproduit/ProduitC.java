package com.example.projetfacgestionproduit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class ProduitC extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Helperr helperr;
    SQLiteDatabase sqLiteDatabasee;
    Spinner spinnerr;
    String[]name={"esp 32","stm32","Raspberry Pi 4 Model B 2GB RAM","Raspberry Pi Zéro WH Avec Connecteurs GPIO Soudés"};
    Button buttonC,buttonCC,Retour;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produit_c);
        LinearLayout linearLayout=findViewById(R.id.layout12);
        AnimationDrawable animationDrawable= (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        helperr=new Helperr(ProduitC.this);
        findid();
        insertData();
        Retour.setOnClickListener(v -> {
            Intent p= new Intent(ProduitC.this, familleProduit.class);
            startActivity(p);
        });
    }

    private void insertData() {
//when click submit insert data into database
        buttonC.setOnClickListener(v -> {
            ContentValues contentValues=new ContentValues();
            contentValues.put("category",spinnerr.getItemAtPosition(spinnerr.getSelectedItemPosition()).toString());
            sqLiteDatabasee=helperr.getWritableDatabase();
            Long rec=sqLiteDatabasee.insert("cartes",null,contentValues);
            if(rec!=null)
            {
                Toast.makeText(ProduitC.this,"Le produit a été inséré",Toast.LENGTH_LONG).show();
            }
            else
            {Toast.makeText(ProduitC.this,"nn",Toast.LENGTH_LONG).show();}
        });
        //new display data
        buttonCC.setOnClickListener(v -> startActivity(new Intent(ProduitC.this,DisplayData2.class)));
        Log.e("ProduitC", "ButtonC ou ButtonCC est null");
    }

    private void findid() {
        buttonC=(Button)findViewById(R.id.buttonC);
        buttonCC=(Button)findViewById(R.id.buttonCC);
        Retour=(Button)findViewById(R.id.Retour);
        spinnerr=(Spinner)findViewById(R.id.spinnerr);
        spinnerr.setOnItemSelectedListener(this);

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,name);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerr.setAdapter(arrayAdapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}