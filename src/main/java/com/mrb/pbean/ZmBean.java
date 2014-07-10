package com.mrb.pbean;

public class ZmBean {

	private String status;
	private String error_code;
	private String name;
	private Boolean _default;
	private long kbps;
	private long framerate;
	private long height;
	private long width;
	private String setting_id;
	private UrlsBean urls;

	private String url;

	private long file_size;
	private String type;
	private String id;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean get_default() {
		return _default;
	}

	public void set_default(Boolean _default) {
		this._default = _default;
	}

	public long getKbps() {
		return kbps;
	}

	public void setKbps(long kbps) {
		this.kbps = kbps;
	}

	public long getFramerate() {
		return framerate;
	}

	public void setFramerate(long framerate) {
		this.framerate = framerate;
	}

	public long getHeight() {
		return height;
	}

	public void setHeight(long height) {
		this.height = height;
	}

	public long getWidth() {
		return width;
	}

	public void setWidth(long width) {
		this.width = width;
	}

	public String getSetting_id() {
		return setting_id;
	}

	public void setSetting_id(String setting_id) {
		this.setting_id = setting_id;
	}

	public UrlsBean getUrls() {
		return urls;
	}

	public void setUrls(UrlsBean urls) {
		this.urls = urls;
	}

	public long getFile_size() {
		return file_size;
	}

	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
