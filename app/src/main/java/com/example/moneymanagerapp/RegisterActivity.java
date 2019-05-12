package com.example.moneymanagerapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtEmail,edtPassword;
    private Button btnRegister;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        registerStuff();
    }

    private void registerStuff() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUser = edtEmail.getText().toString().trim();
                String passwordUser = edtPassword.getText().toString().trim();

                //validasi email dan password
                // jika email kosong
                if (emailUser.isEmpty()){
                    edtEmail.setError("Email tidak boleh kosong");
                }
                // jika email not valid
                else if (!Patterns.EMAIL_ADDRESS.matcher(emailUser).matches()){
                    edtEmail.setError("Email tidak valid");
                }
                // jika password kosong
                else if (passwordUser.isEmpty()){
                    edtPassword.setError("Password tidak boleh kosong");
                }
                //jika password kurang dari 6 karakter
                else if (passwordUser.length() < 6){
                    edtPassword.setError("Password minimal terdiri dari 6 karakter");
                } else {
                    auth.createUserWithEmailAndPassword(emailUser, passwordUser)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this,
                                                task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                    }
                                }
                            });
                }
            }
        });
    }

    private void initViews() {
        edtEmail = (EditText) findViewById(R.id.emailRegister);
        edtPassword = (EditText) findViewById(R.id.passRegister);
        btnRegister = (Button) findViewById(R.id.registerButton);
        auth = FirebaseAuth.getInstance();
    }
}
