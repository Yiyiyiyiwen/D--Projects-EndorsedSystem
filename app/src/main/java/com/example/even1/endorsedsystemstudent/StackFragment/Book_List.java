package com.example.even1.endorsedsystemstudent.StackFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Message;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.even1.endorsedsystemstudent.Adapter.BookListAdapter;
import com.example.even1.endorsedsystemstudent.Adapter.ImageListAdapter;
import com.example.even1.endorsedsystemstudent.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class Book_List extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private Toolbar toolbar;
    private ListView listview;
    private TextView title;
    private String img,bookname,writer,brief,introduce;
    private ArrayList<HashMap<String, Object>> myList = new ArrayList<>();
    private BookListAdapter adapter;
    public int IS_FINISH;


    private android.os.Handler handler = new android.os.Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == IS_FINISH) {
                adapter = new BookListAdapter(getApplicationContext(),myList);
                listview.setAdapter(adapter);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__list);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        listview = (ListView)findViewById(R.id.listview);
        title = (TextView)findViewById(R.id.title);
        init();
    }

    private void init() {
        title.setText("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle("");
        getbookcase();
        listview.setOnItemClickListener(this);
    }

    private void getbookcase(){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://118.25.100.167/android/book.action";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String resultDate = new String(bytes,"utf-8");
                    JSONArray array = new JSONArray(resultDate);
                    for(int j=0;j<array.length();j++){
                        JSONObject js = array.getJSONObject(j);
                        int id = js.getInt("id");
                        img = js.getString("img");
                        String imgurl = "http://118.25.100.167"+img;
                        bookname = js.getString("bookname");
                        writer = js.getString("writer");
                        brief = js.getString("brief");
                        introduce = js.getString("introduce");
                        System.out.println("getbookcase---------------------------------------");
                        System.out.println("bookname-------------"+bookname);
                        HashMap<String,Object> map = new HashMap<>();
                        map.put("pic",imgurl);
                        map.put("name",bookname);
                        map.put("intro",introduce);
                        map.put("id",id);
                        myList.add(map);
                        Message msg = Message.obtain();
                        msg.obj = bytes;
                        msg.what = IS_FINISH;
                        handler.sendMessage(msg);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        //设置搜索栏的默认提示
        searchView.setQueryHint("请输入书本名称");
        //默认刚进去就打开搜索栏
        searchView.setIconified(true);
        //设置输入文本的EditText
        SearchView.SearchAutoComplete et = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        //设置搜索栏的默认提示，作用和setQueryHint相同
        et.setHint("请输入书本名称");
        //设置提示文本的颜色
        et.setHintTextColor(Color.WHITE);
        //设置输入文本的颜色
        et.setTextColor(Color.WHITE);
        //设置提交按钮是否可见
        //searchView.setSubmitButtonEnabled(true);

        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,Book_Detail.class);
        Bundle bundle = new Bundle();
        bundle.putInt("bookid", (Integer) myList.get(position).get("id"));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
