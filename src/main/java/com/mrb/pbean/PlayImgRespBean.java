package com.mrb.pbean;

import java.util.ArrayList;

import com.mrb.bean.ModelImgBean;
import com.mrb.bean.ModelImgRespSmallBean;

public class PlayImgRespBean {
	private String code;
	private String msg;
	private long num;
	private ArrayList<ModelImgRespSmallBean> imgs;

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

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}

	public ArrayList<ModelImgRespSmallBean> getImgs() {
		return imgs;
	}

	public void setImgs(ArrayList<ModelImgRespSmallBean> imgs) {
		this.imgs = imgs;
	}

}
