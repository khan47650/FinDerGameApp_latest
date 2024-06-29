package com.example.findergamesapp.Database;

import android.provider.ContactsContract;

import com.example.findergamesapp.models.QuizModel;
import com.example.findergamesapp.models.Users;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DatabaseHelper {
    DatabaseReference databaseReference,databaseReferenceQuestion;

    public DatabaseHelper() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users profile");
        databaseReferenceQuestion = database.getReference("Question");

    }
    public Task<Void> addUsers(Users users, String uid) {
        return databaseReference.child(uid).setValue(users);
    }
    public Task<Void> addQuestion(QuizModel quizModel) {
        return databaseReferenceQuestion.push().setValue(quizModel);
    }

    public Task<Void> updatedQuestion(QuizModel quizModel, String questionId) {
        return databaseReferenceQuestion.child(questionId).setValue(quizModel);
    }
    public Task<Void> deleteQuestion(String questionId) {
        return databaseReferenceQuestion.child(questionId).removeValue();
    }
    public Task<Void>updateProfileImage(Users users ,String Uid){
       return databaseReference.child(Uid).setValue(users);
    }

    public Query getUser(String uid){
        return databaseReference.child(uid).orderByKey();
    }
    public Query getAllQuestion(){
        return databaseReferenceQuestion.orderByKey();
    }
}
