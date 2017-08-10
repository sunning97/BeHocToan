package com.example.giangnguyen.behoctoan;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;



public class MainActivity extends AppCompatActivity {
    int a;
    int b;
    int c;
    int diem = 0;
    TextView score,cauhoi;
    ImageButton True, False;
    CountDownTimer countDownTimer = new CountDownTimer(5000,1000) {
        @Override
        public void onTick(long l) {
            score.setText("" + l/1000);
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

        Imag();
        PlayDialog();

        True.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (a + b == c) {
                    load();
                    countDownTimer.start();

                }
                else GameoverDialog();
            }
        });

        False.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (a + b != c) {

                    load();
                    countDownTimer.start();

                }
                else GameoverDialog();
            }
        });

    }

    private void PlayDialog()
    {
        final Dialog PlayDialog = new Dialog(MainActivity.this);
        PlayDialog.setContentView(R.layout.playlayout);
        PlayDialog.setCancelable(false);
        ImageButton Play = (ImageButton) PlayDialog.findViewById(R.id.playImg1) ;
        PlayDialog.show();

        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load();
                countDownTimer.start();
                PlayDialog.dismiss();
            }
        });
    }

    private void GameoverDialog()
    {
        final Dialog GameOverDialog = new Dialog(MainActivity.this);
        GameOverDialog.setContentView(R.layout.game_over_dialog);
        ImageButton Play = (ImageButton) GameOverDialog.findViewById(R.id.playImg2) ;
        GameOverDialog.setCancelable(false);
        GameOverDialog.show();
        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load();
                countDownTimer.start();
                GameOverDialog.dismiss();
            }
        });

    }
    private void load()
    {
        Random random = new Random();
        a = random.nextInt(10) + 1;
        b = random.nextInt(10) + 1;
        int temp = random.nextInt(5)+1 ;

        if(temp%2 ==0)c = a + b;
        else c = temp+a+b;

        cauhoi.setText(a + " + " + b + " = " + c);
    }

    private  void Imag()
    {
        score = (TextView) findViewById(R.id.score);
        cauhoi = (TextView) findViewById(R.id.cauhoi);
        True = (ImageButton) findViewById(R.id.True);
        False = (ImageButton) findViewById(R.id.False);
    }


}
