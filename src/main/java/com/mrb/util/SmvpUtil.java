package com.mrb.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

import com.google.gson.Gson;
import com.mrb.bean.PublishBean;
import com.mrb.pbean.MetaBean;
import com.mrb.pbean.SmvpBean;
import com.smvp.sdk.SmvpClient;

public class SmvpUtil {
	private final String TOKEN = "5JfFMQFjrtCD6LjUK5xw61khgkH0fUZw3K0GymjqPmUZB22Gum9rq-lDrW9fmecg";
	private final String ENTRY_ID = "592063311985780151";
	private final String PLAYER_ID = "591771262799587214";
	private final Integer WIDTH = 640;
	private final Integer HEIGHT = 480;
	private final String URL = "http://api.alpha.smvp.cn/entries/json";

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

	public MetaBean getEntries(String entryId) {
		SmvpClient client = new SmvpClient(TOKEN);

		Map result = client.entries.get(entryId);

		// System.out.println(result);
		Gson gson = new Gson();
		String json = gson.toJson(result);
		System.out.println(json);
		MetaBean bean = gson.fromJson(json, MetaBean.class);

		return bean;
	}

	public SmvpBean smvpPost(String entryId) {
		SmvpBean smvp = null;

		URL url = null;
		HttpURLConnection conn = null;
		try {
			url = new URL(URL);
			conn = (HttpURLConnection) url.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		conn.setDoOutput(true); // 允许向服务器输出数据
		conn.setDoInput(true); // 允许接收服务器数据
		try {
			conn.setRequestMethod("POST");
		} catch (ProtocolException e1) {
			e1.printStackTrace();
			return null;
		}
		conn.setUseCaches(false); // Post 请求不能使用缓存
		conn.setConnectTimeout(5000);

		// 参数前面不能加？号
		String urlParas = "entry_id=" + entryId
				+ "&types=[\"MP4\",\"ANDROID\",\"IOS\"]";
		byte[] entity = urlParas.getBytes();

		// 设置请求参数
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded"); // 实体参数类型
		conn.setRequestProperty("Content-Length", entity.length + ""); // 实体参数长度
		String basicAuth = "SMVP_TOKEN|" + TOKEN;
		conn.setRequestProperty("Authorization", basicAuth);

		// 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
		// 要注意的是connection.getOutputStream会隐含的进行connect。
		try {
			conn.connect();
			conn.getOutputStream().write(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		try {
			if (conn.getResponseCode() == 200) {
				InputStream is = conn.getInputStream();
				// 将输入流转换成字符串
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				String json = baos.toString();
				baos.close();
				is.close();

				System.out.println(json);
				Gson gson = new Gson();
				smvp = gson.fromJson(json, SmvpBean.class);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return smvp;
	}

	public static void main(String[] args) {
		SmvpUtil smvp = new SmvpUtil();
		// System.out.println(smvp.getPublishing(ENTRY_ID).getFlash());
		// System.out.println(smvp.getEntries(ENTRY_ID).getThumbnail_url());
		smvp.smvpPost(smvp.ENTRY_ID);
	}

}
