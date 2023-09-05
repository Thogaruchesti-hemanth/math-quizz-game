package com.example.mathquizgame;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout firstConstraintLayout, secondConstraintLayout;
    TextView questionTextView ,scoreTextView,answerTextView,timerTextView;
    int correctAnswerLocation;
    ArrayList<Integer> answers ;
    Button button0,button1,button2,button3,playAgainButton;
    int score ,numOfQuestions;

    public void onStartClick(View view){

        firstConstraintLayout.setVisibility(View.INVISIBLE);
        secondConstraintLayout.setVisibility(View.VISIBLE);
        play();


    }

    private void play() {

        //set Question
        getNewQuestion();

        //set score
        score = 0;
        numOfQuestions = 0;
        setScore();

        //set empty value in answer textView
        answerTextView.setText("");


        //set timer
        setTimer();

    }

    private void setTimer() {


        new CountDownTimer(30000,1000){

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(millisUntilFinished / 1000 +"s");


            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {

                answerTextView.setText("GAME OVER!");
                playAgainButton.setVisibility(View.VISIBLE);

            }


        }.start();
    }

    @SuppressLint("SetTextI18n")
    private void setScore() {

        scoreTextView.setText(score +"/"+ numOfQuestions);

    }

    @SuppressLint("SetTextI18n")
    private void getNewQuestion() {

        Random random = new Random();
        int a = random.nextInt(31);
        int b = random.nextInt(31);

        questionTextView.setText(a + "+" + b + "=?");
        correctAnswerLocation = random.nextInt(4);
        answers.clear();

        for (int i=0;i<4;i++){

            if(i==correctAnswerLocation){
                answers.add(a+b);
            }
            else {
                int wrongAnswer = random.nextInt(61);
                while(wrongAnswer==a+b){
                    wrongAnswer = random.nextInt(61);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));



    }

    @SuppressLint("SetTextI18n")
    public void onOptionsClick(View view)
    {

        if (Integer.toString(correctAnswerLocation).equals(view.getTag().toString())){

            score++;
            answerTextView.setText("Correct!");

        }else {

            answerTextView.setText("Wrong!");
        }
        numOfQuestions++;
        setScore();
        getNewQuestion();


    }
    public void onPlayAgainClick(View view){

        playAgainButton.setVisibility(View.INVISIBLE);
        play();

    }

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        firstConstraintLayout = findViewById(R.id.FirstconstraintLayout);
        secondConstraintLayout = findViewById(R.id.secondConstraintLayout);
        questionTextView = findViewById(R.id.questionTextView);
        answers = new ArrayList<>();
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        scoreTextView = findViewById(R.id.scoreTextView);
        answerTextView = findViewById(R.id.answerTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);


    }
}