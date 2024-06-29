package com.example.findergamesapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.findergamesapp.Database.DatabaseHelper;
import com.example.findergamesapp.models.AdminModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class ActivitySignUp extends AppCompatActivity {
    EditText edtName, edtEmail, edtPassword, edtphone, edtConfirmPass;
    RelativeLayout btnSignUp;
    TextView tvLogin;
    FirebaseAuth auth;
    private String email;
    private String phone;
    private String password;

    DatabaseHelper databaseHelper;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtName = findViewById(R.id.edtName);
        edtphone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPass = findViewById(R.id.edtConfPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvLogin = findViewById(R.id.tvLogin);
        countryCodePicker =findViewById(R.id.ccp);
        auth = FirebaseAuth.getInstance();
        databaseHelper = new DatabaseHelper();
        tvLogin.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ActivityLogin.class);
            startActivity(intent);
        });

        btnSignUp.setOnClickListener(view -> {
            String name = edtName.getText().toString();
            phone = edtphone.getText().toString();
            email = edtEmail.getText().toString();
            password = edtPassword.getText().toString();
            String confirmPassword = edtConfirmPass.getText().toString();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(ActivitySignUp.this, "Please Fill all Fields", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(ActivitySignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }else if(password.length() < 6){
                Toast.makeText(this, "please add more then 6 characters", Toast.LENGTH_SHORT).show();
            }else if(confirmPassword.length() < 6){
                Toast.makeText(this, "please add more then 6 characters", Toast.LENGTH_SHORT).show();
            }
            else {

                phone="+"+countryCodePicker.getSelectedCountryCode().toString()+edtphone.getText().toString();
                Intent intent1 = new Intent(ActivitySignUp.this, ActivityVerification.class);
                intent1.putExtra("name", edtName.getText().toString());
                intent1.putExtra("email", edtEmail.getText().toString());
                intent1.putExtra("password", edtPassword.getText().toString());
                intent1.putExtra("phone", phone);
                startActivity(intent1);
            }
        });


    }


}
