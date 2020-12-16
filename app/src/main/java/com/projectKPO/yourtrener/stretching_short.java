package com.projectKPO.yourtrener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.os.CountDownTimer;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class stretching_short extends AppCompatActivity {

    WebView webView;
    private final String TR_NAME = "Короткая разминка";
    private long backPressedTime;
    private static final long START_TIME_IN_MILLIS = 1000;
    private TextView mTextViewCountDown;
    private TextView mEx;
    private Button mButtonStartPause;
    private ImageButton mButtonExit;
    private TextView mExInfoButton;
    private TextView mExitInfo;
    private CountDownTimer mCountDownTimer;
    private TextView mExInfoText;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private int i=1;
    private int end_i=10;
    private String[] exName;
    private String[] exInfo;
    private String curtime;
    Dialog dialog_info;
    public static final String BD_Name = "BD";
    SharedPreferences mBD;
    public String[] STAT = {"D_Hash","Duration","Tr_Name"};
    @SuppressLint("SetTextI18n")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        curtime = formatter.format(date);
        exName=getResources().getStringArray(R.array.s_s_ex_name);
        exInfo=getResources().getStringArray(R.array.s_s_ex_info);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stretching_short);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mEx= findViewById(R.id.Ex);
        mExInfoButton = findViewById(R.id.exinfobutton);

        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonExit = findViewById(R.id.imageButtonBack);
        mButtonExit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(backPressedTime + 2000> System.currentTimeMillis()){
                    Intent intent = new Intent(stretching_short.this, MenuLevels.class);
                    startActivity(intent); finish();
                    return;
                }else{
                    Toast.makeText(getBaseContext(), "Нажмите ещё раз, чтобы отменить тренировку",Toast.LENGTH_SHORT).show();
                }
                backPressedTime = System.currentTimeMillis();
            }
        });
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
                dialog_info.show();
                mExitInfo= dialog_info.findViewById(R.id.X);
                mExitInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_info.dismiss();
                    }
                });
            }
        });


        updateCountDownText();
        webView = (WebView) findViewById(R.id.s_s);
        webView.loadUrl("file:///android_asset/s_s_1.gif");
        dialog_info= new Dialog(stretching_short.this);
        dialog_info.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_info.setContentView(R.layout.ex_info);
        dialog_info.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_info.setCancelable(false);
        mEx.setText(exName[i]);
        mExInfoText=dialog_info.findViewById(R.id.ex_info_text);
        mExInfoText.setText(exInfo[i]);

    }

    private void startTimer() {
        mBD = this.getSharedPreferences(BD_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor mBDeditor = mBD.edit();
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                if (i<end_i)
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
                mEx.setText(exName[i]);
                mExInfoText.setText(exInfo[i]);
                }
                else{
                    mTimerRunning = false;
                    mButtonStartPause.setText("старт");
                    mButtonStartPause.setVisibility(View.INVISIBLE);
                    mBDeditor.putString(STAT[0],curtime);
                    mBDeditor.putString(STAT[1],Long.toString(START_TIME_IN_MILLIS/1000*end_i));
                    mBDeditor.putString(STAT[2],TR_NAME);
                    mBDeditor.apply();
                    Intent intent = new Intent(stretching_short.this, MenuLevels.class);
                    startActivity(intent); finish();
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
            Intent intent = new Intent(stretching_short.this, MenuLevels.class);
            startActivity(intent); finish();
            return;
        }else{
            Toast.makeText(getBaseContext(), "Нажмите ещё раз, чтобы отменить тренировку",Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }



}