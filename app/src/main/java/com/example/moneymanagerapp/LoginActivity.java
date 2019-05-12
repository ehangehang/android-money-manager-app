package com.example.moneymanagerapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private EditText edtEmail;
    private EditText edtPass;

    private TextView register;

    private Button login;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        loginStuff();

    }

    private void loginStuff() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailUser = edtEmail.getText().toString().trim();
                final String passwordUser = edtPass.getText().toString().trim();

                if (emailUser.isEmpty()) {
                    edtEmail.setError("Email tidak boleh kosong");
                }
                // jika email not valid
                else if (!Patterns.EMAIL_ADDRESS.matcher(emailUser).matches()) {
                    edtEmail.setError("Email tidak valid");
                }
                else if (passwordUser.length() < 6) {
                    edtPass.setError("Password minimal terdiri dari 6 karakter");
                } else {
                    mAuth.signInWithEmailAndPassword(emailUser, passwordUser)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this,
                                                task.getException().getMessage()
                                                , Toast.LENGTH_LONG).show();
                                    } else {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("email", emailUser);
                                        bundle.putString("pass", passwordUser);
                                        startActivity(new Intent(LoginActivity.this, HomeActivity.class)
                                                .putExtra("emailpass", bundle));
                                        finish();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void initViews() {
        edtEmail = (EditText) findViewById(R.id.emailLogin);
        edtPass = (EditText) findViewById(R.id.passLogin);
        register = (TextView) findViewById(R.id.toRegister);
        login = (Button) findViewById(R.id.loginButton);

        mAuth = FirebaseAuth.getInstance();
    }
}
