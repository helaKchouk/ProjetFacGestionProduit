package com.example.projetfacgestionproduit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
 Handler handler = new Handler();
 TextView txt;
 Button clientt, admin;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);

  handler.postDelayed(new Runnable() {
   @Override
   public void run() {
    Intent i = new Intent(MainActivity.this, Home.class);
    startActivity(i);
    finish();
   }
  }, 3000);
 }
}
