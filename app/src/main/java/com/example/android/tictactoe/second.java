package com.example.android.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class second extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button buttonSwitch = (Button) findViewById(R.id.switch_board_to3_btn);
        buttonSwitch.setOnClickListener (new View.OnClickListener() {

            //            switch button OnClickListener
            @Override
            public void onClick(View v) {
                switchBoard();

            }
        });

    }

    private void switchBoard(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
