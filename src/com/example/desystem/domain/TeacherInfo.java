package com.example.desystem.domain;

import java.sql.Date;

import android.R.integer;

public class TeacherInfo{
	
	int teacherid;
	String name;
	int gender;
	String workno;
	int title;
	int duty;
	String birthday;
	int branch;
	String workphone;
	String homephone;
	String mobile;
	String passwd;
	String email;
	int hasstu;
	int state;
	String alias;
	
	public TeacherInfo(int teacherid, String name, int gender, String workno,
			int title, int duty, String birthday, int branch, String workphone,
			String homephone, String mobile, String passwd, String email,
			int hasstu, int state, String alias) {
		
		super();
		this.teacherid = teacherid;
		this.name = name;
		this.gender = gender;
		this.workno = workno;
		this.title = title;
		this.duty = duty;
		this.birthday = birthday;
		this.branch = branch;
		this.workphone = workphone;
		this.homephone = homephone;
		this.mobile = mobile;
		this.passwd = passwd;
		this.email = email;
		this.hasstu = hasstu;
		this.state = state;
		this.alias = alias;
	}

	public int getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getWorkno() {
		return workno;
	}

	public void setWorkno(String workno) {
		this.workno = workno;
	}

	public int getTitle() {
		return title;
	}

	public void setTitle(int title) {
		this.title = title;
	}

	public int getDuty() {
		return duty;
	}

	public void setDuty(int duty) {
		this.duty = duty;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getBranch() {
		return branch;
	}

	public void setBranch(int branch) {
		this.branch = branch;
	}

	public String getWorkphone() {
		return workphone;
	}

	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}

	public String getHomephone() {
		return homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getHasstu() {
		return hasstu;
	}

	public void setHasstu(int hasstu) {
		this.hasstu = hasstu;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	
	
}