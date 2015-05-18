package com.example.desystem.adapter;

import java.util.List;

import com.example.desystem.R;
import com.example.desysytem.domain.EquipmentAddInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class EquipmentAddAdapter extends BaseAdapter {

	Context mContext;
	List<EquipmentAddInfo> mLis;
	
	public EquipmentAddAdapter(Context context,List<EquipmentAddInfo> lis) {
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
        	convertView = LayoutInflater.from(mContext).inflate(R.layout.equipment_add_item,
					null);//将layout文件实例化一个子空间
        	viewHolder.equipmentbuyid = (TextView) convertView.findViewById(R.id.item1);
        	viewHolder.equipment = (TextView) convertView.findViewById(R.id.item2);
        	viewHolder.type = (TextView) convertView.findViewById(R.id.item3);
        	viewHolder.spec = (TextView) convertView.findViewById(R.id.item4);
        	viewHolder.userid = (TextView) convertView.findViewById(R.id.item5);
        	viewHolder.usertype = (TextView) convertView.findViewById(R.id.item6);
        	viewHolder.day = (TextView) convertView.findViewById(R.id.item7);
        	viewHolder.time = (TextView) convertView.findViewById(R.id.item8);
        	viewHolder.price = (TextView) convertView.findViewById(R.id.item9);
        	viewHolder.num = (TextView) convertView.findViewById(R.id.item10);
        	viewHolder.memo = (TextView) convertView.findViewById(R.id.item11);
        	convertView.setTag(viewHolder);
        } else{
        	viewHolder = (NewsViewHolder)convertView.getTag();
        }
        viewHolder.equipmentbuyid.setText(""+mLis.get(position).getEquipmentbuyid());
        viewHolder.equipment.setText(""+mLis.get(position).getEquipment());
        viewHolder.type.setText(""+mLis.get(position).getType());//将int类型转化为String类型
        viewHolder.spec.setText(""+mLis.get(position).getSpec());
        viewHolder.userid.setText(String.valueOf(mLis.get(position).getUserid()));
        viewHolder.usertype.setText(""+mLis.get(position).getUsertype());
        viewHolder.day.setText(""+mLis.get(position).getDay());
        viewHolder.time.setText(""+mLis.get(position).getTime());
        viewHolder.price.setText(""+mLis.get(position).getPrice());
        viewHolder.num.setText(""+mLis.get(position).getNum());
        viewHolder.memo.setText(""+mLis.get(position).getmemo());
        
		return convertView;
	}
	
	private final class NewsViewHolder {
		public TextView equipmentbuyid;
    	public TextView equipment;
    	public TextView type;
    	public TextView spec;
    	public TextView userid;
    	public TextView usertype;
    	public TextView day;
    	public TextView time;
    	public TextView price;
    	public TextView num;
    	public TextView memo;
	}

}


