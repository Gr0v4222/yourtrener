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



public class MenuLevels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_levels);

        //Button buttonStart = (Button)findViewById(R.id.buttonStart); //объект для кнопки начать
        //buttonStart.setOnClickListener(new View.OnClickListener() {
          //  @Override
           // public void onClick(View view) {
             //   try {
               //     Intent intent = new Intent(MenuLevels.this, Menu1.class);
                 //   startActivity(intent); finish();     //команда перейти на другую страницу
               // } catch (Exception e) {  //если переход не состоится, то игра не вылетит и не закроется

               // }
           // }
        //});
        ImageButton backButton = (ImageButton)findViewById(R.id.backButton); //объект для кнопки начать
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent1 = new Intent(MenuLevels.this, Menu1.class);
                    startActivity(intent1); finish();     //команда перейти на другую страницу
                } catch (Exception e) {  //если переход не состоится, то игра не вылетит и не закроется

                }
            }
        });
        Button mbutton5 = (Button)findViewById(R.id.button5); //объект для кнопки начать
        mbutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent3 = new Intent(MenuLevels.this, kardio.class);
                    startActivity(intent3); finish();     //команда перейти на другую страницу
                } catch (Exception e) {  //если переход не состоится, то игра не вылетит и не закроется

                }
            }
        });
        Button mbutton4 = (Button)findViewById(R.id.button4); //объект для кнопки начать
        mbutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent3 = new Intent(MenuLevels.this, utro.class);
                    startActivity(intent3); finish();     //команда перейти на другую страницу
                } catch (Exception e) {  //если переход не состоится, то игра не вылетит и не закроется

                }
            }
        });
        Button mbutton3 = (Button)findViewById(R.id.button3); //объект для кнопки начать
        mbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent3 = new Intent(MenuLevels.this, stretching_long.class);
                    startActivity(intent3); finish();     //команда перейти на другую страницу
                } catch (Exception e) {  //если переход не состоится, то игра не вылетит и не закроется

                }
            }
        });
        Button mbutton2 = (Button)findViewById(R.id.button2); //объект для кнопки начать
        mbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent1 = new Intent(MenuLevels.this, stretching_short.class);
                    startActivity(intent1); finish();     //команда перейти на другую страницу
                } catch (Exception e) {  //если переход не состоится, то игра не вылетит и не закроется

                }
            }
        });
        Window w;
        w = getWindow();
        ((Window) w).setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    @Override
    public void onBackPressed() {
        try {
            Intent intent1 = new Intent(MenuLevels.this, Menu1.class);
            startActivity(intent1); finish();     //команда перейти на другую страницу
        } catch (Exception e) {  //если переход не состоится, то игра не вылетит и не закроется

        }
    }

}