package com.projectKPO.yourtrener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashSet;
import java.util.Set;


public class Stats extends Activity {
    public static final String BD_Name = "BD";
    SharedPreferences mBD;
    public String STAT = "D_Hash";
    private Set<String> D_Hash;


    protected void load_stat(){
        if(mBD.contains(STAT)){
            D_Hash=mBD.getStringSet(STAT, new HashSet<String>());
        }
    }
    public static String[] convert(Set<String> setOfString)
    {

        // Create String[] of size of setOfString

        String[] arrayOfString = new String[setOfString.size()];

        // Copy elements from set to string array
        // using advanced for loop
        int index = setOfString.size()-1;
        for (String str : setOfString)
            arrayOfString[index--] = str;
        return arrayOfString;
    }
    String[] names = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
            "Костя", "Игорь", "Анна", "Денис", "Андрей" };

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        D_Hash=new HashSet<>();
        //Duration=new HashSet<>();
        //Tr_Name=new HashSet<>();
        mBD = this.getSharedPreferences(BD_Name, Context.MODE_PRIVATE);
        load_stat();
        String[] ST = convert(D_Hash);
        if (D_Hash.size()==0)
            {
            ST = new String[1];
            ST[0]="Нет данных";}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);

        // находим список
        ListView lvMain = (ListView) findViewById(R.id.lvMain);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ST);

        // присваиваем адаптер списку
        lvMain.setAdapter(adapter);


    }
    @Override
    public void onBackPressed() {
        try {
            Intent intent1 = new Intent(Stats.this, Menu1.class);
            startActivity(intent1); finish();     //команда перейти на другую страницу
        } catch (Exception e) {  //если переход не состоится, то игра не вылетит и не закроется

        }
    }
}