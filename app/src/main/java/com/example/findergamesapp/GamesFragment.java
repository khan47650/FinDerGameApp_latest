package com.example.findergamesapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findergamesapp.whack_the_mole.Whack_Game_Activity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {factory method to
 * create an instance of this fragment.
 */
public class GamesFragment extends Fragment {
RelativeLayout oxoTab, smackingTab, colorTab;
    public static int totalNumberScore = 1;
    public static int totalHighScore = 0;
    public static int totalLastHighScore = 0;

    int value=0;

    int timer=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_games, container, false);
        totalNumberScore=0;
        oxoTab = (RelativeLayout) view.findViewById(R.id.oxoGame);
        smackingTab = (RelativeLayout) view.findViewById(R.id.smacking);
        colorTab = (RelativeLayout) view.findViewById(R.id.colors);

        oxoTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityOXOMenu.class);
                startActivity(intent);
            }
        });

        smackingTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value=1;
                gameDifficultyDialog();

            }
        });

        colorTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ColorLevelActivity.class);
                startActivity(intent);

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
    public void onResume() {
        super.onResume();
        totalNumberScore=0;
    }

    private void gameDifficultyDialog(){
        Dialog difficultyDialog=new Dialog(getContext());
        difficultyDialog.setContentView(R.layout.game_difficulty_dialog);
        Button btnPlay = difficultyDialog.findViewById(R.id.btnPlay);
        Button btnQuite = difficultyDialog.findViewById(R.id.btnQuit);
        TextView tvEasy=difficultyDialog.findViewById(R.id.tvEasy);
        TextView tvEasyShape=difficultyDialog.findViewById(R.id.tvEasyShape);
        TextView tvModerate=difficultyDialog.findViewById(R.id.tvModerate);
        TextView tvModerateShape=difficultyDialog.findViewById(R.id.tvModerateShape);
        TextView tvHard=difficultyDialog.findViewById(R.id.tvHArd);
        TextView tvHardShape=difficultyDialog.findViewById(R.id.tvHardShape);

        tvEasy.setOnClickListener(view -> {
            tvEasyShape.setVisibility(View.VISIBLE);
            tvModerateShape.setVisibility(View.GONE);
            tvHardShape.setVisibility(View.GONE);
            timer=1;
        });

        tvModerate.setOnClickListener(view -> {
            tvEasyShape.setVisibility(View.GONE);
            tvModerateShape.setVisibility(View.VISIBLE);
            tvHardShape.setVisibility(View.GONE);
            timer=2;
        });

        tvHard.setOnClickListener(view -> {
            tvEasyShape.setVisibility(View.GONE);
            tvModerateShape.setVisibility(View.GONE);
            tvHardShape.setVisibility(View.VISIBLE);
            timer=3;
        });

        btnPlay.setOnClickListener(v -> {
            if (tvEasyShape.getVisibility() == View.GONE && tvModerateShape.getVisibility() == View.GONE && tvHardShape.getVisibility() == View.GONE) {
                Toast.makeText(getContext(), "Please select Difficulty", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(getActivity(), Whack_Game_Activity.class);
                intent.putExtra("lastHighScore", totalLastHighScore);
                intent.putExtra("time", timer);
                Log.d("GameFragemnt","Timer="+timer);
                startActivity(intent);
                difficultyDialog.dismiss();
            }

        });

        btnQuite.setOnClickListener(v -> {
            difficultyDialog.dismiss();
        });

        // Configure the dialog window to be displayed at the bottom
        Window window =difficultyDialog.getWindow();
        if (window != null) {
            window.setGravity(Gravity.CENTER);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        // Set to prevent dismissing the dialog when touched outside of it
        difficultyDialog.setCanceledOnTouchOutside(false);
        // Show the dialog
        difficultyDialog.show();

    }
}