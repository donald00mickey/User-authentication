package com.example.diamondhead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private Button LogMe;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        LogMe = findViewById(R.id.loginMe);

        auth = FirebaseAuth.getInstance();

        LogMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EmailT = Email.getText().toString();
                String PassT = Password.getText().toString();
                loginUser(EmailT,PassT);
            }
        });
    }

    private void loginUser(String Email, String Password) {
        auth.signInWithEmailAndPassword(Email, Password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LoginActivity.this, "login sucessful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, LogoutActivity.class));
                finish();
            }
        });
    }
}