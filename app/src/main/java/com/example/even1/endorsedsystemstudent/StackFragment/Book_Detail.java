package com.example.even1.endorsedsystemstudent.StackFragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.even1.endorsedsystemstudent.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Book_Detail extends AppCompatActivity{

    private TextView bookintro;
    private ImageView all;
    private ListView listView;
    private Toolbar toolbar;
    private ScrollView scrollView;
    private String[] number = {"第0001回","第0002回","第0003回","第0004回"};
    private String[] content = {"宴桃园豪杰三结义 斩黄巾英雄首立功","张翼德怒鞭督邮 何国舅谋诛宦竖","议温明董卓叱丁原 馈金珠李肃说吕布","废汉帝陈留践位 谋董贼孟德献刀"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__detail);

        bookintro = (TextView)findViewById(R.id.bookintro);
        all = (ImageView)findViewById(R.id.all);
        listView = (ListView)findViewById(R.id.listview);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        init();
    }

    private void init() {
        bookintro.setText(R.string.bookdetail);
        all.setOnClickListener(new View.OnClickListener() {
            Boolean flag = true;
            @Override
            public void onClick(View v) {
                if(flag){
                    flag = false;
                    bookintro.setEllipsize(null);
                    bookintro.setSingleLine(flag);
                    all.setImageResource(R.mipmap.up);
                }else{
                    flag = true;
                    bookintro.setEllipsize(TextUtils.TruncateAt.END);
                    bookintro.setSingleLine(flag);
                    all.setImageResource(R.mipmap.down);
                }
            }
        });
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,getData(),R.layout.catalog_item,
                new String[]{"number","content"},
                new int[]{R.id.number,R.id.content});
        listView.setAdapter(simpleAdapter);
        setListViewHeightBasedOnChildren(listView);
        scrollView.smoothScrollTo(0,0);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i=0;i<number.length;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("number",number[i]);
            map.put("content",content[i]);
            list.add(map);
        }
        return list;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView){
        ListAdapter listAdapter = listView.getAdapter();
        if(listAdapter==null){
            return;
        }
        int totalHeight=0;
        for(int i=0;i<listAdapter.getCount();i++){
            View listItem = listAdapter.getView(i,null,listView);
            listItem.measure(0,0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight()*(listAdapter.getCount()-1));
        listView.setLayoutParams(params);

    }
}
