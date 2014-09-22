package com.example.channing.rockpaperscissors;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


public class RockPaperScissors extends Activity implements View.OnClickListener {

    public enum Option {
        ROCK, PAPER, SCISSORS
    }

    public enum Result {
        WIN, LOSS, DRAW
    }

    private Option userSelection;
    private Result gameResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock_paper_scissors);

        Button buttonRock = (Button) findViewById(R.id.buttonRock);
        Button buttonPaper = (Button) findViewById(R.id.buttonPaper);
        Button buttonScissors = (Button) findViewById(R.id.buttonScissors);
        ImageButton buttonHome = (ImageButton) findViewById(R.id.imageButtonHome);

        //Set click listening event for all buttons.
        buttonRock.setOnClickListener(this);
        buttonPaper.setOnClickListener(this);
        buttonScissors.setOnClickListener(this);
        buttonHome.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.rock_paper_scissors, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onClick(View v) {
        ImageView imageView = (ImageView) findViewById(R.id.imageViewAnswerUser);
        boolean play = true;

        switch (v.getId()) {
            case R.id.buttonRock:
                userSelection = Option.ROCK;
                imageView.setImageResource(R.drawable.rocksmall);
                break;
            case R.id.buttonPaper:
                userSelection = Option.PAPER;
                imageView.setImageResource(R.drawable.papersmall);
                break;
            case R.id.buttonScissors:
                userSelection = Option.SCISSORS;
                imageView.setImageResource(R.drawable.scissorssmall);
                break;
            case R.id.imageButtonHome:
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                play = false;
                break;
        }
        if(play){
            play();
            showResults();
        }
    }

    private void showResults(){
        AlertDialog.Builder builder = new AlertDialog.Builder(RockPaperScissors.this);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
       @Override
       public void onClick(DialogInterface dialog, int which){
           //do nothing
       }
        });

        //sets right messsage in regards to the result of the game
        if(gameResult == Result.LOSS){
            builder.setMessage("You Lose!");
        }
        else if(gameResult == Result.WIN){
            builder.setMessage("You Win!");
        }
        else if(gameResult == Result.DRAW){
            builder.setMessage("It's a draw, play again!");
        }

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void play(){
        //Generates a random play
        int rand = ((int)(Math.random() * 10)) % 3;
        Option androidSelection = null;
        ImageView imageView = (ImageView) findViewById(R.id.ImageViewAnswerAndroid);

        //Set the right image according to random selection
        switch(rand) {
            case 0:
                androidSelection = Option.ROCK;
                imageView.setImageResource(R.drawable.rocksmall);
                break;
            case 1:
                androidSelection = Option.PAPER;
                imageView.setImageResource(R.drawable.papersmall);
                break;
            case 2:
                androidSelection = Option.SCISSORS;
                imageView.setImageResource(R.drawable.scissorssmall);
                break;
        }

        //Determine result according to android and user selection
        if(androidSelection == userSelection) {
            gameResult = Result.DRAW;
        }
        else if(androidSelection == Option.ROCK && userSelection == Option.SCISSORS){
            gameResult = Result.LOSS;
        }
        else if(androidSelection == Option.SCISSORS && userSelection == Option.PAPER){
            gameResult = Result.LOSS;
        }
        else if(androidSelection == Option.PAPER && userSelection == Option.ROCK){
            gameResult = Result.LOSS;
        }
        else {
            gameResult = Result.WIN;
        }
    }


}
