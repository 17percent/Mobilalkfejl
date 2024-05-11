package com.example.mobilalkfejl;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button confirmButton;
    private Button backButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth = FirebaseAuth.getInstance();

        backButton = findViewById(R.id.back_button1);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        });

        confirmButton = findViewById(R.id.button2);
        confirmButton.setOnClickListener(v -> login());
    }

    public void login() {
        String email = ((EditText) findViewById(R.id.editTextTextEmailAddress)).getText().toString();
        String pw = ((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();

        auth.signInWithEmailAndPassword(email, pw).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                System.out.println("User logged in successfully!");
                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(i);
            } else {
                System.out.println("Unable to log in user!");
            }
        });
    }

}