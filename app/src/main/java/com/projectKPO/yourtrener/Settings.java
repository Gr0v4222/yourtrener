package com.projectKPO.yourtrener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {
    protected String mName;
    protected int mAge;
    protected float mHeight;
    protected float mWeight;
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
    public int isCorrectToSave(String n, String a, String h,String w){
        if ((n.equals(""))||(a.equals(""))||(w.equals(""))||(h.equals("")))
        {
            return 1;
        }
        else {

            {
                if (isntInt(a)) {
                    return 2;
                } else {
                    if (Integer.parseInt(a) < 3 || Integer.parseInt(a) > 90) {
                        return 3;
                    } else {
                        if (isntNumeric(w)) {
                            return 4;
                        } else {
                            if (Float.parseFloat(w) < 10 || Float.parseFloat(w) > 150) {
                                return 5;
                            } else {
                                if (isntInt(h)) {
                                    return 6;
                                } else {
                                    if ( Integer.parseInt(h) < 55 || Integer.parseInt(h) > 251) {
                                        return 7;
                                    } else return 0;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public void save_set(EditText n, EditText a, EditText h,EditText w,SharedPreferences.Editor ed){
        ed.putString(NAME,n.getText().toString());
        ed.putInt(AGE,Integer.parseInt(a.getText().toString()));
        ed.putFloat(HEIGHT,Float.parseFloat(h.getText().toString()));
        ed.putFloat(WEIGHT,Float.parseFloat(w.getText().toString()));
        ed.apply();
    }
    public boolean load_set (EditText n, EditText a, EditText h, EditText w){
        if(mBD.contains(NAME)) {
            mName = (mBD.getString(NAME, ""));
            mAge = (mBD.getInt(AGE, 10));
            mHeight = (mBD.getFloat(HEIGHT, 1));
            mWeight = (mBD.getFloat(WEIGHT, 30));
            n.setText(mName);
            a.setText(Integer.toString(mAge));
            w.setText(Float.toString(mWeight));
            h.setText(Float.toString(mHeight));
            return true;
        }
        else return false;
    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        mBD = this.getSharedPreferences(BD_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor mBDeditor = mBD.edit();

        final EditText nameEditText = findViewById(R.id.name);
        final EditText ageEditText = findViewById(R.id.age);
        final EditText weightEditText = findViewById(R.id.weight);
        final EditText heightEditText = findViewById(R.id.height);
        final Button loginButton = findViewById(R.id.login);
        if(load_set(nameEditText,ageEditText,heightEditText,weightEditText)){}


        loginButton.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v){
                String sName=nameEditText.getText().toString();
                String sAge=ageEditText.getText().toString();
                String sHeight=nameEditText.getText().toString();
                String sWeight=nameEditText.getText().toString();
                int check_enter=isCorrectToSave(sName,sAge,sHeight,sWeight);
                if (check_enter==1)
                {
                    Toast.makeText(getBaseContext(), "Заполните все поля",Toast.LENGTH_LONG).show();
                }
                else
                {

                    {
                        if (check_enter==2)
                        {Toast.makeText(getBaseContext(), "Не правильно введён возраст",Toast.LENGTH_LONG).show();}
                            else{if (check_enter==3)
                                {Toast.makeText(getBaseContext(), "Не правильно введён возраст",Toast.LENGTH_LONG).show();}
                            else {
                            if (check_enter==4) {
                                    Toast.makeText(getBaseContext(), "Не правильно введён вес (пример: '75.2' в кг)", Toast.LENGTH_LONG).show();
                                }
                                else{
                                    if (check_enter==5)
                                    {Toast.makeText(getBaseContext(), "Не правильно введён вес (пример: '75.2' в кг)",Toast.LENGTH_LONG).show();}
                                    else {
                                        if (check_enter==6) {
                                            Toast.makeText(getBaseContext(), "Не правильно введён рост (пример: '183' в см)", Toast.LENGTH_LONG).show();
                                    }
                                        else {
                                            if (check_enter==7)
                                            {Toast.makeText(getBaseContext(), "Не правильно введён рост (пример: '183' в см)",Toast.LENGTH_LONG).show();}
                                            else {
                                                save_set(nameEditText,ageEditText,heightEditText,weightEditText,mBDeditor);
                                                Intent intent1 = new Intent(Settings.this, Menu1.class);
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
        ImageButton backButton = (ImageButton)findViewById(R.id.imageButtonBack); //объект для кнопки начать
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent1 = new Intent(Settings.this, Menu1.class);
                    startActivity(intent1); finish();     //команда перейти на другую страницу
                } catch (Exception e) {  //если переход не состоится, то игра не вылетит и не закроется

                }
            }
        });
    }


}
