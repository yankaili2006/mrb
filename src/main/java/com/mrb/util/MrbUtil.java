package com.mrb.util;

public class MrbUtil {

	private static String siteUrl = "http://115.28.183.145:8080/mrb";
	private static String imgUrl = siteUrl + "/files";

	public static String getSiteUrl() {
		return siteUrl;
	}

	public static void setSiteUrl(String siteUrl) {
		MrbUtil.siteUrl = siteUrl;
	}

	public static String getImgUrl() {
		return imgUrl;
	}

	public static void setImgUrl(String imgUrl) {
		MrbUtil.imgUrl = imgUrl;
	}

}
