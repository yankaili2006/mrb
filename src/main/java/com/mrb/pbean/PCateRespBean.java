package com.mrb.pbean;

import java.util.ArrayList;

public class PCateRespBean {

	private String code;
	private String msg;
	private int num;
	private ArrayList<PCate4PhoneBean> clist;

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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public ArrayList<PCate4PhoneBean> getClist() {
		return clist;
	}

	public void setClist(ArrayList<PCate4PhoneBean> clist) {
		this.clist = clist;
	}

}
