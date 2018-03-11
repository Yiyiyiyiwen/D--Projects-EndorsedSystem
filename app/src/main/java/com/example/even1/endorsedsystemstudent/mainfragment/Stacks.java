package com.example.even1.endorsedsystemstudent.mainfragment;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.example.even1.endorsedsystemstudent.Adapter.MyViewPagerAdapter;
import com.example.even1.endorsedsystemstudent.Adapter.RollPagerViewAdapter;
import com.example.even1.endorsedsystemstudent.R;
import com.example.even1.endorsedsystemstudent.StackFragment.RankingList;
import com.example.even1.endorsedsystemstudent.StackFragment.ReadingIN;
import com.example.even1.endorsedsystemstudent.StackFragment.ReadingOUT;
import com.example.even1.endorsedsystemstudent.View.NoScrollViewPager;
import com.jude.rollviewpager.RollPagerView;

import java.util.ArrayList;
import java.util.List;

public class Stacks extends Fragment {

    private Toolbar toolbar;
    private RollPagerView rollpagerview;
    private RollPagerViewAdapter adapter;
    private TabLayout tabLayout;
    private NoScrollViewPager viewPager;
    private int[] imgs = {R.mipmap.adver1,R.mipmap.adver1,R.mipmap.adver1};

    private List<Fragment> list;
    private MyViewPagerAdapter adapter2;
    private String[] titles = {"课内阅读","课外阅读","排行榜"};

    private ScrollView scrollView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_stacks,container,false);
        toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        rollpagerview = (RollPagerView)view.findViewById(R.id.rollpagerview);
        tabLayout = (TabLayout)view.findViewById(R.id.tablayout);
        viewPager = (NoScrollViewPager)view.findViewById(R.id.viewpager);
        init();
        return view;
    }

    private void init() {

        rollpagerview.setPlayDelay(2000);
        rollpagerview.setAnimationDurtion(500);
        adapter = new RollPagerViewAdapter(imgs);
        rollpagerview.setAdapter(adapter);

        list = new ArrayList<>();
        list.add(new ReadingIN());
        list.add(new ReadingOUT());
        list.add(new RankingList());

        adapter2 = new MyViewPagerAdapter(getChildFragmentManager(),list,titles);
        viewPager.setAdapter(adapter2);
        tabLayout.setupWithViewPager(viewPager);
    }

}
