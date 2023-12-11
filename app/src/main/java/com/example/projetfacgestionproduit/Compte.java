package com.example.projetfacgestionproduit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;


public class Compte extends AppCompatActivity {

    TextView txt;
    Button seConnecter,inscrip,Retour;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);

        LinearLayout linearLayout=findViewById(R.id.layout2);
        AnimationDrawable animationDrawable= (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        txt = findViewById(R.id.txt);
        inscrip = findViewById(R.id.inscrip);
        seConnecter = findViewById(R.id.seConnecter);
        Retour = findViewById(R.id.Retour);
        inscrip.setOnClickListener(v -> {
            Intent b=new Intent(Compte.this, inscri.class);
            startActivity(b);
        });

        seConnecter.setOnClickListener(v -> {
            Intent c=new Intent(Compte.this, login.class);
            startActivity(c);
        });
        Retour.setOnClickListener(v -> {
            Intent i=new Intent(Compte.this, Home.class);
            startActivity(i);
        });

    }}