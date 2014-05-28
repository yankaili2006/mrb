package com.mrb.pbean;

public class VDoPlayReqBean {
	
	
	private long uid;
	private String vid;
	private int finish;
	private long laststop;

	public int getFinish() {
		return finish;
	}

	public void setFinish(int finish) {
		this.finish = finish;
	}

	public long getLaststop() {
		return laststop;
	}

	public void setLaststop(long laststop) {
		this.laststop = laststop;
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

}
