package com.mrb.pbean;

public class VBean {
	
	private String vid;
	private long vcid;
	private String title;
	private String duration;
	private long reviewcnt;
	private String type;
	private String teacher;
	private String description;
	private String date;
	private String snapshot_url;
	private String thumbnail_url;


	public String getSnapshot_url() {
		return snapshot_url;
	}

	public void setSnapshot_url(String snapshot_url) {
		this.snapshot_url = snapshot_url;
	}

	public String getThumbnail_url() {
		return thumbnail_url;
	}

	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public long getVcid() {
		return vcid;
	}

	public void setVcid(long vcid) {
		this.vcid = vcid;
	}

	public void setReviewcnt(long reviewcnt) {
		this.reviewcnt = reviewcnt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public long getReviewcnt() {
		return reviewcnt;
	}

	public void setReviewcnt(int reviewcnt) {
		this.reviewcnt = reviewcnt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


}
