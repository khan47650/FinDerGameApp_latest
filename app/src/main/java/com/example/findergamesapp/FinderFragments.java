package com.example.findergamesapp;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class FinderFragments extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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

        return inflater.inflate(R.layout.fragment_finder_fragments, container, false);
    }
}