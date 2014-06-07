package com.mrb.pbean;

import java.util.ArrayList;

public class CityRespBean {

	private String code;
	private String msg;
	private int start;
	private int num;
	private ArrayList<City4PhoneBean> clist;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public ArrayList<City4PhoneBean> getClist() {
		return clist;
	}

	public void setClist(ArrayList<City4PhoneBean> clist) {
		this.clist = clist;
	}

}
