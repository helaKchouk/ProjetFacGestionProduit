package com.example.projetfacgestionproduit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;
import android.graphics.drawable.AnimationDrawable;
import android.annotation.SuppressLint;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    TextInputEditText editTextEmail,editTextPassword;
    Button buttonLogin,retour;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;
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
        setContentView(R.layout.activity_login);
        LinearLayout linearLayout=findViewById(R.id.layout9);
        AnimationDrawable animationDrawable= (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        mAuth=FirebaseAuth.getInstance();
        editTextEmail=findViewById(R.id.email1);
        editTextPassword=findViewById(R.id.password1);
        buttonLogin=findViewById(R.id.btn_login);
        retour=findViewById(R.id.retour);
        progressBar=findViewById(R.id.progressBar);
        textView=findViewById(R.id.registerNow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),inscri.class);
                startActivity(intent);
                finish();
            }
        });

        retour.setOnClickListener(v -> {
            Intent i = new Intent(login.this, Compte.class);
            startActivity(i);
        });


        buttonLogin.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            String email,password;
            email=String.valueOf(editTextEmail.getText().toString());
            password=String.valueOf(editTextPassword.getText().toString());
            if(TextUtils.isEmpty(email)){
                Toast.makeText(login.this,"enter email", Toast.LENGTH_LONG).show();
                return;
            }

            if(TextUtils.isEmpty(password)){
                Toast.makeText(login.this,"enter password", Toast.LENGTH_LONG).show();
                return;
            }


            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"login succed",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(getApplicationContext(),ListeChoix.class);
                            startActivity(intent);
                            finish();
                        } else {

                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    });
        });
    }
}