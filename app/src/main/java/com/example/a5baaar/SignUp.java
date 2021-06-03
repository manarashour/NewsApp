package com.example.a5baaar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    MaterialButton alreadyUser;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    MaterialButton createAccount;
    TextInputEditText name;
    TextInputEditText email;
    TextInputEditText password;
    MaterialCheckBox agree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        createAccount = findViewById(R.id.createAccount_btn);
        getSupportActionBar().hide();
        alreadyUser = findViewById(R.id.alreadyUser_btn);
        alreadyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, sign_in_activity.class);
                startActivity(intent);
            }
        });

        name = findViewById(R.id.name_edt);
        email = findViewById(R.id.email_edt2);
        password = findViewById(R.id.password_edt2);
        agree = findViewById(R.id.agree);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }

            private void signUp() {
                String name_signUp = name.getText().toString();
                String email_signUp = email.getText().toString();
                String password_signUp = password.getText().toString();
                if (name_signUp.isEmpty() || email_signUp.isEmpty() || password_signUp.isEmpty() || !agree.isChecked()) {
                    Toast.makeText(SignUp.this, "Please fill all data and sure that you agree to term and conditions", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password_signUp.length() < 6) {
                    Toast.makeText(SignUp.this, "Please write strong password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email_signUp, password_signUp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "User created Successfully", Toast.LENGTH_SHORT).show();
                            finish();

                        } else {
                            Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }

}