package com.example.findergamesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.findergamesapp.Database.DatabaseHelper;
import com.example.findergamesapp.databinding.ActivityEditQuestionBinding;
import com.example.findergamesapp.models.Options;
import com.example.findergamesapp.models.QuizModel;
import com.example.findergamesapp.utils.Extra;
import com.google.android.gms.tasks.OnSuccessListener;


public class EditQuestion extends AppCompatActivity {

    QuizModel quizModel,quizModel1;
    Options options;
    ActivityEditQuestionBinding binding;
    DatabaseHelper databaseHelper;
    String answer;
    Extra extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper();
        extra = new Extra(EditQuestion.this);
        Button saveButton = findViewById(R.id.editbtnSave);
        quizModel = (QuizModel) getIntent().getSerializableExtra("data");
        options = quizModel.getOptions();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.options_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.editselectOption.setAdapter(adapter);
        binding.editselectOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });


        loadDataFields();


        saveButton.setOnClickListener(v -> {
            extra.showProgress("updating", "please wait...");
            if (binding.editQuestion.getText().toString().isEmpty()) {
                Toast.makeText(this, "please fill question field", Toast.LENGTH_SHORT).show();
                return;
            } else if (binding.editoption1.getText().toString().isEmpty()) {
                Toast.makeText(this, "please fill option 1 field", Toast.LENGTH_SHORT).show();
                return;
            } else if (binding.editoption2.getText().toString().isEmpty()) {
                Toast.makeText(this, "please fill option 2 field", Toast.LENGTH_SHORT).show();
                return;
            } else if (binding.editoption3.getText().toString().isEmpty()) {
                Toast.makeText(this, "please fill option 3 field", Toast.LENGTH_SHORT).show();
                return;
            } else if (binding.editoption4.getText().toString().isEmpty()) {
                Toast.makeText(this, "please fill option 4 field", Toast.LENGTH_SHORT).show();
                return;
            } else if (binding.editaddDetail.getText().toString().isEmpty()) {
                Toast.makeText(this, "please fill Details field", Toast.LENGTH_SHORT).show();
                return;
            }
            options = new Options(
                    binding.editoption1.getText().toString(),
                    binding.editoption2.getText().toString(),
                    binding.editoption3.getText().toString(),
                    binding.editoption4.getText().toString()
            );
            String correctOption = binding.editselectOption.getSelectedItem().toString();

            if (correctOption.equals("Option 1")) {
                answer = binding.editoption1.getText().toString();
            } else if (correctOption.equals("Option 2")) {
                answer = binding.editoption2.getText().toString();
            } else if (correctOption.equals("Option 3")) {
                answer = binding.editoption3.getText().toString();
            } else if (correctOption.equals("Option 4")) {
                answer = binding.editoption4.getText().toString();
            }

            quizModel1 = new QuizModel(binding.editQuestion.getText().toString(), options, answer, binding.editaddDetail.getText().toString());

            databaseHelper.updatedQuestion(quizModel1,quizModel.getQuestionId()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    extra.cancelProgress();
                    startActivity(new Intent(getApplicationContext(),ActivityAdminHome.class));
                }
            });

        });
    }

    private void loadDataFields() {
        binding.editaddDetail.setText(quizModel.getResultDetails());
        binding.editQuestion.setText(quizModel.getQuestion());
        binding.editoption1.setText(options.getOptions1());
        binding.editoption2.setText(options.getOptions2());
        binding.editoption3.setText(options.getOptions3());
        binding.editoption4.setText(options.getOptions4());
        String correctOptions = quizModel.getAnswer();

        if (correctOptions.equals(options.getOptions1())) {
            binding.editselectOption.setSelection(0);
        } else if (correctOptions.equals(options.getOptions2())) {
            binding.editselectOption.setSelection(1);
        } else if (correctOptions.equals(options.getOptions2())) {
            binding.editselectOption.setSelection(2);
        } else if (correctOptions.equals(options.getOptions2())) {
            binding.editselectOption.setSelection(3);
        }
    }
}
