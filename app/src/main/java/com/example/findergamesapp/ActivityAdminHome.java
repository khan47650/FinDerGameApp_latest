package com.example.findergamesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.findergamesapp.Database.DatabaseHelper;
import com.example.findergamesapp.adapters.QuizAdapter;
import com.example.findergamesapp.databinding.ActivityAdminHomeBinding;
import com.example.findergamesapp.models.QuizModel;
import com.example.findergamesapp.utils.Extra;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ActivityAdminHome extends AppCompatActivity {

    ActivityAdminHomeBinding binding;

    private List<QuizModel> questionList;

    private AdapterAdminHome adapter;

    DatabaseHelper databaseHelper;
    Extra extra;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper();
        questionList = new ArrayList<>();
        extra = new Extra(ActivityAdminHome.this);
        binding.ivProfile.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),ActivityLogin.class));
            finishAffinity();
        });
        binding.ivAdd.setOnClickListener(v -> {
            Intent intent1 = new Intent(ActivityAdminHome.this, ActivityAddQuestion.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent1);
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        extra.showProgress("data loading","please wait....");
        fetchAndDisplayQuestions();



    }

    private void fetchAndDisplayQuestions() {
        databaseHelper.getAllQuestion().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    QuizModel quizModel = snap.getValue(QuizModel.class);
                    quizModel.setQuestionId(snap.getKey());
                    questionList.add(quizModel);
                }
                adapter = new AdapterAdminHome(questionList, ActivityAdminHome.this);
                binding.recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                extra.cancelProgress();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                extra.cancelProgress();
            }
        });

    }

}