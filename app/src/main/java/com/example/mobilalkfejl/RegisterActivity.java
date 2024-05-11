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

public class RegisterActivity extends AppCompatActivity {

    private Button confirmButton;
    private Button backButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth = FirebaseAuth.getInstance();

        backButton = findViewById(R.id.back_button2);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        });

        confirmButton = findViewById(R.id.button3);
        confirmButton.setOnClickListener(v -> register());
    }

    public void register() {
        String username = ((EditText) findViewById(R.id.editTextText)).getText().toString();
        String email = ((EditText) findViewById(R.id.editTextTextEmailAddress2)).getText().toString();
        String pw = ((EditText) findViewById(R.id.editTextTextPassword2)).getText().toString();

       auth.createUserWithEmailAndPassword(email, pw).addOnCompleteListener(this, task -> {
           if (task.isSuccessful()) {
               System.out.println("User added Successfully!");
               Intent i = new Intent(RegisterActivity.this, HomeActivity.class);
               startActivity(i);
           } else {
               System.out.println("Unable to add user!");
           }
       });
    }
}