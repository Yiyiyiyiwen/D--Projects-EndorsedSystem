package com.example.even1.endorsedsystemstudent.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.even1.endorsedsystemstudent.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Even1 on 2018/2/5.
 */

public class GridViewAdapter extends BaseAdapter {
    private ArrayList<HashMap<String, Object>> myList;
    private Context mContext;
    private TextView name_tv;
    private ImageView img;
    private View deleteView;
    private int number;
    private boolean isShowDelete;// 根据这个变量来判断是否显示删除图标，true是显示，false是不显示

    public GridViewAdapter(Context mcontext,ArrayList<HashMap<String,Object>> myList,int number){
        this.mContext = mcontext;
        this.myList = myList;
        this.number = number;
    }
    public void setIsShowDelete(boolean isShowDelete) {
        this.isShowDelete = isShowDelete;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return myList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(number==1){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.bookshelf_item, null);
            deleteView = convertView.findViewById(R.id.delete);
            deleteView.setVisibility(isShowDelete ? View.VISIBLE : View.GONE);// 设置删除按钮是否显示
        }
        else{
            convertView = LayoutInflater.from(mContext).inflate(R.layout.myclass_item, null);
        }
        img = (ImageView) convertView.findViewById(R.id.pic);
        name_tv = (TextView) convertView.findViewById(R.id.name);

        img.setImageResource(myList.get(position).get("image").hashCode());
        name_tv.setText(myList.get(position).get("name").toString());
        return convertView;
    }

}

