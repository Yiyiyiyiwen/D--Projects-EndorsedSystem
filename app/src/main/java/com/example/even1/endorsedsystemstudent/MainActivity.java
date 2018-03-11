package com.example.even1.endorsedsystemstudent;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.even1.endorsedsystemstudent.Adapter.MyViewPagerAdapter;
import com.example.even1.endorsedsystemstudent.View.NoScrollViewPager;
import com.example.even1.endorsedsystemstudent.mainfragment.BookShelf;
import com.example.even1.endorsedsystemstudent.mainfragment.MyClass;
import com.example.even1.endorsedsystemstudent.mainfragment.MyHome;
import com.example.even1.endorsedsystemstudent.mainfragment.Search;
import com.example.even1.endorsedsystemstudent.mainfragment.Stacks;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private NoScrollViewPager viewPager;

    private List<Fragment>list;
    private MyViewPagerAdapter adapter;
    private String[] titles = {"书架","书库","班级","搜索","我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        viewPager = (NoScrollViewPager)findViewById(R.id.viewpager);

        list = new ArrayList<>();
        list.add(new BookShelf());
        list.add(new Stacks());
        list.add(new MyClass());
        list.add(new Search());
        list.add(new MyHome());

        adapter = new MyViewPagerAdapter(getSupportFragmentManager(),list,titles);
        viewPager.setAdapter(adapter);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

}
