package com.example.android.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class second extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[5][5];

    private boolean player1Playing = true;

    private int roundCount;

    private int p1Points;
    private int p2Points;

    private TextView p1Score;
    private TextView p2Score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        p1Score = findViewById(R.id.player1_score);
        p2Score = findViewById(R.id.player2_score);

//        buttons
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

//        reset button
        Button buttonReset = findViewById(R.id.reset_button);
        buttonReset.setOnClickListener(new View.OnClickListener() {

            //            reset button OnClickListener
            @Override
            public void onClick(View v) {
                resetGame();

            }
        });

//      switch button
        Button buttonSwitch = (Button) findViewById(R.id.switch_board_to3_btn);
        buttonSwitch.setOnClickListener(new View.OnClickListener() {

            //            switch button OnClickListener
            @Override
            public void onClick(View v) {
                switchBoard();

            }
        });

    }


    //    board OnClickListener
    @Override
    public void onClick(View v) {
//      check if button is empty
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

//      check whose turn it is and if player1 set button text to X and O if player2
        if (player1Playing) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

//      add one to number ot rounds
        roundCount++;

//      check which player has won
        if (checkForWin()) {
            if (player1Playing) {
                p1Wins();
            } else {
                p2Wins();
            }
//        draw if none has won
        } else if (roundCount == 25) {
            draw();
//        switch player turns
        } else {
            player1Playing = !player1Playing;
        }

    }

    //  method to check board for a win
    private boolean checkForWin() {

        String[][] field = new String[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
//      check rows
        for (int i = 0; i < 5; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && field[i][0].equals(field[i][3])
                    && field[i][0].equals(field[i][4])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
//      check columns
        for (int i = 0; i < 5; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && field[0][i].equals(field[3][i])
                    && field[0][i].equals(field[4][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

//      check diagonals
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && field[0][0].equals(field[3][3])
                && field[0][0].equals(field[4][4])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][4].equals(field[1][3])
                && field[0][4].equals(field[2][2])
                && field[0][4].equals(field[3][1])
                && field[0][4].equals(field[4][0])
                && !field[0][4].equals("")) {
            return true;
        }

        return false;

    }


    private void p1Wins() {
        p1Points++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        updateScores();
        resetBoard();
    }

    private void p2Wins() {
        p2Points++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        updateScores();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    //  show player points
    private void updateScores() {
        p1Score.setText(Integer.toString(p1Points));
        p2Score.setText(Integer.toString(p2Points));
    }

    private void resetBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Playing = true;

    }

    private void resetGame() {
        p1Points = 0;
        p2Points = 0;
        updateScores();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("p1Points", p1Points);
        outState.putInt("p2Points", p2Points);
        outState.putBoolean("player1Playing", player1Playing);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        p1Points = savedInstanceState.getByte("p1Points");
        p2Points = savedInstanceState.getByte("p2Points");
        player1Playing = savedInstanceState.getBoolean("player1Playing");

    }


    private void switchBoard() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
