package com.example.even1.endorsedsystemstudent.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.even1.endorsedsystemstudent.MainActivity;
import com.example.even1.endorsedsystemstudent.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private TextView appname,register;
    private EditText name,pass;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        appname = (TextView)findViewById(R.id.appname);
        name = (EditText)findViewById(R.id.usename);
        pass = (EditText)findViewById(R.id.password);
        button = (Button)findViewById(R.id.submit);

        init();

    }

    private void init() {
        Typeface fz=Typeface.createFromAsset(getAssets(),"FZlight.ttf");
        appname.setTypeface(fz);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:
                String mName = name.getText().toString();
                String mPassword = pass.getText().toString();

                if (TextUtils.isEmpty(mName.trim()) || TextUtils.isEmpty(mPassword.trim())) {
                    Toast.makeText(Register.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    loginByAsyncHttpClientGet(mName, mPassword);
                    finish();
                }
                break;

        }
    }
    public void loginByAsyncHttpClientGet(final String userName, final String passWord) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://118.25.100.167/system/user/addUser2.action";
        RequestParams params = new RequestParams();
        params.put("username",userName);
        params.put("password",passWord);
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String resultDate = new String(bytes,"utf-8");

                        Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(Register.this, "注册失败2", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
