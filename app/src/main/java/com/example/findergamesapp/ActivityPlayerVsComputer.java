package com.example.findergamesapp;

import android.app.Dialog;
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

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ActivityPlayerVsComputer extends AppCompatActivity implements View.OnClickListener {

    private ImageView[][] cells = new ImageView[3][3];
    private int[][] board = new int[3][3];
    private int currentPlayer = 1;
    private int player1Score = 0;
    private int player2Score = 0;
    private TextView playerOneScore;
    private TextView playerTwoScore;
    private LinearLayout layoutTurn;
    private LinearLayout layoutTurn2;

    ImageView ivBack;
    private boolean playerTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_vs_computer);

        playerOneScore = findViewById(R.id.tvPlayerOneScore);
        playerTwoScore = findViewById(R.id.tvPlayer2Score);
        layoutTurn = findViewById(R.id.layoutTurn);
        layoutTurn2 = findViewById(R.id.layoutTurn2);
        ivBack = findViewById(R.id.ivArrow);

        ivBack.setOnClickListener(v -> {
            finish();
        });

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

        startGame();
    }

    private void startGame() {
        // Randomly determine who starts first
        currentPlayer = new Random().nextInt(2) + 1; // Generates a random number either 1 or 2

        // Display the appropriate turn layout based on the starting player
        if (currentPlayer == 1) {
            layoutTurn.setVisibility(View.VISIBLE);
            layoutTurn2.setVisibility(View.GONE);
        } else {
            layoutTurn.setVisibility(View.GONE);
            layoutTurn2.setVisibility(View.VISIBLE);
            // Computer starts first, make its move
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    computerMove();
                    // After the computer's move, set playerTurn to true
                    playerTurn = true;
                }
            }, 600);
        }
    }


    @Override
    public void onClick(View v) {
        // Check if it's the player's turn
        if (playerTurn) {
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

            if (board[row][col] == 0) {
                board[row][col] = 1; // Player's move
                cells[row][col].setImageResource(R.drawable.ic_tic_tac_cross);

                // Check for a win or draw
                if (checkWin(1)) {
                    // Increment the score of the player
                    player1Score++;
                    playerOneScore.setText(String.valueOf(player1Score));
                    gameFinishedDialog("Player 1 wins!");
                } else if (isBoardFull()) {
                    gameFinishedDialog("It's a draw!");
                } else {
                    // Computer's turn
                    playerTurn = false;
                    layoutTurn2.setVisibility(View.VISIBLE);
                    layoutTurn.setVisibility(View.GONE);
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            computerMove();
                            playerTurn = true;
                            layoutTurn.setVisibility(View.VISIBLE);
                            layoutTurn2.setVisibility(View.GONE);
                        }
                    }, 1500);
                }
            } else {
                Toast.makeText(this, "This cell is already occupied!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Wait for computer's turn!", Toast.LENGTH_SHORT).show();
        }
    }

    private void computerMove() {
        if (checkForWinningMove(2)) {
            gameFinishedDialog("Computer Win!");
            player2Score++;
            playerTwoScore.setText(String.valueOf(player2Score));
            return;
        }

        if (checkForBlockingMove(1)) {
            // After making a blocking move, set playerTurn to true
            playerTurn = true;
            // Toggle the visibility of the layout
            layoutTurn.setVisibility(View.VISIBLE);
            layoutTurn2.setVisibility(View.GONE);
            return;
        }

        makeRandomMove();

        // After the computer completes its move, set playerTurn to true
        playerTurn = true;
        // Toggle the visibility of the layout
        layoutTurn.setVisibility(View.VISIBLE);
        layoutTurn2.setVisibility(View.GONE);

        // Check for a draw after the computer's move
        if (isBoardFull()) {
            gameFinishedDialog("It's a draw!");
        }
    }



    // Method to check for a move that can win the game for the given player
    private boolean checkForWinningMove(int player) {
        // Check all rows, columns, and diagonals for a winning move
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    // Try making a move at this position and check for a win
                    board[i][j] = player;
                    if (checkWin(player)) {
                        // Found a winning move, make the move
                        cells[i][j].setImageResource(R.drawable.ic_tic_tac_round);
                        return true;
                    }
                    // Undo the move
                    board[i][j] = 0;
                }
            }
        }
        return false; // No winning move found
    }

    // Method to check for a move that can prevent the player from winning
    private boolean checkForBlockingMove(int opponent) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    // Try making a move at this position and check if it prevents the player from winning
                    board[i][j] = opponent;
                    if (checkWin(opponent)) {
                        // Found a blocking move make the move to prevent the opponent from winning
                        board[i][j] = 2;
                        cells[i][j].setImageResource(R.drawable.ic_tic_tac_round);
                        return true;
                    }
                    board[i][j] = 0;
                }
            }
        }
        return false;
    }

    // Method to make a random move
    private void makeRandomMove() {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (board[row][col] != 0);

        // Make the random move
        board[row][col] = 2;
        cells[row][col].setImageResource(R.drawable.ic_tic_tac_round);
    }

    // Method to check for a win
    private boolean checkWin(int player) {
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

    // Method to check the if game is draw
    // Method to check if the game is a draw
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return false; // Found an empty cell, so the board is not full
                }
            }
        }
        return true; // No empty cells found, so the board is full
    }


    // Method to show the game finished dialog
    private void gameFinishedDialog(String message) {
        Dialog finishedDialog = new Dialog(this);
        finishedDialog.setContentView(R.layout.wining_dialog);
        TextView tvMessage = finishedDialog.findViewById(R.id.tvWin);
        Button btnContinue = finishedDialog.findViewById(R.id.btnContinue);
        Button btnQuit = finishedDialog.findViewById(R.id.btnQuit);

        tvMessage.setText(message);
        btnContinue.setOnClickListener(v -> {
            resetGame();
            finishedDialog.dismiss();
        });

        btnQuit.setOnClickListener(v -> {
            finish();
        });

        Window window = finishedDialog.getWindow();
        if (window != null) {
            window.setGravity(Gravity.CENTER);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        finishedDialog.setCanceledOnTouchOutside(false);

        finishedDialog.show();
    }

    // Method to reset the game
    private void resetGame() {
        // Clear the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
                cells[i][j].setImageResource(0);
            }
        }
        // Start a new game
        startGame();
    }


}