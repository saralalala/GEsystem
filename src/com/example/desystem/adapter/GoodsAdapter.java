package com.example.desystem.adapter;

import java.util.List;

import com.example.desystem.R;
import com.example.desystem.domain.ComponentInfo;
import com.example.desystem.domain.GoodsInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TextView;


public class GoodsAdapter extends BaseAdapter {

	Context mContext;
	List<GoodsInfo> mLis;
	
	public GoodsAdapter(Context context,List<GoodsInfo> lis) {
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
        	convertView = LayoutInflater.from(mContext).inflate(R.layout.goods_item,
					null);//将layout文件实例化一个子空间
        	viewHolder.goodsid = (TextView) convertView.findViewById(R.id.item1);
        	viewHolder.goods = (TextView) convertView.findViewById(R.id.item2);
        	viewHolder.spec = (TextView) convertView.findViewById(R.id.item3);
        	viewHolder.price = (TextView) convertView.findViewById(R.id.item4);
        	viewHolder.number = (TextView) convertView.findViewById(R.id.item5);
        	viewHolder.catalog = (TextView) convertView.findViewById(R.id.item6);
        	viewHolder.state = (TextView) convertView.findViewById(R.id.item7);
        	viewHolder.photo = (TextView) convertView.findViewById(R.id.item8);
        	convertView.setTag(viewHolder);
        } else{
        	viewHolder = (NewsViewHolder)convertView.getTag();
        }
        viewHolder.goodsid.setText(""+mLis.get(position).getGoodsid());
        viewHolder.goods.setText(mLis.get(position).getGoods());
        viewHolder.spec.setText(mLis.get(position).getSpec());//将int类型转化为String类型
        viewHolder.price.setText(""+mLis.get(position).getPrice());
        viewHolder.number.setText(""+mLis.get(position).getNumber());
        viewHolder.catalog.setText(""+mLis.get(position).getCatalog());
        viewHolder.state.setText(""+mLis.get(position).getState());
        viewHolder.photo.setText(""+mLis.get(position).getPhoto());
		return convertView;
	}
	
	private final class NewsViewHolder {
		public TextView goodsid;
    	public TextView goods;
    	public TextView spec;
    	public TextView price;
    	public TextView number;
    	public TextView catalog;
    	public TextView state;
    	public TextView photo;
	}

}


