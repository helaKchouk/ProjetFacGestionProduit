package com.example.projetfacgestionproduit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ListeChoix extends AppCompatActivity {

    TextView choix;
    Button ListeProduits,membre,retour,famille;
    Helperr h = new Helperr(ListeChoix.this);
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_choix);
        LinearLayout linearLayout=findViewById(R.id.layout7);
        AnimationDrawable animationDrawable= (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        famille=findViewById(R.id.famille);
        choix=findViewById(R.id.choix);
        TextView AdministrateurTextView = findViewById(R.id.Administrateur);
        //membre=findViewById(R.id.membre);
        retour=findViewById(R.id.retour);


        /*membre.setOnClickListener(v -> {
            //h.insertInitialProducts(h.getWritableDatabase());
            Intent i=new Intent(ListeChoix.this,ListeClients.class);
            startActivity(i);

        });*/

        AdministrateurTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Créer un Intent pour démarrer l'activité de destination
                Intent intent = new Intent(ListeChoix.this, ADMIN.class);

                // Ajouter des données supplémentaires si nécessaire
                intent.putExtra("clé", "valeur");

                // Démarrer l'activité de destination
                startActivity(intent);
            }
        });
        retour.setOnClickListener(v -> {
            Intent a=new Intent(ListeChoix.this,Compte.class);
            startActivity(a);

        });

        famille.setOnClickListener(v -> {
            Intent b=new Intent(ListeChoix.this,familleProduit.class);
            startActivity(b);
        });
    }
}