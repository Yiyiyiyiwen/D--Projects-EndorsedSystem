package com.example.even1.endorsedsystemstudent.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.even1.endorsedsystemstudent.Data.Books;
import com.example.even1.endorsedsystemstudent.R;

import java.util.ArrayList;
import java.util.List;

public class HorizonListviewAdapter extends BaseAdapter {

	private LayoutInflater inflater = null;
	private List<Books> goods = new ArrayList<Books>();

	public HorizonListviewAdapter(Context context, List<Books> goods) {
		this.inflater = LayoutInflater.from(context);
		this.goods = goods;
	}

	@Override
	public int getCount() {
		return goods.size();
	}

	@Override
	public Object getItem(int position) {
		return goods.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_infor, null);
			holder.date = (TextView) convertView.findViewById(R.id.name);
			holder.image = (ImageView) convertView.findViewById(R.id.pic);
			holder.item = (LinearLayout) convertView.findViewById(R.id.ll_item);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.date.setText(goods.get(position).getDate());
	    holder.image.setImageResource(goods.get(position).getImageId());
//	    holder.item.setOnTouchListener(new OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View v, MotionEvent ev) {
//				// TODO Auto-generated method stub
//				    int lastX = 0,lastY = 0;
//				    int dx = 0,dy = 0;
//			        int action = ev.getAction();
//			        switch (action) {
//			        case MotionEvent.ACTION_DOWN:
//	                    lastX=(int) ev.getRawX();
//	                    lastY=(int) ev.getRawY();
//	                    break;
//			        case MotionEvent.ACTION_MOVE:
//			        	dx =(int)ev.getRawX() - lastX;   
//	                     dy =(int)ev.getRawY() - lastY; 
//			        	break;
//			        case MotionEvent.ACTION_UP:
//			        	if (dy<200|| dy > -200) {
//							return true;
//						}
//			        	break;
//					default:
//						break;
//					}
//			    return false;
//			}
//			
//		});
			
		return convertView;
	}

	class ViewHolder {
		LinearLayout item;
		TextView date;
		ImageView image;
	}
	
}
