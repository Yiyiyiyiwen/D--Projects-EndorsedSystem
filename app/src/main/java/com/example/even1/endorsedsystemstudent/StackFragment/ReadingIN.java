package com.example.even1.endorsedsystemstudent.StackFragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.even1.endorsedsystemstudent.Adapter.HorizonListviewAdapter;
import com.example.even1.endorsedsystemstudent.Adapter.MyAdapter;
import com.example.even1.endorsedsystemstudent.Data.Books;
import com.example.even1.endorsedsystemstudent.R;
import com.example.even1.endorsedsystemstudent.View.HorizontalListView;
import com.example.even1.endorsedsystemstudent.View.MyListview;

import java.util.ArrayList;
import java.util.List;

public class ReadingIN extends Fragment {

    private HorizontalListView mListView;
    private String[] str = {"小学","初中","高中"};
    private HorizonListviewAdapter mAdapter;
    private List<Books> data;
    private Books goods;
    private MyListview mListView2;
    private MyAdapter adapter;

    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_reading_in,container,false);
        mListView2 = (MyListview)view.findViewById(R.id.mylist);

        View view1 = inflater.inflate(R.layout.item_infor,container,false);
        imageView = (ImageView)view1.findViewById(R.id.pic);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "点击", Toast.LENGTH_SHORT).show();
            }
        });
        initView2();
        return view;
    }
    private void initView2() {


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
        mAdapter = new HorizonListviewAdapter(getActivity(), data);
        adapter = new MyAdapter(getContext(),str,mAdapter);
        mListView2.setAdapter(adapter);


    }
}
