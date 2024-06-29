package com.example.findergamesapp.whack_the_mole;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findergamesapp.GamesFragment;
import com.example.findergamesapp.R;
import com.example.findergamesapp.databinding.ActivityWhackGameBinding;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import pl.droidsonroids.gif.GifImageView;

public class Whack_Game_Activity extends AppCompatActivity {
    ActivityWhackGameBinding binding;
    Handler handler;
    boolean isStop = false;
    int randomNumber;
    int lastHighScore=0;
    private long timeRemainingInMillis = 5000;
    private CountDownTimer countDownTimer;

    boolean isActivityRunning;
    private long initialTimerDurationInMillis;
    int time;

    private static final String PREFS_NAME = "WhackGamePrefs";
    private static final String KEY_HIGH_SCORE = "HighScore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWhackGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        startTimer();

        handler = new Handler();
        refreshGame();
        GamesFragment.totalHighScore=GamesFragment.totalLastHighScore;

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        lastHighScore = prefs.getInt(KEY_HIGH_SCORE, 0);
        binding.totalHighScore.setText("High Score: " + lastHighScore);

        binding.tvScore.setText("Score: " + GamesFragment.totalNumberScore);

        binding.imvGift1.setOnClickListener(v -> {
            binding.imv1.setVisibility(View.VISIBLE);
            binding.imvGift1.setVisibility(View.INVISIBLE);
            checkCompleteGame();


        });
        binding.imvBack.setOnClickListener(v -> {
            GamesFragment.totalNumberScore=0;
            finish();
        });
        binding.imvGift2.setOnClickListener(v -> {
            binding.imv2.setVisibility(View.VISIBLE);
            binding.imvGift2.setVisibility(View.INVISIBLE);
            checkCompleteGame();
        });
        binding.imvGift3.setOnClickListener(v -> {
            binding.imv3.setVisibility(View.VISIBLE);
            binding.imvGift3.setVisibility(View.INVISIBLE);
            checkCompleteGame();
        });
        binding.imvGift4.setOnClickListener(v -> {
            binding.imv4.setVisibility(View.VISIBLE);
            binding.imvGift4.setVisibility(View.INVISIBLE);
            checkCompleteGame();
        });
        binding.imvGift5.setOnClickListener(v -> {
            binding.imv5.setVisibility(View.VISIBLE);
            binding.imvGift5.setVisibility(View.INVISIBLE);
            checkCompleteGame();

        });

        binding.imvGift6.setOnClickListener(v -> {
            binding.imv6.setVisibility(View.VISIBLE);
            binding.imvGift6.setVisibility(View.INVISIBLE);
            checkCompleteGame();
        });
        binding.imvGift7.setOnClickListener(v -> {
            binding.imv7.setVisibility(View.VISIBLE);
            binding.imvGift7.setVisibility(View.INVISIBLE);
            checkCompleteGame();
        });
        binding.imvGift8.setOnClickListener(v -> {
            binding.imv8.setVisibility(View.VISIBLE);
            binding.imvGift8.setVisibility(View.INVISIBLE);
            checkCompleteGame();
        });
        binding.imvGift9.setOnClickListener(v -> {
            binding.imv9.setVisibility(View.VISIBLE);
            binding.imvGift9.setVisibility(View.INVISIBLE);
            checkCompleteGame();
        });
        binding.imvGift10.setOnClickListener(v -> {
            binding.imv10.setVisibility(View.VISIBLE);
            binding.imvGift10.setVisibility(View.INVISIBLE);
            checkCompleteGame();
        });
        binding.imvGift11.setOnClickListener(v -> {
            binding.imv11.setVisibility(View.VISIBLE);
            binding.imvGift11.setVisibility(View.INVISIBLE);
            checkCompleteGame();
        });

