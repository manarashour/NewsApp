package com.example.a5baaar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_in_activity extends AppCompatActivity {

    MaterialButton newUser;
    MaterialButton logInWithYourAccount;
    MaterialButton facebook;
    MaterialButton google;
    TextInputEditText e_mail;
    TextInputEditText pass_word;
    MaterialCheckBox remember_me;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_activity);

        getSupportActionBar().hide();

        newUser = findViewById(R.id.newUser_btn);
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sign_in_activity.this, SignUp.class);
                startActivity(intent);
            }
        });

        logInWithYourAccount = findViewById(R.id.logIn_btn);


        facebook = findViewById(R.id.facebook_btn);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sign_in_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        google = findViewById(R.id.google_btn);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sign_in_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        remember_me = findViewById(R.id.remember_me);
        remember_me.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Toast.makeText(sign_in_activity.this, "your password will be remembered", Toast.LENGTH_LONG).show();
                }
                if (!b) {
                    Toast.makeText(sign_in_activity.this, "your password is not saved ", Toast.LENGTH_LONG).show();
                }
            }
        });

        e_mail = findViewById(R.id.email_edt);
        pass_word = findViewById(R.id.password_edt);
        logInWithYourAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e_mail.getText().toString();
                String pass = pass_word.getText().toString();
                if (email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(sign_in_activity.this, "Please fill all data", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(sign_in_activity.this, "Login success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(sign_in_activity.this, MainActivity.class));
                            finish();

                        } else {
                            Toast.makeText(sign_in_activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

}
