package com.example.findergamesapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findergamesapp.Database.DatabaseHelper;
import com.example.findergamesapp.models.Users;
import com.example.findergamesapp.utils.Extra;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class ActivityProfile extends AppCompatActivity {
    ImageView ivBArrow, ivPen, ivProfile;
    TextView tvName, tvEmail, tvNumber;
    RelativeLayout btnSignout;
    DatabaseHelper databaseHelper;
    FirebaseStorage mStore;
    FirebaseAuth auth;
    Users users;
    public Uri uri;
    RelativeLayout btnSignOut;
    Extra extra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ivBArrow = findViewById(R.id.ivArrow);
        ivProfile = findViewById(R.id.profile_picture);
        ivPen = findViewById(R.id.ivEdit);
        tvName = findViewById(R.id.tvName);
        tvNumber = findViewById(R.id.tvNumber);
        tvEmail = findViewById(R.id.tvMail);
        btnSignout = findViewById(R.id.btnSignOut);
        auth = FirebaseAuth.getInstance();
        extra = new Extra(ActivityProfile.this);
        mStore = FirebaseStorage.getInstance();
        databaseHelper = new DatabaseHelper();

        ivBArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityHome.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        btnSignout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), ActivityLogin.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finishAffinity();
        });

        extra.showProgress("Loading profile","please wait..");

        databaseHelper.getUser(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users = snapshot.getValue(Users.class);
                tvEmail.setText(users.getEmail());
                tvName.setText(users.getName());
                tvNumber.setText(users.getPhone());
                if (users != null && users.getImage() != null && !users.getImage().isEmpty()) {
                    Picasso.get()
                            .load(users.getImage())
                            .into(ivProfile);
                }

                extra.cancelProgress();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityProfile.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            extra.cancelProgress();
            }
        });
        ivPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 45);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            if (data.getData() != null) {
                uri = data.getData();
                ivProfile.setImageURI(uri);
                extra.showProgress("Update profile","please wait...");
                if (uri != null ) {
                    StorageReference filePath = mStore.getReference().child("ImagePost").child(uri.getLastPathSegment());
                    filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> downloadUri = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {

                                    users.setImage(task.getResult().toString());
                                    databaseHelper.updateProfileImage(users,FirebaseAuth.getInstance().getUid()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {

                                            extra.cancelProgress();

                                        }
                                    });
                                }
                            });
                        }
                    });
                }else{
                    extra.cancelProgress();
                }
            }
        }

    }

}