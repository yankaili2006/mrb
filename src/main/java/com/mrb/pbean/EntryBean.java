package com.mrb.pbean;

import java.util.ArrayList;

public class EntryBean {

	private ArrayList cuepoints;
	private String description;
	private String title;
	private ArrayList<ZmBean> renditions;

	private long duration;
	private String password;
	private String id;
	private ArrayList subtitles;
	private ArrayList<ThumbBean> thumbnails;

	public ArrayList getCuepoints() {
		return cuepoints;
	}

	public void setCuepoints(ArrayList cuepoints) {
		this.cuepoints = cuepoints;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<ZmBean> getRenditions() {
		return renditions;
	}

	public void setRenditions(ArrayList<ZmBean> renditions) {
		this.renditions = renditions;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList getSubtitles() {
		return subtitles;
	}

	public void setSubtitles(ArrayList subtitles) {
		this.subtitles = subtitles;
	}

	public ArrayList<ThumbBean> getThumbnails() {
		return thumbnails;
	}

	public void setThumbnails(ArrayList<ThumbBean> thumbnails) {
		this.thumbnails = thumbnails;
	}

}
