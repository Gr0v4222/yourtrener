package space.projectKPO.yourtrener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Menu1 extends AppCompatActivity {

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

        Button buttonEx = (Button)findViewById(R.id.buttonEx); //объект для кнопки начать
        buttonEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent1 = new Intent(Menu1.this, MenuLevels.class);
                    startActivity(intent1); finish();     //команда перейти на другую страницу
                } catch (Exception e) {  //если переход не состоится, то игра не вылетит и не закроется

                }
            }
        });

    }
}