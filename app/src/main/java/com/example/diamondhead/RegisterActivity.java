package com.example.diamondhead;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private Button SUCSign;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        SUCSign = findViewById(R.id.SUCSign);

        auth = FirebaseAuth.getInstance();

        SUCSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailT = Email.getText().toString();
                String passT = Password.getText().toString();

                if (TextUtils.isEmpty(emailT)|| TextUtils.isEmpty(passT)){
                    Toast.makeText(RegisterActivity.this, "Empty Credential", Toast.LENGTH_SHORT).show();
                }else if (passT.length()<6){
                    Toast.makeText(RegisterActivity.this, "Password too Short", Toast.LENGTH_SHORT).show();
                }else {
                    registerUser(emailT, passT);
                }
            }
        });
    }

    private void registerUser(String Email, String Password) {
        auth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Registering user sucessful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                }else{
                    Toast.makeText(RegisterActivity.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}