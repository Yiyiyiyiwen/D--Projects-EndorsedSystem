package com.example.even1.endorsedsystemstudent.View.MyClass;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.even1.endorsedsystemstudent.R;
import com.example.even1.endorsedsystemstudent.View.StackFragment.StartReading;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

public class Homework_detail extends AppCompatActivity implements View.OnClickListener {

    private TextView jianjie,request,bookname,starttime,endtime,chapterid;
    private int bookid;
    private String mbookname;
    private String brief;
    private int mchapterid;
    private TextView Ttitle;
    private Toolbar toolbar;
    public int IS_FINISH;
    private Button check,start,submit;

    private android.os.Handler handler = new android.os.Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == IS_FINISH) {
                bookname.setText("《"+mbookname+"》");
                request.setText(brief);
                jianjie.setText("阅读《"+mbookname+"》第"+mchapterid+"章");

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_detail);
        Ttitle = (TextView) findViewById(R.id.title);
        jianjie = (TextView)findViewById(R.id.jianjie);
        request = (TextView)findViewById(R.id.request);
        bookname = (TextView)findViewById(R.id.bookname);
        starttime = (TextView)findViewById(R.id.starttime);
        endtime = (TextView)findViewById(R.id.endtime);
        chapterid = (TextView)findViewById(R.id.chapters);


        check  = (Button)findViewById(R.id.check);
        start = (Button)findViewById(R.id.startread);
        submit = (Button)findViewById(R.id.submit);
        init();
    }

    private void init() {
        Ttitle.setText("作业详情");


        Bundle bundle=getIntent().getExtras();
        bookid = bundle.getInt("bookid");
        brief = bundle.getString("brief");
        mchapterid = bundle.getInt("chapterid");
        Toast.makeText(this, "bookid"+bookid, Toast.LENGTH_SHORT).show();
        getbookcase(bookid);

        starttime.setText(bundle.getString("ctime"));
        endtime.setText(bundle.getString("endtime"));
        chapterid.setText("第"+bundle.getInt("chapterid")+"章");

        check.setOnClickListener(this);
        start.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    public void getbookcase(final int mbookid){
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
                                if(mbookid==id){
                                    mbookname = js.getString("bookname");
                                    Message msg = Message.obtain();
                                    msg.obj = bytes;
                                    msg.what = IS_FINISH;
                                    handler.sendMessage(msg);
                                }
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.check:
                startActivity(new Intent(this, My_note.class));
                break;
            case R.id.startread:
                Intent intent = new Intent(this, StartReading.class);
                intent.putExtra("bookid", bookid);
                startActivity(intent);
                break;
            case R.id.submit:
                Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
