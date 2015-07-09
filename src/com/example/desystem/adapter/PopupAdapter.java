package com.example.desystem.adapter;

import java.util.List;

import com.example.desystem.R;
import com.example.desystem.domain.ComponentInfo;
import com.example.desystem.domain.GoodsInfo;

import android.R.integer;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView;


public class PopupAdapter extends BaseAdapter {

	Context mContext;
	List<String> mLis;
	int mSelect = 0;
	
	public void changeSelected(int positon){ //刷新方法
		
	     if(positon != mSelect){
	    	 
	    	 this.mSelect = positon;
	    	 Log.i("##adapter2", ""+mSelect);
	    	 //notifyDataSetChanged();
	     }
	     
	    }
	
	public PopupAdapter(Context context,List<String> lis) {
		super();
		this.mContext = context;
		this.mLis = lis;
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(mLis != null){
			return mLis.size();
		}else{
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NewsViewHolder viewHolder;
        if (convertView == null) {
        	viewHolder = new NewsViewHolder();
        	convertView = LayoutInflater.from(mContext).inflate(R.layout.popup_item,
					null);//将layout文件实例化一个子空间
        	if(position == mSelect){
        		
        		Log.i("##adapter3", ""+mSelect);
        		convertView.setBackgroundResource(R.drawable.choose_selected);
        	}else{
        		
        		convertView.setBackgroundResource(R.drawable.choose_inselect);
        	}
        		
        	viewHolder.text = (TextView) convertView.findViewById(R.id.text);
        	//viewHolder.pic = (ImageView) convertView.findViewById(R.id.pic);
        	convertView.setTag(viewHolder);
        } else{
        	viewHolder = (NewsViewHolder)convertView.getTag();
        }
        viewHolder.text.setText(""+mLis.get(position));
        //viewHolder.pic.setVisibility(View.INVISIBLE);
		return convertView;
	}
	
	private final class NewsViewHolder {
		public TextView text;
    	//public ImageView pic;
	}

}


