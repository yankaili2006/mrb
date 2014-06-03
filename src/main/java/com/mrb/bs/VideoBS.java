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
import com.mrb.bean.VideoReqBean;
import com.mrb.ibatis.SqlMap;
import com.mrb.pbean.EntryBean;
import com.mrb.pbean.MetaBean;
import com.mrb.pbean.SmvpBean;
import com.mrb.pbean.UrlBean;
import com.mrb.pbean.UrlsBean;
import com.mrb.pbean.VBean;
import com.mrb.pbean.VCollectBean;
import com.mrb.pbean.VCollectReqBean;
import com.mrb.pbean.VDetailReqBean;
import com.mrb.pbean.VDetailRespBean;
import com.mrb.pbean.VDoCollectBean;
import com.mrb.pbean.VDoCollectReqBean;
import com.mrb.pbean.VDoPlayBean;
import com.mrb.pbean.VDoPlayReqBean;
import com.mrb.pbean.VDoReviewBean;
import com.mrb.pbean.VDoReviewReqBean;
import com.mrb.pbean.VHisBean;
import com.mrb.pbean.VHisReqBean;
import com.mrb.pbean.VListReqBean;
import com.mrb.pbean.VRelateBean;
import com.mrb.pbean.VRelateReqBean;
import com.mrb.pbean.VReviewBean;
import com.mrb.pbean.VReviewReqBean;
import com.mrb.pbean.ZmBean;
import com.mrb.util.DateUtil;
import com.mrb.util.IdUtil;
import com.mrb.util.SmvpUtil;

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
	public Boolean addVideo(VideoReqBean reqBean) {

		SmvpUtil util = new SmvpUtil();
		SmvpBean smvpBean = util.smvpPost(reqBean.getEntryid());

		VideoBean vbean = new VideoBean();
		vbean.setDate(DateUtil.getNow());
		vbean.setOpdate(DateUtil.getNow());
		vbean.setOperid(1L);

		MetaBean metaBean = util.getEntries(reqBean.getEntryid());

		ArrayList<EntryBean> entries = smvpBean.getEntries();
		if (entries != null && entries.size() > 0) {
			EntryBean entryBean = entries.get(0);
			vbean.setVid(entryBean.getId());

			vbean.setVcid(0);
			vbean.setDescription(entryBean.getDescription());

			vbean.setTags("");
			vbean.setDuration(entryBean.getDuration());

			ArrayList<ZmBean> zms = entryBean.getRenditions();
			if (zms != null && zms.size() > 0) {
				ZmBean zmBean = zms.get(0);

				if ("FINISHED".equals(zmBean.getStatus())) {
					vbean.setActivated(1);
				} else {
					vbean.setActivated(0);
				}
				if ("FINISHED".equals(zmBean.getStatus())) {
					vbean.setStatus("Z");
				} else {
					vbean.setStatus("C");
				}

				vbean.setHeight(zmBean.getHeight());
				vbean.setWidth(zmBean.getWidth());
				vbean.setExtension(zmBean.getType()); // TODO 有小问题

				UrlsBean urls = zmBean.getUrls();
				UrlBean mp4Bean = urls.getMp4();
				UrlBean permantBean = urls.getPermanent();
				UrlBean m3u8Bean = urls.getM3u8();

				vbean.setMp4_expires(mp4Bean.getExpires());
				vbean.setMp4_url(mp4Bean.getUrl());
				vbean.setM3u8_expires(m3u8Bean.getExpires());
				vbean.setM3u8_url(m3u8Bean.getUrl());
				vbean.setPermanent_expires(permantBean.getExpires());
				vbean.setPermanent_url(permantBean.getUrl());

				vbean.setZm_file_size(zmBean.getFile_size());
				vbean.setFile_size(zmBean.getFile_size());
				vbean.setZm_id(zmBean.getId());
				vbean.setZm_type(zmBean.getType());
			}

			vbean.setSnapshot_url(metaBean.getSnapshot_url());
			vbean.setThumbnail_url(metaBean.getThumbnail_url());
			vbean.setCreated_time(metaBean.getCreated_time());
			vbean.setModified_time(metaBean.getModified_time());

			vbean.setVcid(reqBean.getVcid());
		}

		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();

			client.update("addVideo", vbean);
			client.commitTransaction();

			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return videoList;
	}

	/*
	 * 获取视频列表
	 */
	public ArrayList<VBean> getVList(VListReqBean bean) {
		ArrayList<VBean> videoList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			videoList = (ArrayList<VBean>) client
					.queryForList("getVList", bean);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return videoList;
	}

	/*
	 * 获取视频列表
	 */
	public VDetailRespBean getVDetail(VDetailReqBean reqBean) {
		VDetailRespBean bean = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			Object obj = client.queryForObject("getVDetail", reqBean);
			if (obj == null) {
				bean = null;
			} else {
				bean = (VDetailRespBean) obj;
			}
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return bean;
	}

	/*
	 * 获取视频评论列表
	 */
	public ArrayList<VReviewBean> getVReviewList(VReviewReqBean bean) {
		ArrayList<VReviewBean> videoList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			videoList = (ArrayList<VReviewBean>) client.queryForList(
					"getVReviewList", bean);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return videoList;
	}

	/*
	 * 获取视频播放历史列表
	 */
	public ArrayList<VHisBean> getVHisList(VHisReqBean bean) {
		ArrayList<VHisBean> videoList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			videoList = (ArrayList<VHisBean>) client.queryForList(
					"getVHisList", bean);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return videoList;
	}

	/*
	 * 获取视频播放历史列表
	 */
	public ArrayList<VRelateBean> getVRelateList(VRelateReqBean bean) {
		ArrayList<VRelateBean> videoList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			videoList = (ArrayList<VRelateBean>) client.queryForList(
					"getVRelateList", bean);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return videoList;
	}

	/*
	 * 获取视频播放历史列表
	 */
	public ArrayList<VCollectBean> getVCollectList(VCollectReqBean bean) {
		ArrayList<VCollectBean> videoList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			videoList = (ArrayList<VCollectBean>) client.queryForList(
					"getVCollectList", bean);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return videoList;
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
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}
		return true;
	}

	/*
	 * 收藏视频信息
	 */
	public Boolean doVCollect(VDoCollectReqBean reqBean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();


			VDoCollectBean bean = new VDoCollectBean();

			bean.setUcid(IdUtil.generateID());
			bean.setUid(reqBean.getUid());
			bean.setVid(reqBean.getVid());
			bean.setDate(DateUtil.getNow());

			client.update("doVCollect", bean);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}
		return true;
	}

	/*
	 * 播放视频信息
	 */
	public Boolean doVPlay(VDoPlayReqBean reqBean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();


			VDoPlayBean bean = new VDoPlayBean();

			bean.setVpid(IdUtil.generateID());
			bean.setUid(reqBean.getUid());
			bean.setVid(reqBean.getVid());
			bean.setDate(DateUtil.getNow());
			bean.setLaststop(reqBean.getLaststop());
			bean.setFinish(reqBean.getFinish());

			client.update("doVPlay", bean);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}
		return true;
	}

	/*
	 * 分享视频信息
	 */
	public Boolean doVShare(VDoCollectReqBean reqBean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();

			VDoCollectBean bean = new VDoCollectBean();

			bean.setUcid(IdUtil.generateID());
			bean.setUid(reqBean.getUid());
			bean.setVid(reqBean.getVid());
			bean.setDate(DateUtil.getNow());

			client.update("doVShare", bean);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}
		return true;
	}

	/*
	 * 视频评论信息
	 */
	public Boolean doVReview(VDoReviewReqBean reqBean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();

			VDoReviewBean bean = new VDoReviewBean();

			bean.setVrid(IdUtil.generateID());
			bean.setUid(reqBean.getUid());
			bean.setVid(reqBean.getVid());
			bean.setText(reqBean.getText());
			bean.setDate(DateUtil.getNow());

			client.update("doVReivew", bean);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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

		// bs.addVideo(bean);

		System.out.println(bs.getVideoList(0, 5).size());

	}

}
