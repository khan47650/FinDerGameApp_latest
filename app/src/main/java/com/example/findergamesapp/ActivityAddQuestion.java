package com.example.findergamesapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.findergamesapp.Database.DatabaseHelper;
import com.example.findergamesapp.models.Options;
import com.example.findergamesapp.models.QuizModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityAddQuestion extends AppCompatActivity {

    AppCompatButton btnSave;
    private EditText addQuestion, option1, option2, option3, option4, addDetail;
    private Spinner selectOption;
    private DatabaseHelper databaseHelper;
    private static final String TAG = "ActivityAddQuestion";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        btnSave = findViewById(R.id.btnSave);
        addQuestion = findViewById(R.id.addQuestion);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        addDetail = findViewById(R.id.addDetail);
        selectOption = findViewById(R.id.selectOption);
        databaseHelper = new DatabaseHelper();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.options_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectOption.setAdapter(adapter);
        selectOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        btnSave.setOnClickListener(v -> saveQuestionToDatabase());
    }

    private void saveQuestionToDatabase() {
        String answer = "";
        String question = addQuestion.getText().toString().trim();
        String ans1 = option1.getText().toString().trim();
        String ans2 = option2.getText().toString().trim();
        String ans3 = option3.getText().toString().trim();
        String ans4 = option4.getText().toString().trim();
        String correctOption = selectOption.getSelectedItem().toString();
        String detail = addDetail.getText().toString().trim();

        if (!question.isEmpty() && !ans1.isEmpty() && !ans2.isEmpty() && !ans3.isEmpty() && !ans4.isEmpty() && !detail.isEmpty()) {
            if (correctOption.equals("Option 1")) {
                answer = ans1;
            } else if (correctOption.equals("Option 2")) {
                answer = ans2;
            } else if (correctOption.equals("Option 3")) {
                answer = ans3;
            } else if (correctOption.equals("Option 4")) {
                answer = ans4;
            }
            Options options = new Options(ans1, ans2, ans3, ans4);
            QuizModel model = new QuizModel(question, options, answer, detail);

            databaseHelper.addQuestion(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(ActivityAddQuestion.this, "successfully added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),ActivityAdminHome.class));
                    finish();
                }
            });

        } else {
            Toast.makeText(ActivityAddQuestion.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
        }
    }


}