package com.example.giangnguyen.behoctoan;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

import static android.R.attr.max;

public class MainActivity extends AppCompatActivity {
    int a = 0;
    int b = 0;
    int c = 0;
    int diem = 0;
    final Random r = new Random();
    TextView score,cauhoi;
    Button dung, sai, play;
    ProgressBar time;


    CountDownTimer timer = new CountDownTimer(60000,60) {
        @Override
        public void onTick(long l) {
            time.setProgress(time.getProgress()-2);
            if(time.getProgress()==0)
            {
                dung.setEnabled(false);
                sai.setEnabled(false);
                play.setEnabled(true);
                cauhoi.setText("Bạn Thua Rồi"+"\n"+"Điểm số: "+diem);

            }
        }

        @Override
        public void onFinish() {

        }
    };
    CountDownTimer thongbao = new CountDownTimer(2000,500) {
        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {
            cauhoi.setText("Bấm \"Chơi\" để bắt đầu");
            time.setProgress(0);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        time =(ProgressBar) findViewById(R.id.timer) ;
        score = (TextView) findViewById(R.id.score);
        cauhoi = (TextView) findViewById(R.id.cauhoi);
        dung = (Button) findViewById(R.id.dung);
        sai = (Button) findViewById(R.id.sai);
        play = (Button) findViewById(R.id.play);

        dung.setEnabled(false);
        sai.setEnabled(false);

        time.setProgress(max);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                diem = 0;
                dung.setEnabled(true);
                sai.setEnabled(true);
                play.setEnabled(false);
                time.setProgress(max);
                score.setText("" + diem);
                timer.start();

                a = r.nextInt(10) + 1;
                b = r.nextInt(10) + 1;

                c = r.nextInt(3) + a + b;

                cauhoi.setText(a + " + " + b + " = " + c);
            }
        });

        dung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                play.setEnabled(false);
                if (a + b == c) {
                    diem++;
                    score.setText("" + diem);

                    a = r.nextInt(10) + 1;
                    b = r.nextInt(10) + 1;

                    c = r.nextInt(3) + a + b;

                    cauhoi.setText(a + " + " + b + " = " + c);
                } else {
                    dung.setEnabled(false);
                    sai.setEnabled(false);
                    play.setEnabled(true);

                    timer.cancel();
                    cauhoi.setText("Bạn Sai Rồi");
                    thongbao.start();
                    diem = 0;
                }
            }
        });

        sai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                play.setEnabled(false);
                if (a + b != c) {
                    diem++;
                    score.setText("" + diem);


                    a = r.nextInt(10) + 1;
                    b = r.nextInt(10) + 1;

                    c = r.nextInt(3) + a + b;

                    cauhoi.setText(a + " + " + b + " = " + c);
                } else {
                    dung.setEnabled(false);
                    sai.setEnabled(false);
                    play.setEnabled(true);
                    timer.cancel();
                    cauhoi.setText("Bạn Sai Rồi");
                    thongbao.start();
                    diem = 0;
                }
            }
        });

    }
}
