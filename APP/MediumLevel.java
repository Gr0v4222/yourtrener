package space.projectKPO.yourtrener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;



public class MediumLevel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medium_level);

        ImageButton buttonBack4 = (ImageButton)findViewById(R.id.buttonBack4); //объект для кнопки начать
        buttonBack4.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View view) {
            try {
              Intent intent = new Intent(MediumLevel.this, MenuLevels.class);
           startActivity(intent); finish();     //команда перейти на другую страницу
         } catch (Exception e) {  //если переход не состоится, то игра не вылетит и не закроется
         }
           }
         });


        Window w;
        w = getWindow();
        ((Window) w).setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}