package space.projectKPO.yourtrener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import space.projectKPO.yourtrener.ui.login.LoginActivity;

public class Menu1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_1);

        Window w;
        w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageButton imageButtonBack = (ImageButton)findViewById(R.id.imageButtonBack);
        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent (Menu1.this, MainActivity.class);
                    startActivity(intent); finish();
                }catch (Exception e) {

                }
            }
        });

        Button buttonEx = (Button)findViewById(R.id.buttonEx); //объект для кнопки
        buttonEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Menu1.this, MenuLevels.class);
                    startActivity(intent);
                    finish();     //команда перейти на другую страницу
                } catch (Exception e) {  //если переход не состоится, то игра не вылетит и не закроется

                }
            }
        });

    }
}
