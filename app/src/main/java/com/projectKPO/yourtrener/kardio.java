package com.projectKPO.yourtrener;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class kardio extends AppCompatActivity {

    WebView webView;
    private long backPressedTime;
    private static final long START_TIME_IN_MILLIS = 30000;
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
    Dialog dialog_info;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        exName=getResources().getStringArray(R.array.k_ex_name);
        exInfo=getResources().getStringArray(R.array.k_ex_info);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kardio);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mEx= findViewById(R.id.Ex);
        mExInfoButton = findViewById(R.id.exinfobutton);

        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonExit = findViewById(R.id.imageButtonBack);
        mButtonExit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(backPressedTime + 2000> System.currentTimeMillis()){
                    Intent intent = new Intent(kardio.this, MenuLevels.class);
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
        webView = (WebView) findViewById(R.id.k);
        webView.loadUrl("file:///android_asset/k_1.gif");
        dialog_info= new Dialog(kardio.this);
        dialog_info.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_info.setContentView(R.layout.ex_info);
        dialog_info.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_info.setCancelable(false);
        mEx.setText(exName[i]);
        mExInfoText=dialog_info.findViewById(R.id.ex_info_text);
        mExInfoText.setText(exInfo[i]);

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
                webView.loadUrl("file:///android_asset/k_"+Integer.toString(i)+".gif");
                mEx.setText(exName[i]);
                mExInfoText.setText(exInfo[i]);
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
            Intent intent = new Intent(kardio.this, MenuLevels.class);
            startActivity(intent); finish();
            return;
        }else{
            Toast.makeText(getBaseContext(), "Нажмите ещё раз, чтобы отменить тренировку",Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }



}