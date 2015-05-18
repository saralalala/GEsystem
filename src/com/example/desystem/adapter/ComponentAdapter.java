package com.example.desystem.adapter;

import java.util.List;

import com.example.desystem.R;
import com.example.desysytem.domain.ComponentInfo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class ComponentAdapter extends BaseAdapter {

	Context mContext;
	List<ComponentInfo> mLis;
	
	public ComponentAdapter(Context context,List<ComponentInfo> lis) {
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
        	convertView = LayoutInflater.from(mContext).inflate(R.layout.item_component,
					null);
        	viewHolder.componentid = (TextView) convertView.findViewById(R.id.comp1);
        	viewHolder.component = (TextView) convertView.findViewById(R.id.comp2);
        	viewHolder.type = (TextView) convertView.findViewById(R.id.comp3);
        	viewHolder.function = (TextView) convertView.findViewById(R.id.comp4);
        	viewHolder.mainfunc = (TextView) convertView.findViewById(R.id.comp5);
        	viewHolder.vendor = (TextView) convertView.findViewById(R.id.comp6);
        	viewHolder.hasdatasheet = (TextView) convertView.findViewById(R.id.comp7);
        	viewHolder.doctype = (TextView) convertView.findViewById(R.id.comp8);
        	viewHolder.docname = (TextView) convertView.findViewById(R.id.comp9);
        	viewHolder.downtimes = (TextView) convertView.findViewById(R.id.comp10);
        	convertView.setTag(viewHolder);
        } else{
        	viewHolder = (NewsViewHolder)convertView.getTag();
        }
        viewHolder.componentid.setText(""+mLis.get(position).getComponentid());
        viewHolder.component.setText(mLis.get(position).getComponent());
        viewHolder.type.setText(mLis.get(position).getType());//将int类型转化为String类型
        viewHolder.function.setText(mLis.get(position).getFunction());
        viewHolder.mainfunc.setText(mLis.get(position).getMainfunc());
        viewHolder.vendor.setText(""+mLis.get(position).getVendor());
        viewHolder.hasdatasheet.setText(""+mLis.get(position).getHasdatasheet());
        viewHolder.doctype.setText(mLis.get(position).getDoctype());
        viewHolder.docname.setText(mLis.get(position).getDocname());
        viewHolder.downtimes.setText(""+mLis.get(position).getDowntimes());
		return convertView;
	}
	
	private final class NewsViewHolder {
		public TextView componentid;
    	public TextView component;
    	public TextView type;
    	public TextView function;
    	public TextView mainfunc;
    	public TextView vendor;
    	public TextView hasdatasheet;
    	public TextView doctype;
    	public TextView docname;
    	public TextView downtimes;
	}

}


