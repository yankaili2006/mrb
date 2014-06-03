package com.mrb.pbean;

public class VDoPlayBean {
	

	private long vpid;
	private long uid;
	private String vid;
	private int finish;
	private String date;
	private long laststop;

	public long getVpid() {
		return vpid;
	}

	public void setVpid(long vpid) {
		this.vpid = vpid;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public int getFinish() {
		return finish;
	}

	public void setFinish(int finish) {
		this.finish = finish;
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getLaststop() {
		return laststop;
	}

	public void setLaststop(long laststop) {
		this.laststop = laststop;
	}

}
