package com.example.even1.endorsedsystemstudent.StackFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.even1.endorsedsystemstudent.Adapter.HorizonListviewAdapter;
import com.example.even1.endorsedsystemstudent.Adapter.MyAdapter;
import com.example.even1.endorsedsystemstudent.Adapter.SearchOldDataAdapter;
import com.example.even1.endorsedsystemstudent.Data.Books;
import com.example.even1.endorsedsystemstudent.R;
import com.example.even1.endorsedsystemstudent.View.HorizontalListView;
import com.example.even1.endorsedsystemstudent.View.MyListview;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import cz.msebera.android.httpclient.Header;

import static android.content.Context.MODE_PRIVATE;

public class ReadingIN extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private HorizontalListView mListView,mListView2,mListView3;
    private String[] str = {"小学","初中","高中"};
    private HorizonListviewAdapter mAdapter;
    private ArrayList<HashMap<String, Object>> myList = new ArrayList<>();
    private LinearLayout primary,middle,high;
    private int id,rank;
    private String writer,brief,introduce;
    private String bookname,img;
    private SharedPreferences pref;
    public int IS_FINISH;



    private android.os.Handler handler = new android.os.Handler(){
        public void handleMessage(Message msg){
            if(msg.what==IS_FINISH){
                mAdapter = new HorizonListviewAdapter(getContext(),myList);
                //adapter = new MyAdapter(getContext(),str,mAdapter);
                //mListView2.setAdapter(adapter);
                mListView.setAdapter(mAdapter);
                mListView2.setAdapter(mAdapter);
                mListView3.setAdapter(mAdapter);
                /*mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Bundle bundle = new Bundle();
                        bundle.putString("bookname",bookname);SimpleAdapter adapter = new SimpleAdapter(getActivity(),list,R.layout.class_item,
                new String[]{"pic","name"},
                new int[]{R.id.pic,R.id.name});
        listView.setAdapter(adapter);SimpleAdapter adapter = new SimpleAdapter(getActivity(),list,R.layout.class_item,
                new String[]{"pic","name"},
                new int[]{R.id.pic,R.id.name});
        listView.setAdapter(adapter);
                        bundle.putString("writer",writer);
                        bundle.putString("brief",brief);
                        bundle.putString("introduce",introduce);
                        Intent intent = new Intent();
                        intent.putExtras(bundle);
                        intent.setClass(getActivity(), Book_Detail.class);
                        startActivity(intent);
                    }
                });*/
                /*mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        HorizonListviewAdapter.ViewHolder holder = (HorizonListviewAdapter.ViewHolder)mAdapter.getView((int) id,view,parent).getTag();
                        holder.item.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getContext(), "234234134314324", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });*/

            }

        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bookrecommend,container,false);
        mListView = (HorizontalListView) view.findViewById(R.id.horizontal_listview);
        mListView2 = (HorizontalListView) view.findViewById(R.id.horizontal_listview2);
        mListView3 = (HorizontalListView) view.findViewById(R.id.horizontal_listview3);

        primary = (LinearLayout)view.findViewById(R.id.primary);
        middle = (LinearLayout)view.findViewById(R.id.middle);
        high = (LinearLayout)view.findViewById(R.id.high);

        primary.setOnClickListener(this);
        initView2();

        mListView.setOnItemClickListener(this);
        return view;
    }
    private void initView2() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://118.25.100.167/android/book.action";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String resultDate = new String(bytes,"utf-8");
                    JSONArray array = new JSONArray(resultDate);
                    for(int j=0;j<5;j++){
                        JSONObject js = array.getJSONObject(j);
                        id = js.getInt("id");
                        bookname = js.getString("bookname");
                        writer = js.getString("writer");
                        brief = js.getString("brief");
                        introduce = js.getString("introduce");
                        img = "http://118.25.100.167"+js.getString("img");
                        //rank = js.getInt("rank");
                        System.out.println(
                                "Readingin---------id-------------"+id
                                        +"bookname-------------"+bookname+"writer--------"+writer+"img------------------"+img
                        );
                        HashMap<String,Object> map = new HashMap<>();
                        map.put("id",id);
                        map.put("pic",img);
                        map.put("name",bookname);
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
                Toast.makeText(getActivity(), "请求失败你傻子", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.primary:
                Intent intent = new Intent(getActivity(),Book_List.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(),Book_Detail.class);
        Bundle bundle = new Bundle();
        bundle.putInt("bookid", (Integer) myList.get(position).get("id"));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
