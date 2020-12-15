package com.projectKPO.yourtrener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Menu1 extends AppCompatActivity {
    private long backPressedTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_1);

        Window w;
        w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Button imageButtonBack = (Button) this.findViewById(R.id.imageButtonBack);
        //imageButtonBack.setOnClickListener(new View.OnClickListener() {
          //  @Override
           // public void onClick(View v) {

           // }
        //});
        ImageButton exitButton = (ImageButton)findViewById(R.id.exitButton); //объект для кнопки начать
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (backPressedTime + 2000 > System.currentTimeMillis()) {
                    finish();
                    return;
                } else {
                    Toast.makeText(getBaseContext(), "Нажмите ещё раз, чтобы отменить тренировку", Toast.LENGTH_SHORT).show();
                }
                backPressedTime = System.currentTimeMillis();
            }
        });
        Button buttonEx = (Button)findViewById(R.id.buttonEx); //объект для кнопки начать
        buttonEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent1 = new Intent(Menu1.this, MenuLevels.class);
                    startActivity(intent1); onPause();     //команда перейти на другую страницу
                } catch (Exception e) {  //если переход не состоится, то игра не вылетит и не закроется

                }
            }
        });
        Button button4Settings = (Button)findViewById(R.id.button4Settings); //объект для кнопки начать
        button4Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent1 = new Intent(Menu1.this, Settings.class);
                    startActivity(intent1); finish();     //команда перейти на другую страницу
                } catch (Exception e) {  //если переход не состоится, то игра не вылетит и не закроется

                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finish();
            return;
        } else {
            Toast.makeText(getBaseContext(), "Нажмите ещё раз, чтобы отменить тренировку", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}