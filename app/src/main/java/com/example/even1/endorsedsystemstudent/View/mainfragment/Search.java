package com.example.even1.endorsedsystemstudent.View.mainfragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.even1.endorsedsystemstudent.R;
import com.example.even1.endorsedsystemstudent.View.CustomizedView.mSearchLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Search extends Fragment {
    private mSearchLayout msearchLy;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search,container,false);

        msearchLy = (mSearchLayout)view.findViewById(R.id.msearchlayout);
        init();
        return view;
    }

    private void init() {
        String shareData = "杨家将,周易,大学,红楼梦,南北史演义,中庸";
        List<String> skills = Arrays.asList(shareData.split(","));

        String shareHotData ="西游记,三国演义,大学,孟子";
        List<String> skillHots = Arrays.asList(shareHotData.split(","));

        msearchLy.initData(skills, skillHots, new mSearchLayout.setSearchCallBackListener() {
            @Override
            public void Search(String str) {
                //进行或联网搜索
            }
            @Override
            public void Back() {
                getActivity().finish();
            }

            @Override
            public void ClearOldData() {
                //清除历史搜索记录  更新记录原始数据
            }
            @Override
            public void SaveOldData(ArrayList<String> AlloldDataList) {
                //保存所有的搜索记录
            }
        });
    }

}
