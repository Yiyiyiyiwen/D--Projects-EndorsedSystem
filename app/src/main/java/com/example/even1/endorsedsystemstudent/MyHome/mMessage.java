package com.example.even1.endorsedsystemstudent.MyHome;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.even1.endorsedsystemstudent.R;
import com.example.even1.endorsedsystemstudent.Util.setListViewHeight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mMessage extends Fragment {

    private ListView listView;
    private String[] teachername = {"三年级一班蒋老师","三年级一班蒋老师","三年级一班蒋老师","三年级一班蒋老师","三年级一班蒋老师","三年级一班蒋老师"};
    private String[] content = {"《红楼梦》阅读批注","《西游记》阅读批注","《三国演义》阅读批注","《红楼梦》阅读批注","《西游记》阅读批注","《三国演义》阅读批注"};
    private String[] time ={"10:31","2-27","2-26","2-25","2-24","2-23"};

    private setListViewHeight setListViewHeight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_m_message,container,false);

        listView = (ListView)view.findViewById(R.id.listview);
        init();
        return view;
    }

    private void init() {
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),getData(),R.layout.message_item,
                new String[]{"teachername","content","time"},
                new int[]{R.id.teachername,R.id.content,R.id.time});
        listView.setAdapter(adapter);

        setListViewHeight.setListViewHeightBasedOnChildren(listView,0);
    }

    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i=0;i<teachername.length;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("content",content[i]);
            map.put("time",time[i]);
            map.put("teachername",teachername[i]);
            list.add(map);
        }
        return list;
    }

}