        binding.imv1.setOnClickListener(v -> {
            showFailedDialogBox();
        });
        binding.imv2.setOnClickListener(v -> {
            showFailedDialogBox();
        });
        binding.imv3.setOnClickListener(v -> {
            showFailedDialogBox();
        });
        binding.imv4.setOnClickListener(v -> {
            showFailedDialogBox();
        });
        binding.imv5.setOnClickListener(v -> {
            showFailedDialogBox();
        });
        binding.imv6.setOnClickListener(v -> {
            showFailedDialogBox();
        });
        binding.imv7.setOnClickListener(v -> {
            showFailedDialogBox();
        });
        binding.imv8.setOnClickListener(v -> {
            showFailedDialogBox();
        });binding.imv9.setOnClickListener(v -> {
            showFailedDialogBox();
        });
        binding.imv10.setOnClickListener(v -> {
            showFailedDialogBox();
        });
        binding.imv11.setOnClickListener(v -> {
            showFailedDialogBox();
        });


    }

    private void checkCompleteGame() {
        restartTimer();
        GamesFragment.totalNumberScore++;
        binding.tvScore.setText("Score: " + GamesFragment.totalNumberScore);
        if (GamesFragment.totalNumberScore > lastHighScore) {
            lastHighScore = GamesFragment.totalNumberScore;
            binding.totalHighScore.setText("High Score: " + lastHighScore);
            saveHighScore(lastHighScore);
            return;
        }
        overridePendingTransition(0, 0);
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);



    }

    private void saveHighScore(int highScore) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGH_SCORE, highScore);
        editor.apply();
    }


    private void playGame() {
        Random random = new Random();
        randomNumber = random.nextInt(6) + 1;

        if (randomNumber == 1) {
            binding.imv1.setVisibility(View.INVISIBLE);
            binding.imvGift1.setVisibility(View.VISIBLE);

        } else if (randomNumber == 2) {
            binding.imv2.setVisibility(View.INVISIBLE);
            binding.imv5.setVisibility(View.INVISIBLE);

            binding.imvGift2.setVisibility(View.VISIBLE);
            binding.imvGift5.setVisibility(View.VISIBLE);

        } else if (randomNumber == 3) {
            binding.imv3.setVisibility(View.INVISIBLE);
            binding.imv7.setVisibility(View.INVISIBLE);


            binding.imvGift3.setVisibility(View.VISIBLE);
            binding.imvGift7.setVisibility(View.VISIBLE);


        } else if (randomNumber == 4) {
            binding.imv9.setVisibility(View.INVISIBLE);
            binding.imvGift9.setVisibility(View.VISIBLE);

        } else if (randomNumber == 7) {
            binding.imv8.setVisibility(View.INVISIBLE);
            binding.imv11.setVisibility(View.INVISIBLE);
            binding.imvGift8.setVisibility(View.VISIBLE);
            binding.imvGift11.setVisibility(View.VISIBLE);
        } else if (randomNumber == 6) {

            binding.imv5.setVisibility(View.INVISIBLE);
            binding.imvGift5.setVisibility(View.VISIBLE);

        } else if (randomNumber == 5) {
            binding.imv10.setVisibility(View.INVISIBLE);
            binding.imvGift10.setVisibility(View.VISIBLE);
            isStop = true;

        } else {
            binding.imv4.setVisibility(View.INVISIBLE);
            binding.imvGift4.setVisibility(View.VISIBLE);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshGame();
            }
        },2800);

    }
    @Override
    protected void onRestart() {
        this.recreate();
        refreshGame();
        super.onRestart();

    }

    private void showFailedDialogBox() {
        if (!isActivityRunning){
            return;
        }
        refreshGame();
        AlertDialog.Builder builder = new AlertDialog.Builder(Whack_Game_Activity.this);
        LayoutInflater inflater = (LayoutInflater) getApplication().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_view_smacking_game_over, null);

        Button btnQuit = view.findViewById(R.id.btnQuit);
        Button btnPlay = view.findViewById(R.id.btnPlay);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setBackground(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        } else {
            view.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        builder.setView(view);
        AlertDialog dialog = builder.create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        dialog.setCancelable(false);
        btnPlay.setOnClickListener(v -> {
            GamesFragment.totalNumberScore = 0;
            overridePendingTransition(0, 0);
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);

        });
        btnQuit.setOnClickListener(v -> {
            dialog.dismiss();
            GamesFragment.totalLastHighScore=GamesFragment.totalNumberScore;
            GamesFragment.totalNumberScore = 0;
            finish();
        });

        dialog.show();
    }

    private void refreshGame() {
        binding.imv1.setVisibility(View.VISIBLE);
        binding.imv2.setVisibility(View.VISIBLE);
        binding.imv3.setVisibility(View.VISIBLE);
        binding.imv4.setVisibility(View.VISIBLE);
        binding.imv5.setVisibility(View.VISIBLE);
        binding.imv6.setVisibility(View.VISIBLE);
        binding.imv7.setVisibility(View.VISIBLE);
        binding.imv8.setVisibility(View.VISIBLE);
        binding.imv9.setVisibility(View.VISIBLE);
        binding.imv10.setVisibility(View.VISIBLE);
        binding.imv11.setVisibility(View.VISIBLE);

        binding.imvGift1.setVisibility(View.INVISIBLE);
        binding.imvGift2.setVisibility(View.INVISIBLE);
        binding.imvGift3.setVisibility(View.INVISIBLE);
        binding.imvGift4.setVisibility(View.INVISIBLE);
        binding.imvGift5.setVisibility(View.INVISIBLE);
        binding.imvGift6.setVisibility(View.INVISIBLE);
        binding.imvGift7.setVisibility(View.INVISIBLE);
        binding.imvGift8.setVisibility(View.INVISIBLE);
        binding.imvGift9.setVisibility(View.INVISIBLE);
        binding.imvGift10.setVisibility(View.INVISIBLE);
        binding.imvGift11.setVisibility(View.INVISIBLE);


        playGame();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        GamesFragment.totalLastHighScore=GamesFragment.totalHighScore;
        GamesFragment.totalNumberScore=0;

    }
    // Start the timer with a specified duration
    private void startTimer(long timerDurationInMillis) {
        countDownTimer = new CountDownTimer(timerDurationInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemainingInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                timeRemainingInMillis = 0;
                updateTimerText();
                showFailedDialogBox();
            }
        };

        countDownTimer.start();
    }

    // Overload the startTimer method for initial start
    private void startTimer() {
        time = getIntent().getIntExtra("time", 0);
        Log.d("WhackGameActivity", "Time value received from intent: " + time);

        switch (time) {
            case 1:
                initialTimerDurationInMillis = 15000;
                break;
            case 2:
                initialTimerDurationInMillis = 10000;
                break;
            case 3:
                initialTimerDurationInMillis = 5000;
                break;
            default:
                initialTimerDurationInMillis = 0;
                break;
        }

        startTimer(initialTimerDurationInMillis);
    }

    private void updateTimerText() {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeRemainingInMillis);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeRemainingInMillis) - TimeUnit.MINUTES.toSeconds(minutes);
        String timeFormatted = String.format("%d:%02d", minutes, seconds);
        binding.tvCount.setText(timeFormatted);
    }

    // Add a method to restart the timer
    private void restartTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        startTimer(initialTimerDurationInMillis); // Restart the timer from the initial duration
    }

    @Override
    protected void onStart() {
        super.onStart();
        isActivityRunning = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isActivityRunning = false;
    }
}