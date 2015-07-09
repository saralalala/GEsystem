package com.example.desystem.domain;

import android.R.integer;

public class UserFuncInfo {
	private int picid;
	private String testname;
	private int arrow;
	
	public UserFuncInfo() {
		super();

	}
	
	public UserFuncInfo(int picid,String testname,int arrow) {
		super();
		this.picid = picid;
		this.testname = testname;
		this.arrow = arrow;

	}

	public int getArrow() {
		return arrow;
	}

	public void setArrow(int arrow) {
		this.arrow = arrow;
	}

	public void setTestPic(int picid) {
		this.picid = picid;
	}

	public int getTestPic() {
		return picid;
	}
	
	public String getTestName() {
		return testname;
	}
	public void setTestName(String testname) {
		this.testname = testname;
	}	
	
}
