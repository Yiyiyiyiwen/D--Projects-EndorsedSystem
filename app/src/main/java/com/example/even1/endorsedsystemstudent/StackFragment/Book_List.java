package com.example.even1.endorsedsystemstudent.StackFragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.even1.endorsedsystemstudent.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Book_List extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private Toolbar toolbar;
    private ListView listview;
    private TextView title;

    private int[] pic = {R.mipmap.book1,R.mipmap.book2,R.mipmap.book3,R.mipmap.book4,R.mipmap.book5};
    private String[] name = {"《朝花夕拾》","《昆虫记》","《小王子》","《福尔摩斯》","《三国演义》"};
    private String[] intro = {"《朝花夕拾》原名《旧事重提》，是现代文学家鲁迅的散文集，收录鲁迅于1926年创作的10篇回忆性散文，[1]  1928年由北京未名社初版，现编入《鲁迅全集》第2卷。",
    "《昆虫记》（Souvenirs Entomologiques）又称《昆虫世界》《昆虫物语》《昆虫学札记》或《昆虫的故事》，是法国昆虫学家、文学家让-亨利·卡西米尔·法布尔所著的长篇科普文学作品，共十卷。（法国） 该作品是一部概括昆虫的种类、特征、习性和婚习的昆虫学巨著，同时也是一部富含知识、趣味美感和哲理的文学宝藏。这部著作的法文书名直译为《昆虫学的回忆》，副标题为“对昆虫的本能及其习俗的研究”。它的文字清新、自然有趣，语调轻松幽默诙谐，基于事实的故事情节曲折奇异。作者将昆虫的多彩生活与自己的人生感悟融为一体，用人性去看待昆虫。字里行间都透露出作者对生命的尊敬与热爱。",
    "《小王子》是法国作家安托万·德·圣·埃克苏佩里于1942年写成的著名儿童文学短篇小说。本书的主人公是来自外星球的小王子。书中以一位飞行员作为故事叙述者，讲述了小王子从自己星球出发前往地球的过程中，所经历的各种历险。作者以小王子的孩子式的眼光，透视出成人的空虚、盲目，愚妄和死板教条，用浅显天真的语言写出了人类的孤独寂寞、没有根基随风流浪的命运。同时，也表达出作者对金钱关系的批判，对真善美的讴歌。",
    "《福尔摩斯探案全集》是英国作家阿瑟·柯南道尔创作的小说集，主角名为夏洛克·福尔摩斯（Sherlock Holmes，又译作歇洛克·福尔摩斯），共有4部长篇及56个短篇。第一部长篇《血字的研究》完成于1886年，隔年与其它作品合集出版于《比顿圣诞年刊》。被多次改编为电影与电视剧。",
    "《三国演义》是中国古典四大名著之一，是中国第一部长篇章回体历史演义小说，全名为《三国志通俗演义》（又称《三国志演义》），作者是元末明初的著名小说家罗贯中。《三国志通俗演义》成书后有嘉靖壬午本等多个版本传于世，到了明末清初，毛宗岗对《三国演义》整顿回目、修正文辞、改换诗文"
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
        setSupportActionBar(toolbar);
        SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.books_item,
                new String[]{"pic","name","intro"},
                new int[]{R.id.pic,R.id.name,R.id.intro});
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }

    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i=0;i<pic.length;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("pic",pic[i]);
            map.put("name",name[i]);
            map.put("intro",intro[i]);
            list.add(map);
        }
        return list;
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
        startActivity(new Intent(this,Book_Detail.class));
    }
}
