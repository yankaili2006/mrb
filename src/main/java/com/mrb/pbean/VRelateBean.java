package com.mrb.pbean;

public class VRelateBean {

	private int vrid;
	private String vid;
	private String iuri;
	private String title;
	private long duration;
	private int reviewcnt;
	private String date;

	public int getVrid() {
		return vrid;
	}

	public void setVrid(int vrid) {
		this.vrid = vrid;
	}

	public int getReviewcnt() {
		return reviewcnt;
	}

	public void setReviewcnt(int reviewcnt) {
		this.reviewcnt = reviewcnt;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getIuri() {
		return iuri;
	}

	public void setIuri(String iuri) {
		this.iuri = iuri;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
