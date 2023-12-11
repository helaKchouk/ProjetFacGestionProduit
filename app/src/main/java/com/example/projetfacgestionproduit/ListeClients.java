
package com.example.projetfacgestionproduit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ListeClients extends AppCompatActivity {
    ListView lst;
    Button Acceuil;
    Helper h=new Helper(ListeClients.this);
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_clients);
        LinearLayout linearLayout=findViewById(R.id.layout8);
        AnimationDrawable animationDrawable= (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        lst =findViewById(R.id.lstc);
        Acceuil=findViewById(R.id.Acceuil);
        Cursor c= h.getAllClients();

        SimpleCursorAdapter adapter=new SimpleCursorAdapter(ListeClients.this,R.layout.item2,c,new String[]{c.getColumnName(0),c.getColumnName(1),c.getColumnName(2)},new int[]{R.id.id,R.id.nom,R.id.prenom},1);
        lst.setAdapter(adapter);

        lst.setOnItemClickListener((parent, view, position, id) -> {

            TextView t=view.findViewById(R.id.id);
            Intent b=new Intent(ListeClients.this, DetailsClient.class);

            b.putExtra("id",t.getText().toString());
            startActivity(b);
        });

        Acceuil.setOnClickListener(v -> {
            Intent i=new Intent(ListeClients.this,Home.class);
            startActivity(i);

        });

    }
}