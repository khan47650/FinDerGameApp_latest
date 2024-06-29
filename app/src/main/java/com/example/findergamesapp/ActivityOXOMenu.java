package com.example.findergamesapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.findergamesapp.Database.DatabaseHelper;
import com.example.findergamesapp.models.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ActivityOXOMenu extends AppCompatActivity {
    ImageView ivBackArrow;
    DatabaseHelper databaseHelper;
    RelativeLayout playervsPlayer, playervsCom, relative2;
    FirebaseAuth auth;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxomenu);
        databaseHelper = new DatabaseHelper();
        auth = FirebaseAuth.getInstance();
        ivBackArrow = findViewById(R.id.ivArrow);
        playervsPlayer = findViewById(R.id.players);
        playervsCom = findViewById(R.id.playervsCom);
        relative2 = findViewById(R.id.relative2);
        sharedPreferences = getSharedPreferences("OXO_PREFS", Context.MODE_PRIVATE);

        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityHome.class);
                startActivity(intent);
            }
        });

        playervsPlayer.setOnClickListener(v -> {
            gameSelectionDialog();
        });


        relative2.setOnClickListener(v -> {
            gameDifficultyDialog();
        });
    }


    private void gameDifficultyDialog(){
        Dialog difficultyDialog=new Dialog(this);
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
        });

        tvModerate.setOnClickListener(view -> {
            tvEasyShape.setVisibility(View.GONE);
            tvModerateShape.setVisibility(View.VISIBLE);
            tvHardShape.setVisibility(View.GONE);
        });

        tvHard.setOnClickListener(view -> {
            tvEasyShape.setVisibility(View.GONE);
            tvModerateShape.setVisibility(View.GONE);
            tvHardShape.setVisibility(View.VISIBLE);
        });

        btnPlay.setOnClickListener(v -> {
            if (tvEasyShape.getVisibility() == View.GONE && tvModerateShape.getVisibility() == View.GONE && tvHardShape.getVisibility() == View.GONE) {
                Toast.makeText(getApplicationContext(),"Please select Difficulty",Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(ActivityOXOMenu.this, ActivityPlayerVsComputer.class);
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

    private void gameSelectionDialog() {
        Dialog selectionDialog = new Dialog(this);
        // Set the custom layout for the dialog
        selectionDialog.setContentView(R.layout.dialog_selecting_game_icons);

        Button btnPlay = selectionDialog.findViewById(R.id.btnPlay);
        Button btnQuite = selectionDialog.findViewById(R.id.btnQuit);
        RelativeLayout relativeLayoutCross = selectionDialog.findViewById(R.id.relativeCross);
        RelativeLayout relativeRound = selectionDialog.findViewById(R.id.relativeRound);
        ImageView ivSelectCross = selectionDialog.findViewById(R.id.ivSelectCross);
        ImageView ivSelectRound = selectionDialog.findViewById(R.id.ivSelectRound);
        TextView tvPlayer1=selectionDialog.findViewById(R.id.tvPlayer1);
        TextView tvPlayer2=selectionDialog.findViewById(R.id.tvPlayer2);

        relativeLayoutCross.setOnClickListener(view -> {
            ivSelectCross.setVisibility(View.VISIBLE);
            ivSelectRound.setVisibility(View.GONE);
            tvPlayer1.setVisibility(View.VISIBLE);
            tvPlayer2.setVisibility(View.GONE);
            saveSelectedIcons(R.drawable.ic_tic_tac_cross, R.drawable.ic_tic_tac_round);
        });

        relativeRound.setOnClickListener(view -> {
            ivSelectCross.setVisibility(View.GONE);
            ivSelectRound.setVisibility(View.VISIBLE);
            tvPlayer1.setVisibility(View.GONE);
            tvPlayer2.setVisibility(View.VISIBLE);
            saveSelectedIcons(R.drawable.ic_tic_tac_round, R.drawable.ic_tic_tac_cross);
        });

        btnPlay.setOnClickListener(v -> {
            if (ivSelectCross.getVisibility()==View.GONE && ivSelectRound.getVisibility()==View.GONE){
                Toast.makeText(getApplicationContext(),"Please select Any Icon",Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(ActivityOXOMenu.this, ActivityOXOPlay.class);
                startActivity(intent);
                selectionDialog.dismiss();
            }

        });

        btnQuite.setOnClickListener(v -> {
            selectionDialog.dismiss();
        });

        // Configure the dialog window to be displayed at the bottom
        Window window = selectionDialog.getWindow();
        if (window != null) {
            window.setGravity(Gravity.CENTER);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        // Set to prevent dismissing the dialog when touched outside of it
        selectionDialog.setCanceledOnTouchOutside(false);

        // Show the dialog
        selectionDialog.show();
    }

    private void saveSelectedIcons(int playerOneIconResId, int playerTwoIconResId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("PLAYER_ONE_ICON", playerOneIconResId);
        editor.putInt("PLAYER_TWO_ICON", playerTwoIconResId);
        editor.apply();
    }
}
