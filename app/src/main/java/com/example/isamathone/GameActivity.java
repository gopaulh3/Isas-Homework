package com.example.isamathone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.text.DecimalFormat;


public class GameActivity extends AppCompatActivity {

    Button goButton, button0, button1, button2, button3, playAgainButton, goBackButton;
    TextView resultTextView, scoreTextView, sumTextView, timerTextView, bestScoreTextView;
    int locationOfCorrectAnswer, score = 0, numberOfQuestions = 0, categoryChosen = 0;
    GridLayout gridLayout;
    ScoreStorage scoreStorage;
    SharedPreferences sp;
    boolean isEasy = true;
    ConstraintLayout gameLayout;
    String[] strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initStuff();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent objectIntent = new Intent(getApplicationContext(),MainActivity.class);
        objectIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getApplicationContext().startActivity(objectIntent);
    }

    private void initStuff() {
        getSupportActionBar().setDisplayOptions(androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_title_layout);
        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        bestScoreTextView = findViewById(R.id.bestScoreTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        goBackButton = findViewById(R.id.goBackButton);
        gameLayout = findViewById(R.id.gameLayout);
        goButton = findViewById(R.id.goButton);
        gridLayout = findViewById(R.id.gridLayout2);
        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
        strings = getIntent().getStringArrayExtra("Info");
        sp = getApplicationContext().getSharedPreferences("GameScoreData", Context.MODE_PRIVATE);

        if (strings[0].equals("Hard")) isEasy = false;

        switch (strings[1]) {
            case "Addition": categoryChosen = 1; break;
            case "Multiplication": categoryChosen = 2; break;
            case "Subtraction": categoryChosen = 3; break;
            case "Division": categoryChosen = 4; break;
            case "Rounding": categoryChosen = sortOut(5); break;
            case "Algebra": categoryChosen = sortOut(6); break;
            default: categoryChosen = 0;
        }
        scoreStorage = new ScoreStorage(sp,categoryChosen,bestScoreTextView,isEasy);
        scoreStorage.initialData();
    }

    private int sortOut(int i) {
        sumTextView.setTextSize(25);
        button0.setTextSize(30);
        button1.setTextSize(30);
        button2.setTextSize(30);
        button3.setTextSize(30);
        return i;
    }

    public void goBack(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getApplicationContext().startActivity(intent);
    }

    public void playAgain(View view) {
        gridLayout.setVisibility(View.VISIBLE);
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("20s");
        scoreTextView.setText("0.00");
        System.out.println("Here it is  => "+scoreTextView.getText().toString());
        newQuestion(categoryChosen);
        playAgainButton.setVisibility(View.INVISIBLE);
        goBackButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");

        new CountDownTimer(20000, 1000) {

            @Override
            public void onTick(long l) {
                timerTextView.setText(l / 1000 + "s");
            }

            @Override
            public void onFinish() {
                scoreStorage.checkIfUpdateScores(Float.parseFloat(scoreTextView.getText().toString()));
                scoreStorage.initialData();
                playAgainButton.setVisibility(View.VISIBLE);
                goBackButton.setVisibility(View.VISIBLE);
                gridLayout.setVisibility(View.GONE);
            }
        }.start();
    }

    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            score++;
        }
        numberOfQuestions++;
        double s = ((score * 100) / (double) (numberOfQuestions + 1));
        scoreTextView.setText(new DecimalFormat("#.00").format(s));
        resultTextView.setText(score + "/" + numberOfQuestions);
        newQuestion(categoryChosen);
    }

    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }

    public void newQuestion(int category) {
        if (category == 6) {
            Algebra algebra = new Algebra(button0, button1, button2, button3, sumTextView);
            locationOfCorrectAnswer = algebra.whichOps(isEasy);
        } else {
            Mathematics mathematics = new Mathematics(button0, button1, button2, button3, sumTextView);
            locationOfCorrectAnswer = mathematics.general(category, isEasy);
        }
    }
}