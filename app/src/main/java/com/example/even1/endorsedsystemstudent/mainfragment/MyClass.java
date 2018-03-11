package com.example.even1.endorsedsystemstudent.mainfragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.even1.endorsedsystemstudent.MyClass.MyClass_detail;
import com.example.even1.endorsedsystemstudent.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyClass extends Fragment implements AdapterView.OnItemClickListener{

    private ListView listView;
    private Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_class,container,false);

        toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        listView = (ListView)view.findViewById(R.id.listview) ;
        init();
        return view;
    }

    private void init() {setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");

        SimpleAdapter adapter = new SimpleAdapter(getActivity(),getData(),R.layout.class_item,
                new String[]{"pic","name"},
                new int[]{R.id.pic,R.id.name});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i=0;i<11;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("pic",R.mipmap.head);
            map.put("name","三年级"+(i+1)+"班");
            list.add(map);
        }
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getActivity(),MyClass_detail.class));
    }
}
