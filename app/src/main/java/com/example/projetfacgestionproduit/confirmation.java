package com.example.projetfacgestionproduit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;

public class confirmation extends AppCompatActivity {
TextView confirmation;
Button dx;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        LinearLayout linearLayout=findViewById(R.id.layout3);
        AnimationDrawable animationDrawable= (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        confirmation=findViewById(R.id.confimation);
        dx=findViewById(R.id.dx);

        dx.setOnClickListener(v -> {
            Intent i=new Intent(confirmation.this, MainActivity.class);
            startActivity(i);
        });
    }
}