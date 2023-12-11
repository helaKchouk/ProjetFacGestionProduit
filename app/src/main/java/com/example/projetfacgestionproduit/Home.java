package com.example.projetfacgestionproduit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.graphics.drawable.AnimationDrawable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Home extends AppCompatActivity {

    TextView txt;
    Button clientt,admin;
    FirebaseAuth auth;
    Button button;
    TextView textView;
    FirebaseUser user;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    RelativeLayout relativeLayout=findViewById(R.id.layoutt);
        AnimationDrawable animationDrawable= (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        txt = findViewById(R.id.txt);
        admin = findViewById(R.id.admin);
        clientt = findViewById(R.id.clientt);
        auth= FirebaseAuth.getInstance();
        textView=findViewById(R.id.user_details);
        //user= auth.getCurrentUser();

       /* if(user==null)
        {Intent intent=new Intent(getApplicationContext(),login.class);
            startActivity(intent);
            finish();}
        else
        {
            textView.setText(user.getEmail());
        }*/
        clientt.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();

            Intent intent=new Intent(getApplicationContext(),Compte.class);
            startActivity(intent);
            finish();
        });





        /*clientt.setOnClickListener(v -> {
            Intent c=new Intent(Home.this, Compte.class);
            startActivity(c);
        });*/



        admin.setOnClickListener(v -> {
            Intent b=new Intent(Home.this, ADMIN.class);
            startActivity(b);
        });


    }}