package com.example.desystem.adapter;

import java.util.List;

import com.example.desystem.R;
import com.example.desysytem.domain.EquipmentInfo;
import com.example.desysytem.domain.RepairInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class RepairAdapter extends BaseAdapter {

	Context mContext;
	List<RepairInfo> mLis;
	
	public RepairAdapter(Context context,List<RepairInfo> lis) {
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
        	convertView = LayoutInflater.from(mContext).inflate(R.layout.repair_item,
					null);//将layout文件实例化一个子空间
        	viewHolder.askrepairid = (TextView) convertView.findViewById(R.id.item1);
        	viewHolder.day = (TextView) convertView.findViewById(R.id.item2);
        	viewHolder.time = (TextView) convertView.findViewById(R.id.item3);
        	viewHolder.equipment = (TextView) convertView.findViewById(R.id.item4);
        	viewHolder.memo = (TextView) convertView.findViewById(R.id.item5);
        	viewHolder.usertype = (TextView) convertView.findViewById(R.id.item6);
        	viewHolder.userid = (TextView) convertView.findViewById(R.id.item7);
        	viewHolder.deal = (TextView) convertView.findViewById(R.id.item8);
        	viewHolder.dealid = (TextView) convertView.findViewById(R.id.item9);
        	viewHolder.result = (TextView) convertView.findViewById(R.id.item10);
        	viewHolder.repday = (TextView) convertView.findViewById(R.id.item11);
        	viewHolder.reptime = (TextView) convertView.findViewById(R.id.item12);
        	viewHolder.repairstate = (TextView) convertView.findViewById(R.id.item13);
        	convertView.setTag(viewHolder);
        } else{
        	viewHolder = (NewsViewHolder)convertView.getTag();
        }
        viewHolder.askrepairid.setText(""+mLis.get(position).getAskrepairid());
        viewHolder.day.setText(""+mLis.get(position).getDay());
        viewHolder.time.setText(""+mLis.get(position).getTime());//将int类型转化为String类型
        viewHolder.equipment.setText(""+mLis.get(position).getEquipment());
        viewHolder.memo.setText(mLis.get(position).getMemo());
        viewHolder.usertype.setText(""+mLis.get(position).getUsertype());
        viewHolder.userid.setText(""+mLis.get(position).getUserid());
        viewHolder.deal.setText(""+mLis.get(position).getDeal());
        viewHolder.dealid.setText(""+mLis.get(position).getDealid());
        viewHolder.result.setText(""+mLis.get(position).getResult());
        viewHolder.repday.setText(""+mLis.get(position).getRepday());
        viewHolder.reptime.setText(""+mLis.get(position).getReptime());
        viewHolder.repairstate.setText(""+mLis.get(position).getRepairstate());
		return convertView;
	}
	
	private final class NewsViewHolder {
		public TextView askrepairid;
    	public TextView day;
    	public TextView time;
    	public TextView equipment;
    	public TextView memo;
    	public TextView usertype;
    	public TextView userid;
    	public TextView deal;
    	public TextView dealid;
    	public TextView result;
    	public TextView repday;
    	public TextView reptime;
    	public TextView repairstate;

	}

}


