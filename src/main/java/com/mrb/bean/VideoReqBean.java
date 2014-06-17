package com.mrb.bean;

public class VideoReqBean {

	private String entryid;
	private long vcid;
	private String type;
	private String teacher;

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

	public String getEntryid() {
		return entryid;
	}

	public void setEntryid(String entryid) {
		this.entryid = entryid;
	}

	public long getVcid() {
		return vcid;
	}

	public void setVcid(long vcid) {
		this.vcid = vcid;
	}

}
