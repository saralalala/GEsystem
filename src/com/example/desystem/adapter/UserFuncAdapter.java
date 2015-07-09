package com.example.desystem.adapter;

import java.util.List;

import com.example.desystem.R;
import com.example.desystem.domain.UserFuncInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class UserFuncAdapter extends BaseAdapter {

	Context mContext;
	List<UserFuncInfo> aLis;
	
	public UserFuncAdapter(Context context,List<UserFuncInfo> lis) {
		super();
		this.mContext = context;
		this.aLis = lis;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(aLis != null){
			return aLis.size();
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
        	convertView = LayoutInflater.from(mContext).inflate(R.layout.user_func_item,
					null);
        	viewHolder.picId = (ImageView) convertView.findViewById(R.id.test_picid);
        	viewHolder.testName = (TextView) convertView.findViewById(R.id.test_name);
        	viewHolder.arrow = (ImageView) convertView.findViewById(R.id.test_arrow);
        	convertView.setTag(viewHolder);
        } else{
        	viewHolder = (NewsViewHolder)convertView.getTag();
        }
        viewHolder.picId.setImageResource(aLis.get(position).getTestPic());
        viewHolder.testName.setText(aLis.get(position).getTestName());
        viewHolder.arrow.setImageResource(aLis.get(position).getArrow());
		return convertView;
	}
	
	private final class NewsViewHolder {
    	public ImageView picId;
    	public TextView testName;
    	public ImageView arrow;
	}

}

