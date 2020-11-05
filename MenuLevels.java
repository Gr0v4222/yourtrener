package space.projectKPO.yourtrener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

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

        Window w;
        w = getWindow();
        ((Window) w).setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}