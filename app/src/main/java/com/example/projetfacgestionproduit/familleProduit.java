package com.example.projetfacgestionproduit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.drawable.AnimationDrawable;
import android.annotation.SuppressLint;
public class familleProduit extends AppCompatActivity {
TextView famillep;
Button cartes,moduleCapteur,Prototypage,Retour;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_famille_produit);
        LinearLayout linearLayout=findViewById(R.id.layout4);
        AnimationDrawable animationDrawable= (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        cartes=findViewById(R.id.cartes);
        Prototypage=findViewById(R.id.Prototypage);
        moduleCapteur=findViewById(R.id.moduleCapteur);
        Retour=findViewById(R.id.Retour);
        famillep=findViewById(R.id.famillep);

        cartes.setOnClickListener(v -> {
            Intent b=new Intent(familleProduit.this, ProduitC.class);
            startActivity(b);
        });
        moduleCapteur.setOnClickListener(v -> {
            Intent u=new Intent(familleProduit.this, Produit2.class);
            startActivity(u);
        });

        Prototypage.setOnClickListener(v -> {
            Intent u=new Intent(familleProduit.this, Produit3.class);
            startActivity(u);
        });
        Retour.setOnClickListener(v -> {
            Intent u=new Intent(familleProduit.this, ListeChoix.class);
            startActivity(u);
        });

    }
}