package com.example.even1.endorsedsystemstudent.StackFragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.even1.endorsedsystemstudent.Adapter.HorizonListviewAdapter;
import com.example.even1.endorsedsystemstudent.Adapter.MyAdapter;
import com.example.even1.endorsedsystemstudent.Data.Books;
import com.example.even1.endorsedsystemstudent.R;
import com.example.even1.endorsedsystemstudent.View.HorizontalListView;
import com.example.even1.endorsedsystemstudent.View.MyListview;

import java.util.ArrayList;
import java.util.List;

public class ReadingOUT extends Fragment implements View.OnClickListener{
    private HorizontalListView mListView;
    private String[] str = {"小学","初中","高中"};
    private HorizonListviewAdapter mAdapter;
    private List<Books> data;
    private Books goods;
    private MyListview mListView2;
    private MyAdapter adapter;

    private Toolbar toolbar;

    private ImageView imageView;
    private LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_reading_out,container,false);

        mListView2 = (MyListview)view.findViewById(R.id.mylist);
        toolbar = (Toolbar)view.findViewById(R.id.toolbar);

        //inflater = LayoutInflater.from(context);
        View convertView = inflater.inflate(R.layout.item_books,container,false);
        imageView = (ImageView)convertView.findViewById(R.id.all);
        linearLayout = (LinearLayout) convertView.findViewById(R.id.toall);

        initView2();
        return view;
    }
    public void initView2() {
        data = new ArrayList<Books>();
        goods = new Books("第一本书", R.mipmap.book);
        data.add(goods);
        goods = new Books("第二本书",R.mipmap.book);
        data.add(goods);
        goods = new Books("第三本书",R.mipmap.book);
        data.add(goods);
        goods = new Books("第四本书",R.mipmap.book);
        data.add(goods);
        goods = new Books("第五本书",R.mipmap.book);
        data.add(goods);
        /*mAdapter = new HorizonListviewAdapter(getActivity(), data);
        adapter = new MyAdapter(getContext(),str,mAdapter);
        mListView2.setAdapter(adapter);*/

        //imageView.setOnClickListener(this);
        //linearLayout.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.all:
                startActivity(new Intent(getActivity(),Book_List.class));
                break;
            default:
                break;
        }
    }

}
