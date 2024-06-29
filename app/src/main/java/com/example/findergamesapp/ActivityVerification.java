package com.example.findergamesapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.findergamesapp.Database.DatabaseHelper;
import com.example.findergamesapp.models.Users;
import com.example.findergamesapp.utils.Extra;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ActivityVerification extends AppCompatActivity {

    private EditText edtOTP;
    String phoneNumber;
    DatabaseHelper databaseHelper;

    String otPid;
    String verificationCode;
    PhoneAuthProvider.ForceResendingToken resendingToken;
    Long timeoutSeconds = 60L;
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    String name, email, password;
    Extra extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        databaseHelper = new DatabaseHelper();
        progressDialog = new ProgressDialog(ActivityVerification.this);
        mAuth = FirebaseAuth.getInstance();
        extra = new Extra(ActivityVerification.this);
        edtOTP=findViewById(R.id.edtOTP);
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        phoneNumber = getIntent().getStringExtra("phone");
        otpCall();
        edtOTP.requestFocus();

        Button btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(v -> {
            String enteredCode = edtOTP.getText().toString();

            if (enteredCode.isEmpty()) {
                Toast.makeText(this, "please fill this fields", Toast.LENGTH_SHORT).show();
            } else {
                progressDialog.cancel();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otPid, enteredCode);
                signInWithPhoneAuthCredential(credential);
            }
        });

    }

    private void otpCall() {

        progressDialog.setTitle("OTP");
        progressDialog.setMessage("please wait..");
        progressDialog.show();

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        progressDialog.cancel();
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        progressDialog.cancel();
                        Toast.makeText(ActivityVerification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("error verfication",e.getMessage());
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        otPid = s;
                        progressDialog.cancel();
                    }
                })
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);


    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            signup(email,password);
                        } else {
                            Toast.makeText(ActivityVerification.this, "Sign Code Error", Toast.LENGTH_SHORT).show();
                        Log.d("error verfication","error");
                        }
                    }
                });
    }

    private void signup(String email, String password) {
        extra.showProgress("Sin up and verification","please wait....");
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Users users = new Users(name, phoneNumber, email, password,"");
                            databaseHelper.addUsers(users, mAuth.getCurrentUser().getUid()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    extra.cancelProgress();
                                    startActivity(new Intent(ActivityVerification.this, ActivityHome.class));
                                    Toast.makeText(ActivityVerification.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    },2000);

                });

    }


}
