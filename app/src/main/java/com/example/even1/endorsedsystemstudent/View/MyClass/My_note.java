package com.example.even1.endorsedsystemstudent.View.MyClass;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.even1.endorsedsystemstudent.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class My_note extends AppCompatActivity {
    private ArrayList<HashMap<String, Object>> notelist = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_note);

        SharedPreferences sp = getSharedPreferences("SP_TEST", Activity.MODE_PRIVATE);
        String mynote = sp.getString("notes","");
        if(!mynote.equals("")){
            Gson gson = new Gson();
            notelist = gson.fromJson(mynote,new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
        }
        //System.out.println("----------------------------------"+notelist);
        listView = (ListView)findViewById(R.id.notes);

        SimpleAdapter mSchedule = new SimpleAdapter(getApplicationContext(), //activity的context
                notelist,//数据来源 ，第一步造出来的数据
                R.layout.note_item,//ListItem的XML实现，对应一下布局
                new String[] {"mysentense", "mynote"},//动态数组与ListItem对应的子项
                new int[] {R.id.sentense,R.id.note});
        listView.setAdapter(mSchedule);
    }

}
