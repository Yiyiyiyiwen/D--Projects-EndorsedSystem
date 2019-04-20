package com.example.even1.endorsedsystemstudent.View.MyHome;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.even1.endorsedsystemstudent.R;
import com.example.even1.endorsedsystemstudent.View.CustomizedView.MyListview;
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

import static com.example.even1.endorsedsystemstudent.View.StackFragment.Book_Detail.setListViewHeightBasedOnChildren;

public class mMessage extends Fragment {

    private MyListview listView;
    private String[] teachername = {"三年级一班蒋老师","三年级一班蒋老师","三年级一班蒋老师","三年级一班蒋老师","三年级一班蒋老师","三年级一班蒋老师"};
    private String[] content = {"《红楼梦》阅读批注","《西游记》阅读批注","《三国演义》阅读批注","《红楼梦》阅读批注","《西游记》阅读批注","《三国演义》阅读批注"};
    private String[] time ={"10:31","2-27","2-26","2-25","2-24","2-23"};
    List<Map<String,Object>> list = new ArrayList<>();
    private List<Integer> mHomework = new ArrayList<>();
    private int cid,mid;
    private int id,uid,bookid,chapterid,oid;
    private String brief,ctime,endtime;
    private String mbookname;
    List<String> bookname = new ArrayList<>();
    public int IS_FINISH;

    private android.os.Handler handler = new android.os.Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == IS_FINISH) {
                SimpleAdapter adapter = new SimpleAdapter(getContext(),list,R.layout.message_item,
                        new String[]{"title","time","head","content"},
                        new int[]{R.id.title,R.id.time,R.id.head,R.id.content});
                listView.setAdapter(adapter);
                //setListViewHeight.setListViewHeightBasedOnChildren(listView,0);
                setListViewHeightBasedOnChildren(listView);
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_m_message,container,false);

        listView = (MyListview)view.findViewById(R.id.listview);
        init();
        return view;
    }

    private void init() {
        SharedPreferences sp = getContext().getSharedPreferences("User", Context.MODE_PRIVATE);
        mid = sp.getInt("id",999);
        getmClass(mid);
        getbookcase();
    }

    private void getmClass(int userid) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://118.25.100.167/android/classlist.action?userid="+userid;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String resultDate = new String(bytes,"utf-8");
                    JSONArray array = new JSONArray(resultDate);
                    for(int j=0;j<array.length();j++){
                        JSONObject js = array.getJSONObject(j);
                        cid = js.getInt("id");
                        mHomework.add(cid);
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getbookcase(){
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
                        mbookname = js.getString("bookname");
                        bookname.add(mbookname);
                    }
                    System.out.println(bookname);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for(int position=0;position<mHomework.size();position++){
                    inithomework(mHomework.get(position));
                }

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void inithomework(int cid) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://118.25.100.167/android/homework.action?cid="+cid;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String resultDate = new String(bytes,"utf-8");
                    JSONArray array = new JSONArray(resultDate);
                    for(int j=0;j<array.length();j++){
                        JSONObject js = array.getJSONObject(j);
                        id = js.getInt("id");
                        uid = js.getInt("uid");
                        bookid = js.getInt("bookid");
                        chapterid = js.getInt("chapterid");
                        oid = js.getInt("oid");
                        brief = js.getString("brief");
                        //时间戳转换
                        ctime = mInfor.stampToDate(js.getString("ctime"));
                        endtime = mInfor.stampToDate(js.getString("endtime"));
                        Map<String,Object> map = new HashMap<>();
                        map.put("content",brief);
                        map.put("head",R.mipmap.green);
                        map.put("title","阅读《"+bookname.get(bookid-1)+"》第"+chapterid+"回");
                        map.put("time","截止时间："+endtime);
                        list.add(map);
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
                Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
