package com.example.findergamesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ActivityOXOPlay extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBArrow;
    private ImageView[][] cells = new ImageView[3][3];
    private int[][] board = new int[3][3];
    private int currentPlayer;
    private TextView tvPlayerOneScore;
    private TextView tvPlayer2Score;
    private int player1Score = 0;
    private int player2Score = 0;
    private int playerOneIconResId; // Resource ID for player one's icon
    private int playerTwoIconResId; // Resource ID for player two's icon

    private LinearLayout layoutTurn;
    private LinearLayout layoutTurn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxoplay);

        ivBArrow = findViewById(R.id.ivArrow);
        layoutTurn = findViewById(R.id.layoutTurn);
        layoutTurn2 = findViewById(R.id.layoutTurn2);

        ivBArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityOXOMenu.class);
                startActivity(intent);
            }
        });

        tvPlayerOneScore = findViewById(R.id.tvPlayerOneScore);
        tvPlayer2Score = findViewById(R.id.tvPlayer2Score);

        // Initialize ImageViews for cells and set onClickListeners
        cells[0][0] = findViewById(R.id.one);
        cells[0][1] = findViewById(R.id.two);
        cells[0][2] = findViewById(R.id.three);
        cells[1][0] = findViewById(R.id.four);
        cells[1][1] = findViewById(R.id.five);
        cells[1][2] = findViewById(R.id.six);
        cells[2][0] = findViewById(R.id.seven);
        cells[2][1] = findViewById(R.id.eight);
        cells[2][2] = findViewById(R.id.nine);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j].setOnClickListener(this);
            }
        }

        // Get player one's and player two's icons from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("OXO_PREFS", MODE_PRIVATE);
        playerOneIconResId = sharedPreferences.getInt("PLAYER_ONE_ICON", R.drawable.ic_tic_tac_cross); // Default to cross icon
        playerTwoIconResId = sharedPreferences.getInt("PLAYER_TWO_ICON", R.drawable.ic_tic_tac_round); // Default to round icon

        // Randomly select the starting player
        currentPlayer = getRandomStartingPlayer();

        // Update the UI to show the starting player's turn
        updateTurnUI();
    }

    @Override
    public void onClick(View v) {
        // Get the clicked cell position
        int row = -1;
        int col = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (v.getId() == cells[i][j].getId()) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        // Check if the cell is empty
        if (board[row][col] == 0) {
            // Update the board with the current player's symbol
            board[row][col] = currentPlayer;
            // Set the image resource based on the current player
            cells[row][col].setImageResource(currentPlayer == 1 ? playerOneIconResId : playerTwoIconResId);

            // Check for a win or draw
            if (checkWin(currentPlayer)) {
                // Increment the score of the current player
                if (currentPlayer == 1) {
                    player1Score++;
                    tvPlayerOneScore.setText(String.valueOf(player1Score));
                } else {
                    player2Score++;
                    tvPlayer2Score.setText(String.valueOf(player2Score));
                }
                gameFinishedDialog("Player " + currentPlayer + " wins!");
            } else if (isBoardFull()) {
                Toast.makeText(this, "It's a draw!", Toast.LENGTH_SHORT).show();
                gameFinishedDialog("It's a draw!");
            } else {
                // Switch to the other player's turn
                currentPlayer = currentPlayer == 1 ? 2 : 1;
                // Update the UI to reflect the current player's turn
                updateTurnUI();
            }
        } else {
            Toast.makeText(this, "This cell is already occupied!", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to randomly select the starting player
    private int getRandomStartingPlayer() {
        Random random = new Random();
        return random.nextInt(2) + 1; // Generate a random number (1 or 2)
    }

    // Method to update the UI to reflect the current player's turn
    private void updateTurnUI() {
        layoutTurn.setVisibility(currentPlayer == 1 ? View.VISIBLE : View.GONE);
        layoutTurn2.setVisibility(currentPlayer == 2 ? View.VISIBLE : View.GONE);
    }

    // Method to check for a win
    private boolean checkWin(int player) {
        // Check rows, columns, and diagonals for the same player's symbols
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true; // Row win
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true; // Column win
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true; // Diagonal win
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true; // Diagonal win
        }
        return false;
    }

    // Method to check if the board is full (i.e., it's a draw)
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return false; // Found an empty cell
                }
            }
        }
        return true; // Board is full
    }

    // Method to reset the game
    private void resetGame() {
        // Reset the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
                cells[i][j].setImageResource(0);
            }
        }
        // Randomly select the starting player again
        currentPlayer = getRandomStartingPlayer();
        // Update the UI to show the starting player's turn
        updateTurnUI();
    }

    private void gameFinishedDialog(String Message) {
        // Create a dialog instance
        Dialog finishedDialog = new Dialog(this);

        // Set the custom layout for the dialog
        finishedDialog.setContentView(R.layout.wining_dialog);

        Button btnContinue = finishedDialog.findViewById(R.id.btnContinue);
        Button btnQuite = finishedDialog.findViewById(R.id.btnQuit);
        TextView tvText = finishedDialog.findViewById(R.id.tvWin);
        tvText.setText(Message);

        btnContinue.setOnClickListener(v -> {
            resetGame();
            finishedDialog.dismiss();
        });

        btnQuite.setOnClickListener(v -> {
            finish();
        });

        // Configure the dialog window to be displayed at the bottom
        Window window = finishedDialog.getWindow();
        if (window != null) {
            window.setGravity(Gravity.CENTER);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        // Set to prevent dismissing the dialog when touched outside of it
        finishedDialog.setCanceledOnTouchOutside(false);

        // Show the dialog
        finishedDialog.show();
    }
}
