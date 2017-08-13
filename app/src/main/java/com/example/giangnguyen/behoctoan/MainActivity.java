package com.example.giangnguyen.behoctoan;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    final String DATA = "data";
    final String HIGHT_SCORE = "hight score";
    int a;
    int b;
    int c;
    int score = 0;
    int curr = 100;


    TextView Txt_score, cauhoi;
    ImageButton True, False;
    ProgressBar progressBar;
    RelativeLayout bg;


    CountDownTimer countDownTimer = new CountDownTimer(1800, 10) {
        @Override
        public void onTick(long l) {
            if (curr > 0) {
                curr--;
                progressBar.setProgress(curr);
            }
        }

        @Override
        public void onFinish() {
            GameoverDialog();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer songtrue = MediaPlayer.create(this, R.raw.coin);
        final MediaPlayer songfalse = MediaPlayer.create(this, R.raw.game_over);
        Imag();
        randomColor();
        PlayDialog();


        True.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (a + b == c) {
                    score++;
                    Txt_score.setText("" + score);
                    countDownTimer.cancel();
                    load();

                } else {
                    GameoverDialog();
                }
            }
        });

        False.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (a + b != c) {

                    score++;
                    Txt_score.setText("" + score);
                    countDownTimer.cancel();

                    load();

                } else {
                    GameoverDialog();
                }

            }
        });

    }

    private void PlayDialog() {

        final Dialog PlayDialog = new Dialog(MainActivity.this);
        PlayDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        PlayDialog.setContentView(R.layout.playlayout);
        PlayDialog.setCancelable(false);
        ImageButton Play = (ImageButton) PlayDialog.findViewById(R.id.playImg1);
        PlayDialog.show();

        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load();
                randomColor();
                PlayDialog.dismiss();
            }
        });
    }

    private void GameoverDialog() {
        countDownTimer.cancel();
        addData();
        final Dialog GameOverDialog = new Dialog(MainActivity.this);
        GameOverDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        GameOverDialog.setContentView(R.layout.game_over_dialog);
        TextView scoree = (TextView) GameOverDialog.findViewById(R.id.scoree);
        scoree.setText("" + score);
        ImageButton Play = (ImageButton) GameOverDialog.findViewById(R.id.playImg2);
        GameOverDialog.setCancelable(false);
        GameOverDialog.show();

        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                score = 0;
                Txt_score.setText("" + score);
                load();
                randomColor();
                GameOverDialog.dismiss();
            }
        });

    }

    private void load() {
        curr = 100;
        Random random = new Random();
        a = random.nextInt(10) + 1;
        b = random.nextInt(10) + 1;
        int temp = random.nextInt(5) + 1;

        if (temp % 2 == 0) c = a + b;
        else c = temp + a + b;

        progressBar.setProgress(curr);
        countDownTimer.start();

        cauhoi.setText(a + " + " + b + " = " + c);
    }

    private void Imag() {
        Txt_score = (TextView) findViewById(R.id.score);
        cauhoi = (TextView) findViewById(R.id.cauhoi);
        True = (ImageButton) findViewById(R.id.True);
        False = (ImageButton) findViewById(R.id.False);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        bg = (RelativeLayout) findViewById(R.id.bacground);
    }

    public void randomColor() {
        int[] androidColors = getResources().getIntArray(R.array.androidcolors);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        bg.setBackgroundColor(randomAndroidColor);
    }

    private int readData() {
        SharedPreferences sharedPreferences = getSharedPreferences(DATA, Context.MODE_PRIVATE);
        int score = sharedPreferences.getInt(HIGHT_SCORE, 20);
        return score;
    }

    private void addData() {
        //if (score > readData()) {
            SharedPreferences sharedPreferences = getSharedPreferences(DATA, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putInt(HIGHT_SCORE, score);
            editor.apply();
        //}


    }
}
