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

public class Class_Member extends AppCompatActivity {

    private TextView teacher,student;
    private int[]head={R.mipmap.head};
    private String[] tname = {"李老师","王老师","赵老师"};
    private String[] sname = {"王芳","王芳","王芳","王芳","王芳","王芳","王芳","王芳","王芳","王芳","王芳","王芳","王芳","王芳","王芳"};
    private ListView tlistview,slitview;

    private Toolbar toolbar;
    private int slength = sname.length;
    private int tlength = tname.length;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class__member);

        teacher = (TextView)findViewById(R.id.tnumber);
        student = (TextView)findViewById(R.id.snumber);
        tlistview = (ListView)findViewById(R.id.teacher);
        slitview = (ListView)findViewById(R.id.student);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        init();
    }

    private void init() {
        teacher.setText(tlength+"人");
        student.setText(slength+"人");

        SimpleAdapter tadapter = new SimpleAdapter(this,getData(tname),R.layout.class_item,
                new String[]{"pic","name"},
                new int[]{R.id.pic,R.id.name});
        SimpleAdapter sadapter = new SimpleAdapter(this,getData(sname),R.layout.class_item,
                new String[]{"pic","name"},
                new int[]{R.id.pic,R.id.name});
        tlistview.setAdapter(tadapter);
        slitview.setAdapter(sadapter);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private List<Map<String,Object>> getData(String[] catagory){
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i=0;i<catagory.length;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("pic",R.mipmap.head);
            map.put("name",catagory[i]);
            list.add(map);
        }
        return list;
    }
}
