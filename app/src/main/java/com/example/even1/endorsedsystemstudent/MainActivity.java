package com.example.even1.endorsedsystemstudent;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.even1.endorsedsystemstudent.Adapter.MyViewPagerAdapter;
import com.example.even1.endorsedsystemstudent.Login.UserManage;
import com.example.even1.endorsedsystemstudent.View.NoScrollViewPager;
import com.example.even1.endorsedsystemstudent.mainfragment.BookShelf;
import com.example.even1.endorsedsystemstudent.mainfragment.MyClass;
import com.example.even1.endorsedsystemstudent.mainfragment.MyHome;
import com.example.even1.endorsedsystemstudent.mainfragment.Search;
import com.example.even1.endorsedsystemstudent.mainfragment.Stacks;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private NoScrollViewPager viewPager;

    private List<Fragment>list;
    private MyViewPagerAdapter adapter;
    private String[] titles = {"书架","书库","班级","搜索","我的"};
    private TextView name;
    //private String id,nickname,state,biref,phone,email,birth,job,place,school;

    private String nickname ;
    private Fragment myhome = new MyHome();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();

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
        viewPager.setOffscreenPageLimit(4);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        tabLayout.setupWithViewPager(viewPager);

        /*MyHome myHome = new MyHome();
        Bundle bundle = new Bundle();
        bundle.putString("nickname",nickname);
        myHome.setArguments(bundle);*/

    }

}
