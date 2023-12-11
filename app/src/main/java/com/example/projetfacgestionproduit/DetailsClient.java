package com.example.projetfacgestionproduit;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class DetailsClient extends AppCompatActivity {

    EditText nom, prenom;
    Button modifier, supprimer,retour;
    String id;
    Helper h = new Helper(DetailsClient.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_client);

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        retour = findViewById(R.id.retour);
        modifier = findViewById(R.id.modifier);
        supprimer = findViewById(R.id.supprimer);

        id = getIntent().getStringExtra("id");
        Client C = h.getOneClient(Integer.parseInt(id));
        nom.setText(C.getNom());
        prenom.setText(C.getPrenom());



        retour.setOnClickListener(v -> {
            Intent i = new Intent(DetailsClient.this, ADMIN.class);
            startActivity(i);
        });


        modifier.setOnClickListener(v -> {
            Client Cl = new Client(Integer.parseInt(id), nom.getText().toString(), prenom.getText().toString());
            h.updateClient(Cl);
            Intent i = new Intent(DetailsClient.this, ListeClients.class);
            startActivity(i);
        });


        supprimer.setOnClickListener(v1 -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                user.delete()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // L'utilisateur a été supprimé de Firebase Authentication
                                h.deleteClient(Integer.parseInt(id));
                                Intent l = new Intent(DetailsClient.this, ListeClients.class);
                                startActivity(l);
                            } else {
                                // Il y a eu une erreur lors de la suppression de l'utilisateur
                                // Vous pouvez afficher un message d'erreur ou effectuer d'autres actions en conséquence
                                Log.e(TAG, "Error deleting user: " + task.getException().getMessage());
                                Toast.makeText(DetailsClient.this, "Erreur lors de la suppression de l'utilisateur", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }
}
