package com.mrb.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.google.gson.Gson;
import com.mrb.bean.EntryBean;
import com.mrb.bean.PublishBean;
import com.smvp.sdk.SmvpClient;

public class SmvpUtil {
	private final String TOKEN = "5JfFMQFjrtCD6LjUK5xw61khgkH0fUZw3K0GymjqPmUZB22Gum9rq-lDrW9fmecg";
	private final String ENTRY_ID = "592063311985780151";
	private final String PLAYER_ID = "591771262799587214";
	private final Integer WIDTH = 640;
	private final Integer HEIGHT = 480;

	/*
	 * 参数 类型 必选 描述 entry_id string 是 视频的ID player_id string 是 播放器的ID width int 是
	 * 播放器宽度 height int 是 播放器高度
	 */
	public PublishBean getPublishing(String entryId) {
		SmvpClient client = new SmvpClient(TOKEN);
		Map result = client.entries.getPublishing(entryId, PLAYER_ID, WIDTH,
				HEIGHT);
		Gson gson = new Gson();
		String json = gson.toJson(result);
		PublishBean bean = gson.fromJson(json, PublishBean.class);

		return bean;
	}

	/*
	 * 
	 * 参数 类型 必选 描述 entry_id string 是 视频的ID types object 否
	 * 默认是：‘MP4’，PC端的转码信息；‘ANDROID’：安卓端的转码信息;'IOS':Iphone端的转码信息
	 */
	public EntryBean getEntries(String entryId) {
		SmvpClient client = new SmvpClient(TOKEN);

		 Map result = client.entries.get(entryId);
		//String url = "http://api.alpha.smvp.cn/entries/json?entry_id="
		//		+ ENTRY_ID + "&types=MP4";
		//String result = getPage(url);

		//System.out.println(result);
		Gson gson = new Gson();
		String json = gson.toJson(result);
		System.out.println(json);
		EntryBean bean = gson.fromJson(json, EntryBean.class);

		return bean;
	}

	/**
	 * @param urlString
	 * @return URL对应的HTML字符串
	 */
	public String getPage(String urlString) {

		URL url;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		// 创建HTTP连接
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
			String basicAuth = "SMVP_TOKEN|" + TOKEN;
			connection.setRequestProperty("Authorization", basicAuth);
			connection.connect();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		InputStream in = null;
		int tryTimes = 5;
		boolean connectOK = false;
		while (tryTimes >= 0 && !connectOK) {
			try {
				in = connection.getInputStream();
				connectOK = true;
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				tryTimes--;
			}
		}
		if (null == in) {
			return null;
		}

		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader breader = new BufferedReader(new InputStreamReader(
					in, "GBK"));
			String str = null;
			while ((str = breader.readLine()) != null) {
				buffer.append(str + "\n");
			}
			if (breader != null) {
				breader.close();
			}
			if (in != null) {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return buffer.toString();
	}

	public static void main(String[] args) {
		SmvpUtil smvp = new SmvpUtil();
		String ENTRY_ID = "593727852328384587";
		System.out.println(smvp.getPublishing(ENTRY_ID).getFlash());
		System.out.println(smvp.getEntries(ENTRY_ID).getThumbnail_url());
	}

}
