package com.example.even1.endorsedsystemstudent.MyClass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.even1.endorsedsystemstudent.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Class_Homework extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private TextView Ttitle;
    private String[] title = {"阅读《红楼梦》第23回","阅读《红楼梦》第23回","阅读《红楼梦》第23回","阅读《红楼梦》第23回","阅读《红楼梦》第23回"};
    private String[] time = {"2018.01.02","2018.01.03","2018.01.04","2018.01.05","2018.01.06"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class__homework);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        listView = (ListView)findViewById(R.id.listview);
        Ttitle = (TextView) findViewById(R.id.title);

        init();
    }

    private void init() {
        Ttitle.setText("班级作业");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.homework_item,
                new String[]{"title","time"},
                new int[]{R.id.title,R.id.time});
        listView.setAdapter(adapter);
    }

    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i=0;i<title.length;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("title",title[i]);
            map.put("time",time[i]);
            list.add(map);
        }
        return list;
    }
}
