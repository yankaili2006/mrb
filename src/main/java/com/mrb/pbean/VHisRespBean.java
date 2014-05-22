package com.mrb.pbean;

import java.util.ArrayList;

public class VHisRespBean {
	private String Code;
	private String Msg;
	private int Start;
	private int Num;
	private ArrayList<VHisBean> Vlist;

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public int getStart() {
		return Start;
	}

	public void setStart(int start) {
		Start = start;
	}

	public int getNum() {
		return Num;
	}

	public void setNum(int num) {
		Num = num;
	}

	public ArrayList<VHisBean> getVlist() {
		return Vlist;
	}

	public void setVlist(ArrayList<VHisBean> vlist) {
		Vlist = vlist;
	}

}
