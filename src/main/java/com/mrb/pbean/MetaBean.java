package com.mrb.pbean;

import java.util.ArrayList;

public class MetaBean {
	
	private String status; // 视频状态
	private String audio_channels; // 音轨
	private String description; // 视频简介
	private ArrayList<String> tag; // 视频标签
	private String snapshot_url; // 大截图地址
	private long file_size; // 视频文件大小
	private boolean activated; // 是否上线
	private String modified_time;// 最近修改时间
	private long height; // 视频高度
	private String created_time;// 视频创建时间
	private long video_kbps; // 视频码率
	private long audio_kbps; // 音频码率
	private long duration; // 视频时长
	private long audio_sample_rate; // 音频采样率
	private String category; // 视频分类
	private String extension; // 视频后缀
	private String title; // 视频标题
	private long framerate; // 原始视频帧率
	private String id;// 视频id
	private long width; // 视频宽度
	private String thumbnail_url;// 小截图地址
	private long category_id; // 分类id
	private String error_code; // 错误代码

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAudio_channels() {
		return audio_channels;
	}

	public void setAudio_channels(String audio_channels) {
		this.audio_channels = audio_channels;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<String> getTag() {
		return tag;
	}

	public void setTag(ArrayList<String> tag) {
		this.tag = tag;
	}

	public String getSnapshot_url() {
		return snapshot_url;
	}

	public void setSnapshot_url(String snapshot_url) {
		this.snapshot_url = snapshot_url;
	}

	public long getFile_size() {
		return file_size;
	}

	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getModified_time() {
		return modified_time;
	}

	public void setModified_time(String modified_time) {
		this.modified_time = modified_time;
	}

	public long getHeight() {
		return height;
	}

	public void setHeight(long height) {
		this.height = height;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

	public long getVideo_kbps() {
		return video_kbps;
	}

	public void setVideo_kbps(long video_kbps) {
		this.video_kbps = video_kbps;
	}

	public long getAudio_kbps() {
		return audio_kbps;
	}

	public void setAudio_kbps(long audio_kbps) {
		this.audio_kbps = audio_kbps;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getAudio_sample_rate() {
		return audio_sample_rate;
	}

	public void setAudio_sample_rate(long audio_sample_rate) {
		this.audio_sample_rate = audio_sample_rate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getFramerate() {
		return framerate;
	}

	public void setFramerate(long framerate) {
		this.framerate = framerate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getWidth() {
		return width;
	}

	public void setWidth(long width) {
		this.width = width;
	}

	public String getThumbnail_url() {
		return thumbnail_url;
	}

	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}

	public long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

}
