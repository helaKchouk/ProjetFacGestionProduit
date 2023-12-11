package com.example.projetfacgestionproduit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.drawable.AnimationDrawable;
import android.widget.LinearLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class inscri extends AppCompatActivity {
    TextInputEditText editTextEmail,editTextPassword;
    Button btn_register,retour;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;
    Helper h=new Helper(this);
    @SuppressLint("MissingInflatedId")
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(),Home.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscri);
        LinearLayout linearLayout=findViewById(R.id.layout6);
        AnimationDrawable animationDrawable= (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        mAuth=FirebaseAuth.getInstance();
        editTextEmail=findViewById(R.id.email2);
        editTextPassword=findViewById(R.id.password2);
        retour=findViewById(R.id.retour);
        btn_register=findViewById(R.id.btn_register);
        progressBar=findViewById(R.id.progressBar);
        TextView AdministrateurTextView = findViewById(R.id.Administrateur);

        textView=findViewById(R.id.loginNow);
        textView.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(), com.example.projetfacgestionproduit.login.class);
            startActivity(intent);
            finish();
        });
        retour.setOnClickListener(v -> {
            Intent i = new Intent(inscri.this, Compte.class);
            startActivity(i);
        });
        AdministrateurTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Créer un Intent pour démarrer l'activité de destination
                Intent intent = new Intent(inscri.this, ADMIN.class);

                // Ajouter des données supplémentaires si nécessaire
                intent.putExtra("clé", "valeur");

                // Démarrer l'activité de destination
                startActivity(intent);
            }
        });
        btn_register.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            String email,password;
            email=String.valueOf(editTextEmail.getText().toString());
            password=String.valueOf(editTextPassword.getText().toString());
            if(TextUtils.isEmpty(email)){
                Toast.makeText(inscri.this,"enter email", Toast.LENGTH_LONG).show();
                return;
            }

            if(TextUtils.isEmpty(password)){
                Toast.makeText(inscri.this,"enter password", Toast.LENGTH_LONG).show();
                return;
            }
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {

                            Toast.makeText(inscri.this, "Account created.",
                                    Toast.LENGTH_SHORT).show();
                            Client C = new Client(editTextEmail.getText().toString(),editTextPassword.getText().toString());
                            h.insertClient(C);

                            Intent intent=new Intent(getApplicationContext(), com.example.projetfacgestionproduit.ListeChoix.class);
                            startActivity(intent);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(inscri.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "createUserWithEmail:failure", task.getException());


                        }
                    });
        }




        );
    }
}