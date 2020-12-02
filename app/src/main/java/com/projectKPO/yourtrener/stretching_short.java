package com.projectKPO.yourtrener;

import android.app.Dialog;
import android.content.Intent;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.os.CountDownTimer;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Locale;

public class stretching_short extends AppCompatActivity {

    WebView webView;
    private long backPressedTime;
    private static final long START_TIME_IN_MILLIS = 3000;
    private TextView mTextViewCountDown;
    private TextView mEx;
    private Button mButtonStartPause;
    private Button mButtonExit;
    private TextView mExInfoButton;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private int i=1;
    private String[] A={"","ПОВОРОТЫ ГОЛОВЫ","ВРАЩЕНИЕ ЗАПЯСТИЙ","ВРАЩЕНИЕ ЛОКТЕЙ"," ВРАЩЕНИЕ ПЛЕЧАМИ","ВРАЩЕНИЕ РУКАМИ","НАКЛОНЫ КОРПУСА","ВРАЩЕНИЕ ТАЗОМ","ВРАЩЕНИЕ БЕДРАМИ","ВРАЩЕНИЕ КОЛЕНЯМИ","ВРАЩЕНИЕ СТОПАМИ"};
    Dialog dialog_info;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stretching_short);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mEx= findViewById(R.id.Ex);
        mExInfoButton = findViewById(R.id.exinfobutton);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonExit = findViewById(R.id.button_exit);
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });
        mExInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        updateCountDownText();
        webView = (WebView) findViewById(R.id.s_s);
        webView.loadUrl("file:///android_asset/s_s_1.gif");
        dialog_info= new Dialog(this);
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                if (i<10)
                {
                    i+=1;
                mTimerRunning = false;
                mButtonStartPause.setText("старт");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mTimeLeftInMillis = START_TIME_IN_MILLIS;
                updateCountDownText();
                mButtonStartPause.setVisibility(View.VISIBLE);
                webView.clearCache(true);
                webView.loadUrl("file:///android_asset/s_s_"+Integer.toString(i)+".gif");
                mEx.setText(A[i]);
                }
                else{
                    mTimerRunning = false;
                    mButtonStartPause.setText("старт");
                    mButtonStartPause.setVisibility(View.INVISIBLE);
                }
            }
        }.start();
        mTimerRunning = true;
        mButtonStartPause.setText("пауза");
    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("старт");
    }
    private void updateCountDownText() {
        int seconds = (int) (mTimeLeftInMillis / 1000) ;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d", seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }
    @Override
    public void onBackPressed(){
        if(backPressedTime + 2000> System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else{
            Toast.makeText(getBaseContext(), "Нажмите ещё раз, чтобы отменить тренировку",Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }



}