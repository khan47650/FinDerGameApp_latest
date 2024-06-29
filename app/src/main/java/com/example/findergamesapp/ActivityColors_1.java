package com.example.findergamesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.findergamesapp.databinding.ActivityColors1Binding;
import com.example.findergamesapp.utils.SharedPreferenceClass;

import java.util.Random;

public class ActivityColors_1 extends AppCompatActivity {

    ActivityColors1Binding binding;
    int color1, color2, color3, color4, color5, color6;
    String name1, name2, name3, name4, name5, name6, level;
    boolean isColor1Selected = false, isColor2Selected = false, isColor3Selected = false,
            isColor4Selected = false, isColor5Selected = false, isColor6Selected = false;
    private CountDownTimer countDownTimer;
    private int selectedColorCount = 0;
    private boolean isActivityRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityColors1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ivArrow.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ColorLevelActivity.class);
            startActivity(intent);
        });
        binding.ivPic.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ActivityColorsChart.class);
            startActivity(intent);
        });

        // Retrieve the current level from shared preferences
        SharedPreferenceClass sharedPreferenceClass = new SharedPreferenceClass(this);
        int currentLevel = sharedPreferenceClass.getCurrentLevel();

        color1 = getIntent().getIntExtra("color1", -1);
        color2 = getIntent().getIntExtra("color2", -1);
        color3 = getIntent().getIntExtra("color3", -1);
        color4 = getIntent().getIntExtra("color4", -1);
        color5 = getIntent().getIntExtra("color5", -1);
        color6 = getIntent().getIntExtra("color6", -1);
        name1 = getIntent().getStringExtra("name1");
        name2 = getIntent().getStringExtra("name2");
        name3 = getIntent().getStringExtra("name3");
        name4 = getIntent().getStringExtra("name4");
        name5 = getIntent().getStringExtra("name5");
        name6 = getIntent().getStringExtra("name6");
        level = getIntent().getStringExtra("level");

        rightColors();

        binding.tvNames.setText(name1 + " = " + name2 + " , " + name3 + " = " + name5 + " \n\t\t\t\t\t\t\t " + name4 + " = " + name6);
        binding.tvSelectedNames.setText("Find " + name1 + " , " + name3 + " and " + name4);
        binding.tvLevel.setText("Level " + level);


        binding.tvLeftColor1.setText(name1);
        binding.tvLeftColor2.setText(name2);
        binding.tvLeftColor3.setText(name3);
        binding.tvLeftColor4.setText(name4);
        binding.tvLeftColor5.setText(name5);
        binding.tvLeftColor6.setText(name6);


        int[] colors1 = {color2, color5, color6};
        shuffleArray(colors1, 0, 2);
        color2 = colors1[0];
        color5 = colors1[1];
        color6 = colors1[2];

        int[] colors2 = {color1, color3, color4};
        shuffleArray(colors2, 0, 2);
        color1 = colors2[0];
        color3 = colors2[1];
        color4 = colors2[2];


        GradientDrawable shapeDrawable = new GradientDrawable();
        shapeDrawable.setShape(GradientDrawable.RECTANGLE);
        shapeDrawable.setCornerRadius(getResources().getDimension(R.dimen.card_corner_radius));
        shapeDrawable.setColor(getResources().getColor(color1));
        binding.Color1.setBackground(shapeDrawable);


        GradientDrawable shapeDrawable2 = new GradientDrawable();
        shapeDrawable2.setShape(GradientDrawable.RECTANGLE);
        shapeDrawable2.setCornerRadius(getResources().getDimension(R.dimen.card_corner_radius));
        shapeDrawable2.setColor(getResources().getColor(color2));
        binding.Color2.setBackground(shapeDrawable2);


        GradientDrawable shapeDrawable3 = new GradientDrawable();
        shapeDrawable3.setShape(GradientDrawable.RECTANGLE);
        shapeDrawable3.setCornerRadius(getResources().getDimension(R.dimen.card_corner_radius));
        shapeDrawable3.setColor(getResources().getColor(color5));
        binding.Color3.setBackground(shapeDrawable3);

        GradientDrawable shapeDrawable4 = new GradientDrawable();
        shapeDrawable4.setShape(GradientDrawable.RECTANGLE);
        shapeDrawable4.setCornerRadius(getResources().getDimension(R.dimen.card_corner_radius));
        shapeDrawable4.setColor(getResources().getColor(color4));
        binding.Color4.setBackground(shapeDrawable4);

        GradientDrawable shapeDrawableBlack = new GradientDrawable();
        shapeDrawableBlack.setShape(GradientDrawable.RECTANGLE);
        shapeDrawableBlack.setCornerRadius(getResources().getDimension(R.dimen.card_corner_radius));
        shapeDrawableBlack.setColor(getResources().getColor(color3));
        binding.Color5.setBackground(shapeDrawableBlack);

        GradientDrawable shapeDrawableWhite = new GradientDrawable();
        shapeDrawableWhite.setShape(GradientDrawable.RECTANGLE);
        shapeDrawableWhite.setCornerRadius(getResources().getDimension(R.dimen.card_corner_radius));
        shapeDrawableWhite.setColor(getResources().getColor(color6));
        binding.Color6.setBackground(shapeDrawableWhite);

        binding.Color1.setOnClickListener(v -> {
            if (isColor1Selected) {
                binding.pColor1.setBackground(null);
                isColor1Selected = false;
                selectedColorCount--;
            } else {
                GradientDrawable shapeDrawable5 = new GradientDrawable();
                shapeDrawable5.setShape(GradientDrawable.RECTANGLE);
                shapeDrawable5.setCornerRadius(getResources().getDimension(R.dimen.card_corner_radius));
                shapeDrawable5.setColor(getResources().getColor(R.color.grey));
                binding.pColor1.setBackground(shapeDrawable5);
                isColor1Selected = true;
                selectedColorCount++;
            }
            validateSelection();
        });

        binding.Color2.setOnClickListener(v -> {
            if (isColor2Selected) {
                binding.pColor2.setBackground(null);
                isColor2Selected = false;
                selectedColorCount--;
            } else {
                GradientDrawable shapeDrawable5 = new GradientDrawable();
                shapeDrawable5.setShape(GradientDrawable.RECTANGLE);
                shapeDrawable5.setCornerRadius(getResources().getDimension(R.dimen.card_corner_radius));
                shapeDrawable5.setColor(getResources().getColor(R.color.grey));
                binding.pColor2.setBackground(shapeDrawable5);
                isColor2Selected = true;
                selectedColorCount++;
            }
            validateSelection();
        });

        binding.Color3.setOnClickListener(v -> {
            if (isColor3Selected) {
                binding.pColor3.setBackground(null);
                isColor3Selected = false;
                selectedColorCount--;
            } else {
                GradientDrawable shapeDrawable5 = new GradientDrawable();
                shapeDrawable5.setShape(GradientDrawable.RECTANGLE);
                shapeDrawable5.setCornerRadius(getResources().getDimension(R.dimen.card_corner_radius));
                shapeDrawable5.setColor(getResources().getColor(R.color.grey));
                binding.pColor3.setBackground(shapeDrawable5);
                isColor3Selected = true;
                selectedColorCount++;
            }
            validateSelection();
        });

        binding.Color4.setOnClickListener(v -> {
            if (isColor4Selected) {
                binding.pColor4.setBackground(null);
                isColor4Selected = false;
                selectedColorCount--;
            } else {
                GradientDrawable shapeDrawable5 = new GradientDrawable();
                shapeDrawable5.setShape(GradientDrawable.RECTANGLE);
                shapeDrawable5.setCornerRadius(getResources().getDimension(R.dimen.card_corner_radius));
                shapeDrawable5.setColor(getResources().getColor(R.color.grey));
                binding.pColor4.setBackground(shapeDrawable5);
                isColor4Selected = true;
                selectedColorCount++;
            }
            validateSelection();
        });

        binding.Color5.setOnClickListener(v -> {
            if (isColor5Selected) {
                binding.pColor5.setBackground(null);
                isColor5Selected = false;
                selectedColorCount--;
            } else {
                GradientDrawable shapeDrawable5 = new GradientDrawable();
                shapeDrawable5.setShape(GradientDrawable.RECTANGLE);
                shapeDrawable5.setCornerRadius(getResources().getDimension(R.dimen.card_corner_radius));
                shapeDrawable5.setColor(getResources().getColor(R.color.grey));
                binding.pColor5.setBackground(shapeDrawable5);
                isColor5Selected = true;
                selectedColorCount++;
            }
            validateSelection();
        });

        binding.Color6.setOnClickListener(v -> {
            if (isColor6Selected) {
                binding.pColor6.setBackground(null);
                isColor6Selected = false;
                selectedColorCount--;
            } else {
                GradientDrawable shapeDrawable5 = new GradientDrawable();
                shapeDrawable5.setShape(GradientDrawable.RECTANGLE);
                shapeDrawable5.setCornerRadius(getResources().getDimension(R.dimen.card_corner_radius));
                shapeDrawable5.setColor(getResources().getColor(R.color.grey));
                binding.pColor6.setBackground(shapeDrawable5);
                isColor6Selected = true;
                selectedColorCount++;
            }
            validateSelection();
        });

        startTimer();
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

    private void validateSelection() {
         if (selectedColorCount >= 3) {
            checkSelection();
        }
    }

    private void checkSelection() {
        if (!(isColor2Selected && isColor3Selected && isColor6Selected)) {
            gameFinishedDialog("Wrong Selection");
        } else {
            // Retrieve the current level from shared preferences
            SharedPreferenceClass sharedPreferenceClass = new SharedPreferenceClass(this);
            int currentLevel = sharedPreferenceClass.getCurrentLevel();

            // Check if the current level played is the highest unlocked level
            if (Integer.parseInt(level) == currentLevel) {

                // Increment the unlocked level
                int nextLevel = currentLevel + 1;
                sharedPreferenceClass.setCurrentLevel(nextLevel);
            }

            Intent intent = new Intent(ActivityColors_1.this, ColorLevelActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void gameFinishedDialog(String message) {
        if (!isActivityRunning) {
            return;
        }


        // Cancel the timer
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        Dialog finishedDialog = new Dialog(this);
        finishedDialog.setContentView(R.layout.row_view_smacking_game_over);

        Button btnContinue = finishedDialog.findViewById(R.id.btnPlay);
        Button btnQuite = finishedDialog.findViewById(R.id.btnQuit);
        TextView tvText = finishedDialog.findViewById(R.id.tvplay);
        tvText.setText(message);

        btnContinue.setOnClickListener(v -> {
            recreate();
            finishedDialog.dismiss();
        });

        btnQuite.setOnClickListener(v -> finish());

        Window window = finishedDialog.getWindow();
        if (window != null) {
            window.setGravity(Gravity.CENTER);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        finishedDialog.setCanceledOnTouchOutside(false);
        finishedDialog.show();
    }

    private void startTimer() {
        long timerDuration=8000;

        countDownTimer = new CountDownTimer(timerDuration, 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                binding.tvCount.setText(String.format("%02d", seconds));
            }

            public void onFinish() {
                binding.tvCount.setText("00");
                if (isActivityRunning) {
                    gameFinishedDialog("Time's up! Game over.");
                }
            }
        }.start();
    }

    private void shuffleArray(int[] array, int start, int end) {
        int index, temp;
        Random random = new Random();
        for (int i = end; i > start; i--) {
            index = random.nextInt(i - start + 1) + start;
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }


}


    private void rightColors() {
        GradientDrawable shapeDrawableRight1 = new GradientDrawable();
        shapeDrawableRight1.setShape(GradientDrawable.RECTANGLE);
        shapeDrawableRight1.setCornerRadius(getResources().getDimension(R.dimen.card_corner_radius));
        shapeDrawableRight1.setColor(getResources().getColor(color1));
        binding.rrLeftColor1.setBackground(shapeDrawableRight1);

        GradientDrawable shapeDrawableRight2 = new GradientDrawable();
        shapeDrawableRight2.setShape(GradientDrawable.RECTANGLE);
        shapeDrawableRight2.setCornerRadius(getResources().getDimension(R.dimen.card_corner_radius));
        shapeDrawableRight2.setColor(getResources().getColor(color2));
        binding.rrLeftColor2.setBackground(shapeDrawableRight2);

        GradientDrawable shapeDrawableRight3 = new GradientDrawable();
        shapeDrawableRight3.setShape(GradientDrawable.RECTANGLE);
        shapeDrawableRight3.setCornerRadius(getResources().getDimension(R.dimen.card_corner_radius));
        shapeDrawableRight3.setColor(getResources().getColor(color3));
        binding.rrLeftColor3.setBackground(shapeDrawableRight3);

        GradientDrawable shapeDrawableRight4 = new GradientDrawable();
        shapeDrawableRight4.setShape(GradientDrawable.RECTANGLE);
        shapeDrawableRight4.setCornerRadius(getResources().getDimension(R.dimen.card_corner_radius));
        shapeDrawableRight4.setColor(getResources().getColor(color4));
        binding.rrLeftColor4.setBackground(shapeDrawableRight4);

        GradientDrawable shapeDrawableRight5 = new GradientDrawable();
        shapeDrawableRight5.setShape(GradientDrawable.RECTANGLE);
        shapeDrawableRight5.setCornerRadius(getResources().getDimension(R.dimen.card_corner_radius));
        shapeDrawableRight5.setColor(getResources().getColor(color5));
        binding.rrLeftColor5.setBackground(shapeDrawableRight5);

        GradientDrawable shapeDrawableRight6 = new GradientDrawable();
        shapeDrawableRight6.setShape(GradientDrawable.RECTANGLE);
        shapeDrawableRight6.setCornerRadius(getResources().getDimension(R.dimen.card_corner_radius));
        shapeDrawableRight6.setColor(getResources().getColor(color6));
        binding.rrLeftColor6.setBackground(shapeDrawableRight6);
    }
}

