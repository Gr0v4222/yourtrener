package com.projectKPO.yourtrener;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.os.CountDownTimer;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Locale;
public class Activity_login extends AppCompatActivity {
    private String mName;
    private int mAge;
    private float mHeight;
    private float mWeight;
    public static final String BD_Name = "BD";
    SharedPreferences mBD;
    public static final String NAME = "Name";
    public static final String AGE = "Age";
    public static final String HEIGHT = "Height";
    public static final String WEIGHT = "Weight";
    public static boolean isntNumeric(String str) {
        try {
            Double.parseDouble(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
    public static boolean isntInt(String str) {
        try {
            Integer.parseInt(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mBD = this.getSharedPreferences(BD_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor mBDeditor = mBD.edit();

        final EditText nameEditText = findViewById(R.id.name);
        final EditText ageEditText = findViewById(R.id.age);
        final EditText weightEditText = findViewById(R.id.weight);
        final EditText heightEditText = findViewById(R.id.height);
        final Button loginButton = findViewById(R.id.login);
        if(mBD.contains(NAME)) {
            mName=(mBD.getString(NAME, ""));
            mAge=(mBD.getInt(AGE, 0));
            mHeight=(mBD.getFloat(HEIGHT, 0));
            mWeight=(mBD.getFloat(WEIGHT, 0));
        }
        else mName="";
        if (!(mName.equals(""))){
            Intent intent1 = new Intent(Activity_login.this, Menu1.class);
            startActivity(intent1); finish();
        }


        loginButton.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v){

                if ((nameEditText.getText().toString().equals(""))||(ageEditText.getText().toString().equals(""))||(weightEditText.getText().toString().equals(""))||(heightEditText.getText().toString().equals("")))
                {
                    Toast.makeText(getBaseContext(), "Заполните все поля",Toast.LENGTH_LONG).show();
                }
                else
                {

                    {
                        if (isntInt(ageEditText.getText().toString()))
                        {Toast.makeText(getBaseContext(), "Не правильно введён возраст",Toast.LENGTH_LONG).show();}
                            else{if (Integer.parseInt(ageEditText.getText().toString())<0 || Integer.parseInt(ageEditText.getText().toString())>90)
                                {Toast.makeText(getBaseContext(), "ЧТО ТЫ ТАКОЕ... Не правильно введён возраст",Toast.LENGTH_LONG).show();}
                            else {
                            if (isntNumeric(weightEditText.getText().toString())) {
                                    Toast.makeText(getBaseContext(), "Не правильно введён вес (пример: '75.2' в кг)", Toast.LENGTH_LONG).show();
                                }
                                else{
                                    if (Float.parseFloat(weightEditText.getText().toString())<10 || Float.parseFloat(weightEditText.getText().toString())>180)
                                    {Toast.makeText(getBaseContext(), "ЧТО ТЫ ТАКОЕ... Не правильно введён вес",Toast.LENGTH_LONG).show();}
                                    else {
                                        if (isntNumeric(heightEditText.getText().toString())) {
                                            Toast.makeText(getBaseContext(), "Не правильно введён рост (пример: '1.83' в метрах)", Toast.LENGTH_LONG).show();
                                    }
                                        else {
                                            if (Float.parseFloat(heightEditText.getText().toString())<0.55 || Float.parseFloat(heightEditText.getText().toString())>2.51)
                                            {Toast.makeText(getBaseContext(), "Не правильно введён рост",Toast.LENGTH_LONG).show();}
                                            else {
                                                mName = nameEditText.getText().toString();
                                                mAge = Integer.parseInt(ageEditText.getText().toString());
                                                mWeight = Float.parseFloat(weightEditText.getText().toString());
                                                mHeight = Float.parseFloat(heightEditText.getText().toString());
                                                mBDeditor.putString(NAME,mName);
                                                mBDeditor.putInt(AGE,mAge);
                                                mBDeditor.putFloat(HEIGHT,mHeight);
                                                mBDeditor.putFloat(WEIGHT,mWeight);
                                                mBDeditor.apply();
                                                Intent intent1 = new Intent(Activity_login.this, Menu1.class);
                                                startActivity(intent1); finish();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

}
