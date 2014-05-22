package com.mrb.pbean;

import java.util.ArrayList;

public class VRelateRespBean {

	private String code;
	private String msg;
	private int start;
	private int num;
	private ArrayList<VRelateBean> rlist;

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

	public ArrayList<VRelateBean> getRlist() {
		return rlist;
	}

	public void setRlist(ArrayList<VRelateBean> rlist) {
		this.rlist = rlist;
	}

}
