package com.example.pos_mobile.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pos_mobile.R;
import com.example.pos_mobile.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText etEmail, etFullName, etPhone, etPassword,etSignUpPassword,etSignUpConfirmPassword;
    Button register,pp;
    ProgressBar regPB;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();
        etFullName = findViewById(R.id.etFullName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etSignUpPassword = findViewById(R.id.etSignUpPassword);
        etSignUpConfirmPassword=findViewById(R.id.etSignUpConfirmPassword);
        register = findViewById(R.id.btnSignupp);
        pp = findViewById(R.id.pp);
        regPB=findViewById(R.id.regProgressBar);

        mAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register.setVisibility(View.INVISIBLE);
                regPB.setVisibility(View.VISIBLE);
                signup();
            }
        });
        pp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }

    private void signup() {
        String email = etEmail.getText().toString();
        String fullName = etFullName.getText().toString();
        String phone = etPhone.getText().toString();
        String password = etSignUpPassword.getText().toString();
        String confirmPassword = etSignUpConfirmPassword.getText().toString();

        if (fullName.isEmpty()) {
            etFullName.setError("Full Name is required");
            etFullName.requestFocus();
            regPB.setVisibility(View.GONE);
            register.setVisibility(View.VISIBLE);
            return;
        }
        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            regPB.setVisibility(View.GONE);
            register.setVisibility(View.VISIBLE);
            return;
        }
        if (phone.isEmpty()) {
            etPhone.setError("Phone is required");
            etPhone.requestFocus();
            regPB.setVisibility(View.GONE);
            register.setVisibility(View.VISIBLE);
            return;
        }
        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            regPB.setVisibility(View.GONE);
            register.setVisibility(View.VISIBLE);
            return;
        }
        if (password.length() < 6) {
            etPassword.setError("Password should be 6 characters long:");
            etPassword.requestFocus();
            regPB.setVisibility(View.GONE);
            register.setVisibility(View.VISIBLE);
            return;
        }
        if(!password.equals(confirmPassword)){
            etSignUpConfirmPassword.setError("Password not matching");
            etSignUpConfirmPassword.requestFocus();
            regPB.setVisibility(View.GONE);
            register.setVisibility(View.VISIBLE);
            return;

        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            UserModel user = new UserModel(fullName, email, phone, password);
                            FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).
                                    setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "succes", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "failed", Toast.LENGTH_LONG).show();
                                        regPB.setVisibility(View.GONE);
                                        register.setVisibility(View.VISIBLE);
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(RegisterActivity.this, "failed", Toast.LENGTH_LONG).show();
                            regPB.setVisibility(View.GONE);
                            register.setVisibility(View.VISIBLE);
                        }

                    }
                });
    }

}

