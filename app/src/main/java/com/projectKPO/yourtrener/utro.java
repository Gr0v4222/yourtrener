package com.projectKPO.yourtrener;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class utro extends AppCompatActivity {

    private final String TR_NAME = "Утренняя разминка";
    private ImageView u;
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
    private int end_i=12;
    private String[] exName;
    private String[] exInfo;
    Dialog dialog_info;
    private String curtime;
    public static final String BD_Name = "BD";
    SharedPreferences mBD;
    public String STAT = "D_Hash";
    private Set<String> D_Hash;
    protected void load_stat(){
        if(mBD.contains(STAT)){
            D_Hash=mBD.getStringSet(STAT, new HashSet<String>());
        }
    }
    protected void add_stat(String Dh, String D, String TN, SharedPreferences.Editor ed){
        D_Hash.add(Dh+" "+TN+": "+D);
        ed.putStringSet(STAT, D_Hash);
        ed.apply();
    }
    @SuppressLint("SetTextI18n")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        D_Hash=new HashSet<>();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        curtime = formatter.format(date);
        exName=getResources().getStringArray(R.array.u_ex_name);
        exInfo=getResources().getStringArray(R.array.u_ex_info);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utro);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mEx= findViewById(R.id.Ex);
        mExInfoButton = findViewById(R.id.exinfobutton);

        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonExit = findViewById(R.id.imageButtonBack);
        mButtonExit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(backPressedTime + 2000> System.currentTimeMillis()){
                    Intent intent = new Intent(utro.this, MenuLevels.class);
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
        u = (ImageView) findViewById(R.id.u);
        u.setImageResource(R.drawable.u_1);
        dialog_info= new Dialog(utro.this);
        dialog_info.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_info.setContentView(R.layout.ex_info);
        dialog_info.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_info.setCancelable(false);
        mExInfoText=dialog_info.findViewById(R.id.ex_info_text);
        mEx.setText(exName[i]);
        mExInfoText.setText(exInfo[i]);

    }

    private void startTimer() {
        mBD = this.getSharedPreferences(BD_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor mBDeditor = mBD.edit();
        load_stat();
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
                String name="s_l_"+Integer.toString(i);
                int id = getResources().getIdentifier(name, "drawable", getPackageName());
                u.setImageResource(id);
                mEx.setText(exName[i]);
                mExInfoText.setText(exInfo[i]);
                }
                else{
                    mTimerRunning = false;
                    mButtonStartPause.setText("старт");
                    mButtonStartPause.setVisibility(View.INVISIBLE);
                    add_stat(curtime,Long.toString(START_TIME_IN_MILLIS/1000*end_i),TR_NAME,mBDeditor);
                    Intent intent = new Intent(utro.this, MenuLevels.class);
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
            Intent intent = new Intent(utro.this, MenuLevels.class);
            startActivity(intent); finish();
            return;
        }else{
            Toast.makeText(getBaseContext(), "Нажмите ещё раз, чтобы отменить тренировку",Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }



}