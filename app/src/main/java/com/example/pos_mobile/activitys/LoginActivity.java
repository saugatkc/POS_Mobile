package com.example.pos_mobile.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pos_mobile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText etemail, etpassword;
    Button login;
    TextView signup;
    SharedPreferences rememberMe;
    ProgressBar loginPB;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        etemail = findViewById(R.id.etUsername);
        etpassword = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);
        signup = findViewById(R.id.tvSignup);
        loginPB=findViewById(R.id.loginProgressBar);

        mAuth = FirebaseAuth.getInstance();

        rememberMe = getSharedPreferences("user", Context.MODE_PRIVATE);
        String email = rememberMe.getString("email", "empty");
        if (!email.equals("empty")) {
            String password = rememberMe.getString("password", "");
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
//            loginuser(email, password);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login.setVisibility(View.INVISIBLE);
                loginPB.setVisibility(View.VISIBLE);
                String email = etemail.getText().toString();
                String password = etpassword.getText().toString();
                loginuser(email, password);
            }

        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void loginuser(String email, String password) {
        if (email.isEmpty()) {
            etemail.setError("Email is required");
            etemail.requestFocus();
            loginPB.setVisibility(View.GONE);
            login.setVisibility(View.VISIBLE);
            return;
        }
        if (password.isEmpty()) {
            etpassword.setError("Password is required");
            etpassword.requestFocus();
            loginPB.setVisibility(View.GONE);
            login.setVisibility(View.VISIBLE);
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    SharedPreferences.Editor editor=rememberMe.edit();
                    editor.putString("email",email);
                    editor.putString("password",password);
                    editor.commit();

                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "failed", Toast.LENGTH_LONG).show();
                    loginPB.setVisibility(View.GONE);
                    login.setVisibility(View.VISIBLE);
                }

            }
        });


    }
}