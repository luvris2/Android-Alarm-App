package com.luvris2.alarmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    CountDownTimer timer;
    TextView textTimer;
    ImageView img;
    MediaPlayer mp;
    int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTime = findViewById(R.id.editTime);
        Button btnStart = findViewById(R.id.btnStart);
        Button btnCancel = findViewById(R.id.btnCancel);
        textTimer = findViewById(R.id.textTimer);
        img = findViewById(R.id.imageView);
        mp = MediaPlayer.create(this, R.raw.alarm);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = Integer.parseInt(editTime.getText().toString()) * 1000 ;
                timerFunction(time);
                timer.start();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                textTimer.setText("남은 시간 : " + time/1000+"");
            }
        });
    }

    public void timerFunction(int time) {
        timer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long l) {
                long remain = l / 1000;
                textTimer.setText("남은 시간 : " + remain);
            }
            @Override
            public void onFinish() {
                textTimer.setText("시간 종료");
                YoYo.with(Techniques.Tada).duration(700).repeat(5).playOn(img);
                mp.start();
            }
        };
    }
}