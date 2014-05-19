package com.mrb.util;

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

		Gson gson = new Gson();
		String json = gson.toJson(result);
		EntryBean bean = gson.fromJson(json, EntryBean.class);
		
		return bean;
	}

	public static void main(String[] args) {
		SmvpUtil smvp = new SmvpUtil();
		String ENTRY_ID = "593727852328384587";
		System.out.println(smvp.getPublishing(ENTRY_ID).getFlash());
		System.out.println(smvp.getEntries(ENTRY_ID).getThumbnail_url());
	}

}
