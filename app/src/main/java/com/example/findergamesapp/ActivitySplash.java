package com.example.findergamesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.findergamesapp.utils.Constant;
import com.google.firebase.auth.FirebaseAuth;

public class ActivitySplash extends AppCompatActivity {

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        auth = FirebaseAuth.getInstance();
        int splashDuration = 3000;
        new Handler().postDelayed(() -> {
            if(auth.getCurrentUser()!=null){
                if(auth.getCurrentUser().getUid().equals(Constant.AdminId)){
                    startActivity(new Intent(ActivitySplash.this,ActivityAdminHome.class));
                    finish();
                }else{
                    startActivity(new Intent(ActivitySplash.this,ActivityHome.class));
                    finish();
                }

            }else{
                startActivity(new Intent(ActivitySplash.this, ActivityLogin.class));
                finish();
            }
        }, splashDuration);
    }
}