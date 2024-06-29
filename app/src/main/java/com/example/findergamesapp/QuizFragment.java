package com.example.findergamesapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findergamesapp.Database.DatabaseHelper;
import com.example.findergamesapp.adapters.QuizAdapter;
import com.example.findergamesapp.interfaces.OnRemoveLastQuestion;
import com.example.findergamesapp.models.QuizModel;
import com.example.findergamesapp.utils.Extra;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuizFragment extends Fragment implements OnRemoveLastQuestion {
    private List<QuizModel> list;
    RecyclerView recyclerView;
    int lastIndex = 2;
    QuizAdapter adapter;
    DatabaseHelper databaseHelper;
    Extra extra;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        databaseHelper = new DatabaseHelper();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        list = new ArrayList<>();
        extra = new Extra(getContext());
        extra.showProgress("Loading Data", "please wait...");

        databaseHelper.getAllQuestion().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    QuizModel quizModel = snap.getValue(QuizModel.class);
                    list.add(quizModel);
                }
                adapter = new QuizAdapter(getContext(), list, QuizFragment.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                extra.cancelProgress();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                extra.cancelProgress();
            }
        });

        final long[] backPressedTime = {0};

        getActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (backPressedTime[0] + 2000 > System.currentTimeMillis()) {
                    getActivity().finish();
                    return;
                } else {
                    Toast.makeText(getContext(), getResources().getString(R.string.doubleBackToExitPressedMessage), Toast.LENGTH_SHORT).show();
                }
                backPressedTime[0] = System.currentTimeMillis();
            }
        });


        return view;
    }

    @Override
    public void onRemoveLastQuestion() {
        int size = list.size();
        if (list.isEmpty() || size == 0 || size == 1) {
            return;
        } else if (size >= 2) {
            list.remove(0);
            list.remove(0);
            adapter.notifyDataSetChanged();
        } else if (size == 1) {
            list.remove(0);
            adapter.notifyDataSetChanged();
        }
    }

}
