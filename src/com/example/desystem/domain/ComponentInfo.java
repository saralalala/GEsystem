package com.example.desystem.domain;

import android.R.integer;

public class ComponentInfo {

	private int componentid;
	private String component;
	private String type;
	private String function;
	private String mainfunc;
	private int vendor;
	private int hasdatasheet;
	private String doctype;
	private String docname;
	private int downtimes;
	
	public ComponentInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public ComponentInfo(int componentid,String component,String type,String function,String mainfunc,int vendor,int hasdatasheet,String doctype,String docname,int downtimes) {
		
		this.componentid = componentid;
		this.component = component;
		this.type = type ;
		this.function = function;
		this.mainfunc = mainfunc;
		this.vendor = vendor;
		this.hasdatasheet = hasdatasheet;
		this.doctype = doctype;
		this.docname = docname;
		this.downtimes = downtimes;
		// TODO Auto-generated constructor stub
	}

	public int getComponentid() {
		return componentid;
	}
	public void setComponentid(int componentid) {
		this.componentid = componentid;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getMainfunc() {
		return mainfunc;
	}
	public void setMainfunc(String mainfunc) {
		this.mainfunc = mainfunc;
	}
	public int getVendor() {
		return vendor;
	}
	public void setVendor(int vendor) {
		this.vendor = vendor;
	}
	public int getHasdatasheet() {
		return hasdatasheet;
	}
	public void setHasdatasheet(int hasdatasheet) {
		this.hasdatasheet = hasdatasheet;
	}
	public String getDoctype() {
		return doctype;
	}
	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}
	public String getDocname() {
		return docname;
	}
	public void setDocname(String docname) {
		this.docname = docname;
	}
	public int getDowntimes() {
		return downtimes;
	}
	public void setDowntimes(int downtimes) {
		this.downtimes = downtimes;
	}


}
