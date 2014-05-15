/**
 * Feb 25, 2011 
 * BookBS.java 
 */
package com.mrb.bs;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mrb.bean.VideoBean;
import com.mrb.ibatis.SqlMap;
import com.mrb.pbean.VBean;
import com.mrb.pbean.VDetailRespBean;

/**
 * @author Administrator 7:24:13 PM
 */
public class VideoBS {

	/**
	 * 
	 */
	public VideoBS() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 注册新视频
	 */
	public Boolean addVideo(VideoBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();

			SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
			String now = dfm.format(new Date());

			bean.setDate(Long.valueOf(now));
			bean.setOpdate(Long.valueOf(now));
			bean.setOperid(1L);

			client.update("addVideo", bean);
			client.commitTransaction();

			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * 通过uid获取视频信息
	 */
	public VideoBean getVideoById(String id) {
		VideoBean bean = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			bean = (VideoBean) client.queryForObject("getVideoById", id);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	/*
	 * 获取视频列表
	 */
	public ArrayList<VideoBean> getVideoList(Integer index, Integer cnt) {
		ArrayList<VideoBean> videoList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("index", index);
			map.put("cnt", cnt);
			videoList = (ArrayList<VideoBean>) client.queryForList(
					"getVideoList", map);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return videoList;
	}

	/*
	 * 获取视频列表
	 */
	public ArrayList<VBean> getVList(Integer vcid, Integer index, Integer cnt) {
		ArrayList<VBean> videoList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("vcid", vcid);
			map.put("index", index);
			map.put("cnt", cnt);
			videoList = (ArrayList<VBean>) client.queryForList("getVList", map);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return videoList;
	}

	/*
	 * 获取视频列表
	 */
	public VDetailRespBean getVDetail(String vid, long uid) {
		VDetailRespBean bean = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			HashMap map = new HashMap();
			map.put("vid", vid);
			map.put("uid", uid);
			bean = (VDetailRespBean) client.queryForObject("getVDetail",
					map);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	/*
	 * 获取视频数目
	 */
	public Integer getVideoCnt() {
		Integer cnt = 0;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			cnt = (Integer) client.queryForObject("getVideoCnt");
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	/*
	 * 更新视频信息
	 */
	public Boolean updateVideo(VideoBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("updateVideo", bean);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * 更新视频信息
	 */
	public Boolean updateVideoPwd(VideoBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("updateVideoPwd", bean);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * 删除视频信息
	 */
	public Boolean delVideoById(String id) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("delVideoById", id);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		VideoBS bs = new VideoBS();
		VideoBean bean = new VideoBean();
		bean.setVid("ZZZZZZZ111111112222222222");
		bean.setVcid(648194L);
		bean.setStatus("Z");
		bean.setSnapshot_url("http://www.baidu.com/a.jpg");
		bean.setThumbnail_url("http://www.baidu.com/b.jpg");
		bean.setFile_size(1024L);
		bean.setActivated(0);
		bean.setModified_time("2014-04-13 12:42:32");
		bean.setCreated_time("2014-04-13 12:42:32");
		bean.setHeight(100L);
		bean.setWidth(200L);
		bean.setDuration(10000L);
		bean.setExtension(".jpg");
		bean.setTitle("这是一个测试视频");
		bean.setMp4_expires(10);
		bean.setMp4_url("1000.mp4");
		bean.setM3u8_expires(100);
		bean.setM3u8_url("m3u8.m3u8");
		bean.setPermanent_expires(11);
		bean.setPermanent_url("url.perm");
		bean.setZm_file_size(100020L);
		bean.setZm_id("100000ddddd");
		bean.setZm_type("mp4");

		bs.addVideo(bean);

		System.out.println(bs.getVideoList(0, 5).size());

	}

}
