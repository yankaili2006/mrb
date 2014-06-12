package com.mrb.pbean;

public class VDoReviewBean {
	
	
	private long vrid;
	private long uid;
	private String vid;
	private String text;
	private String date;


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public long getVrid() {
		return vrid;
	}

	public void setVrid(long vrid) {
		this.vrid = vrid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

}
