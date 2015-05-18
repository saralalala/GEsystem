package com.example.desysytem.domain;

public class GoodsInfo {

	private int goodsid;
	private String goods;
	private String spec;
	private int price;
	private int number;
	private int catalog;
	private int state;
	private byte[] photo;
	
	public GoodsInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public GoodsInfo(int goodsid,String goods,String spec,int price,int number,int catalog,int state,byte[] photo) {
		
		this.goodsid = goodsid;
		this.goods = goods;
		this.spec = spec ;
		this.price = price;
		this.number = number;
		this.catalog = catalog;
		this.state = state;
		this.photo = photo;
		// TODO Auto-generated constructor stub
	}
	
	public int getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(int goodsid) {
		this.goodsid = goodsid;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCatalog() {
		return catalog;
	}

	public void setCatalog(int catalog) {
		this.catalog = catalog;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}


}
