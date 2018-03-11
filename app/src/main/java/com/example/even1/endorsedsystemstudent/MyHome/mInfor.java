package com.example.even1.endorsedsystemstudent.MyHome;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.even1.endorsedsystemstudent.Adapter.GridViewAdapter;
import com.example.even1.endorsedsystemstudent.R;

import java.util.ArrayList;
import java.util.HashMap;

public class mInfor extends Fragment {

    private GridView gridView;
    private GridViewAdapter adapter;
    private String[] name = {"福尔摩斯探案集","昆虫记","家"};
    private int[] pic = {R.mipmap.bookshelfitem1,R.mipmap.bookshelfitem2,R.mipmap.bookshelfitem3};
    private ArrayList<HashMap<String, Object>> myList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_m_infor,container,false);

        gridView = (GridView)view.findViewById(R.id.gridview);
        init();
        return view;
    }

    private void init() {
        myList = getMenuAdapter(pic,name);
        adapter = new GridViewAdapter(getActivity(),myList,1);
        gridView.setAdapter(adapter);
    }

    private ArrayList<HashMap<String, Object>> getMenuAdapter(//将数据装入List
                                                              int[] menuImageArray, String[] menuNameArray) {
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < menuImageArray.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("image", menuImageArray[i]);
            map.put("name", menuNameArray[i]);
            data.add(map);
        }
        return data;
    }
}
