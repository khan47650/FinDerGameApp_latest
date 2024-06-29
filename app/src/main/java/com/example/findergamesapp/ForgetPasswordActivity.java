package com.example.findergamesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findergamesapp.Database.DatabaseHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {
    EditText edtEmail;
    Button btnSendEmail;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        edtEmail =findViewById(R.id.edtEmail);
        btnSendEmail =findViewById(R.id.btnSendEmail);
        auth =FirebaseAuth.getInstance();
        btnSendEmail.setOnClickListener(v->{
           String email =  edtEmail.getText().toString();
            ResetPassword(email);
        });
    }
    private void ResetPassword(String email) {

        auth.sendPasswordResetEmail(email)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ForgetPasswordActivity.this);
                        builder.setTitle("Forget password");
                        builder.setMessage("this email send request to change the password please check email");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                dialog.dismiss();
                            }
                        });
                        builder.show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ForgetPasswordActivity.this, "Error :- " + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
}