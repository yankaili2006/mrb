package com.mrb.pbean;

public class VReviewBean {


	private long vrid;
	private String vid;
	private long uid;
	private String uname;
	private String text;
	private String date;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public long getVrid() {
		return vrid;
	}

	public void setVrid(long vrid) {
		this.vrid = vrid;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
