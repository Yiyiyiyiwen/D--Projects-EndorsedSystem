package com.example.even1.endorsedsystemstudent.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.even1.endorsedsystemstudent.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Even1 on 2018/4/1.
 */

public class ImageListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private View deleteView;
    private boolean isShowDelete;// 根据这个变量来判断是否显示删除图标，true是显示，false是不显示
    private ArrayList<HashMap<String,Object>> myList;
    public ImageListAdapter(Context context, ArrayList<HashMap<String,Object>> myList) {
        this.context = context;
        this.myList = myList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Object getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setIsShowDelete(boolean isShowDelete) {
        this.isShowDelete = isShowDelete;
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.bookshelf_item, parent, false);
            deleteView = convertView.findViewById(R.id.delete);
            deleteView.setVisibility(isShowDelete ? View.VISIBLE : View.GONE);// 设置删除按钮是否显示
            viewHolder.pic = (ImageView)convertView.findViewById(R.id.pic);
            viewHolder.name = (TextView)convertView.findViewById(R.id.name);

        Glide
                    .with(context)
                    .load(myList.get(position).get("image"))
                    .into(viewHolder.pic);
        viewHolder.name.setText((CharSequence) myList.get(position).get("name"));
        }
        return convertView;

    }

    static class ViewHolder{
        TextView name;
        ImageView pic;
    }
}
