package com.example.desystem.adapter;

import java.util.List;

import com.example.desystem.R;
import com.example.desystem.domain.EquipmentAddInfo;
import com.example.desystem.domain.EquipmentBorrowInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class EquipmentBorrowAdapter extends BaseAdapter {

	Context mContext;
	List<EquipmentBorrowInfo> mLis;
	
	public EquipmentBorrowAdapter(Context context,List<EquipmentBorrowInfo> lis) {
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
		final NewsViewHolder viewHolder;
        if (convertView == null) {
        	viewHolder = new NewsViewHolder();
        	convertView = LayoutInflater.from(mContext).inflate(R.layout.equipment_borrow_item,
					null);//将layout文件实例化一个子空间
        	viewHolder.equipmentborrowid = (TextView) convertView.findViewById(R.id.item1);
        	viewHolder.equipmentid = (TextView) convertView.findViewById(R.id.item2);
        	viewHolder.takeperson = (TextView) convertView.findViewById(R.id.item3);
        	viewHolder.disperson = (TextView) convertView.findViewById(R.id.item4);
        	viewHolder.day = (TextView) convertView.findViewById(R.id.item5);
        	viewHolder.time = (TextView) convertView.findViewById(R.id.item6);
        	viewHolder.reday = (TextView) convertView.findViewById(R.id.item7);
        	viewHolder.retime = (TextView) convertView.findViewById(R.id.item8);
        	viewHolder.memo = (TextView) convertView.findViewById(R.id.item9);
        	convertView.setTag(viewHolder);
        } else{
        	viewHolder = (NewsViewHolder)convertView.getTag();
        }
        viewHolder.equipmentborrowid.setText(""+mLis.get(position).getEquipmentborrowid());
        viewHolder.equipmentid.setText(""+mLis.get(position).getEquipmentid());
        viewHolder.takeperson.setText(""+mLis.get(position).getTakeperson());//将int类型转化为String类型
        viewHolder.disperson.setText(""+mLis.get(position).getDisperson());
        viewHolder.day.setText(""+mLis.get(position).getDay());
        viewHolder.time.setText(""+mLis.get(position).getTime());
        viewHolder.reday.setText(""+mLis.get(position).getReday());
        viewHolder.retime.setText(""+mLis.get(position).getRetime());
        viewHolder.memo.setText(""+mLis.get(position).getMemo());
        
		return convertView;
	}
	
	private final class NewsViewHolder {
		public TextView equipmentborrowid;
    	public TextView equipmentid;
    	public TextView takeperson;
    	public TextView disperson;
    	public TextView day;
    	public TextView time;
    	public TextView reday;
    	public TextView retime;
    	public TextView memo;
	}

}


