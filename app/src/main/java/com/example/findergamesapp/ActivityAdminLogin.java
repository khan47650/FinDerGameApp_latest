package com.example.findergamesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityAdminLogin extends AppCompatActivity {
    private EditText edtName, edtPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        firebaseAuth = FirebaseAuth.getInstance();

        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        RelativeLayout btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
                Intent intent = new Intent(ActivityAdminLogin.this,ActivityAdminHome.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void loginUser() {
        String email = edtName.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            String userId = user.getUid();
                            String username = edtName.getText().toString().trim();

                            DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
                            usersRef.child(userId).child("username").setValue(username);

                            Toast.makeText(ActivityAdminLogin.this, "Login successful", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ActivityAdminLogin.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}