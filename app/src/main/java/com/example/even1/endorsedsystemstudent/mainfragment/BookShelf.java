package com.example.even1.endorsedsystemstudent.mainfragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.even1.endorsedsystemstudent.Adapter.GridViewAdapter;
import com.example.even1.endorsedsystemstudent.R;
import com.example.even1.endorsedsystemstudent.StackFragment.Book_Detail;

import java.util.ArrayList;
import java.util.HashMap;

public class BookShelf extends Fragment implements AdapterView.OnItemLongClickListener ,AdapterView.OnItemClickListener{

    private Toolbar toolbar;
    private GridView gridview;

    private boolean isShowDelete = false;

    private String[] name = {"福尔摩斯探案集","昆虫记","家","安徒生童话"};
    private int[] pic = {R.mipmap.bookshelfitem1,R.mipmap.bookshelfitem2,R.mipmap.bookshelfitem3,R.mipmap.bookshelfitem4};

    private GridViewAdapter adapter;
    private ArrayList<HashMap<String, Object>> myList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_book_shelf,container,false);

        toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        gridview = (GridView)view.findViewById(R.id.gridview);
        init();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.bookshelfmenu,menu);
    }

    private void init() {

        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");

        gridview.setOnItemLongClickListener(this);
        myList = getMenuAdapter(pic,name);
        adapter = new GridViewAdapter(getActivity(),myList,1);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        if (isShowDelete) {
            isShowDelete = false;

        } else {
            isShowDelete = true;
            adapter.setIsShowDelete(isShowDelete);
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    delete(position);//删除选中项
                    adapter = new GridViewAdapter(getActivity(), myList,1);//重新绑定一次adapter
                    gridview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();//刷新gridview

                }

            });
        }

        adapter.setIsShowDelete(isShowDelete);//setIsShowDelete()方法用于传递isShowDelete值

        return true;
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

    private void delete(int position) {//删除选中项方法
        ArrayList<HashMap<String, Object>> newList = new ArrayList<HashMap<String, Object>>();
        if (isShowDelete) {
            myList.remove(position);
            isShowDelete = false;
        }
        newList.addAll(myList);
        myList.clear();
        myList.addAll(newList);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getActivity(), Book_Detail.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.manage:
                gridview.setOnItemLongClickListener(this);
        }
        return false;
    }

}
