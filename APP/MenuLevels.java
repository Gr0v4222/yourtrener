package space.projectKPO.yourtrener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;



public class MenuLevels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_levels);

        ImageButton imageButtonBack = (ImageButton)findViewById(R.id.imageButtonBack);
        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent (MenuLevels.this, Menu1.class);
                    startActivity(intent); finish();
                }catch (Exception e) {

                }
            }
        });

        Button buttonLite = (Button)findViewById(R.id.buttonLite); //объект для кнопки
        buttonLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MenuLevels.this, LiteLevel.class);
                    startActivity(intent);
                    finish();     //команда перейти на другую страницу
                } catch (Exception e) {  //если переход не состоится, то игра не вылетит и не закроется

                }
            }
        });

        Button buttonMedium = (Button)findViewById(R.id.buttonMedium); //объект для кнопки
        buttonMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MenuLevels.this, MediumLevel.class);
                    startActivity(intent);
                    finish();     //команда перейти на другую страницу
                } catch (Exception e) {  //если переход не состоится, то игра не вылетит и не закроется

                }
            }
        });

        Button buttonHard = (Button)findViewById(R.id.buttonHard); //объект для кнопки
        buttonHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MenuLevels.this, HardLevel.class);
                    startActivity(intent);
                    finish();     //команда перейти на другую страницу
                } catch (Exception e) {  //если переход не состоится, то игра не вылетит и не закроется

                }
            }
        });

        Window w;
        w = getWindow();
        ((Window) w).setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
