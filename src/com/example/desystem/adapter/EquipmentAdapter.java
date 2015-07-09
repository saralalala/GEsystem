package com.example.desystem.adapter;

import java.util.List;

import com.example.desystem.R;
import com.example.desystem.domain.EquipmentInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class EquipmentAdapter extends BaseAdapter {

	Context mContext;
	List<EquipmentInfo> mLis;
	
	public EquipmentAdapter(Context context,List<EquipmentInfo> lis) {
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
        	convertView = LayoutInflater.from(mContext).inflate(R.layout.equipment_item,
					null);//将layout文件实例化一个子空间
        	viewHolder.equipmentid = (TextView) convertView.findViewById(R.id.item1);
        	viewHolder.equipment = (TextView) convertView.findViewById(R.id.item2);
        	viewHolder.type = (TextView) convertView.findViewById(R.id.item3);
        	viewHolder.spec = (TextView) convertView.findViewById(R.id.item4);
        	viewHolder.price = (TextView) convertView.findViewById(R.id.item5);
        	viewHolder.room = (TextView) convertView.findViewById(R.id.item6);
        	viewHolder.groupno = (TextView) convertView.findViewById(R.id.item7);
        	viewHolder.chargeperson = (TextView) convertView.findViewById(R.id.item8);
        	viewHolder.estate = (TextView) convertView.findViewById(R.id.item9);
        	viewHolder.ustate = (TextView) convertView.findViewById(R.id.item10);
        	viewHolder.repairstate = (TextView) convertView.findViewById(R.id.item11);
        	viewHolder.spinner = (Spinner) convertView.findViewById(R.id.item12);
        	convertView.setTag(viewHolder);
        } else{
        	viewHolder = (NewsViewHolder)convertView.getTag();
        }
        viewHolder.equipmentid.setText(""+mLis.get(position).getEquipmentid());
        viewHolder.equipment.setText(""+mLis.get(position).getEquipment());
        viewHolder.type.setText(""+mLis.get(position).getType());//将int类型转化为String类型
        viewHolder.spec.setText(""+mLis.get(position).getSpec());
        viewHolder.price.setText(String.valueOf(mLis.get(position).getPrice()));
        viewHolder.room.setText(""+mLis.get(position).getRoom());
        viewHolder.groupno.setText(""+mLis.get(position).getGroupno());
        viewHolder.chargeperson.setText(""+mLis.get(position).getChargeperson());
        viewHolder.estate.setText(""+mLis.get(position).getEstate());
        viewHolder.ustate.setText(""+mLis.get(position).getUstate());
        viewHolder.repairstate.setText(""+mLis.get(position).getRepairstate());
//        viewHolder.spinner.setOnItemClickListener(new OnItemClickListener() {  
//		  
//        @Override  
//        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
//                long arg3) {  
//            // TODO Auto-generated method stub  
//             switch(arg2){
//             case 0:
//            	 
//            	 break;
//             case 1:
//            	 break ;
//             case 2:
//            	 viewHolder.ustate.setText("back");
//            	 break;
//             case 3:
//            	 break;
//             default:
//            	 break;
//            		 
//             }
//        }  
//  
//    });
		return convertView;
	}
	
	private final class NewsViewHolder {
		public TextView equipmentid;
    	public TextView equipment;
    	public TextView type;
    	public TextView spec;
    	public TextView price;
    	public TextView room;
    	public TextView groupno;
    	public TextView chargeperson;
    	public TextView estate;
    	public TextView ustate;
    	public TextView repairstate;
    	public Spinner spinner;
	}

}


