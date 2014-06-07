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
import com.mrb.bean.ModelImgBean;
import com.mrb.ibatis.SqlMap;
import com.mrb.pbean.ModelImgReqBean;
import com.mrb.pbean.PlayImgReqBean;

/**
 * @author Administrator 7:24:13 PM
 */
public class ModelImgBS {
	/*
	 * 注册新模块图片
	 */
	public Boolean addModelImg(ModelImgBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();

			long iid = System.currentTimeMillis() % 1000000;
			bean.setIid(iid);

			String iuri = bean.getIuri();
			if (iuri.contains("/")) {
				iuri = iuri.substring(iuri.lastIndexOf("/") + 1, iuri.length());
			}
			if (iuri.contains("\\")) {
				iuri = iuri
						.substring(iuri.lastIndexOf("\\") + 1, iuri.length());
			}
			bean.setIuri(iuri);

			client.update("addModelImg", bean);
			client.commitTransaction();

			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * 通过uid获取模块图片信息
	 */
	public ModelImgBean getModelImgByIid(Long iid) {
		ModelImgBean bean = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			bean = (ModelImgBean) client.queryForObject("getModelImgByIid", iid);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return bean;
	}

	/*
	 * 获取模块图片列表
	 */
	public ArrayList<ModelImgBean> getModelImgList(int start, int num) {
		ArrayList<ModelImgBean> modelimgList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("start", start);
			map.put("num", num);
			Object obj = client.queryForList("getModelImgList", map);
			if (obj != null) {
				modelimgList = (ArrayList<ModelImgBean>) obj;
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
		return modelimgList;
	}
	
	/*
	 * 获取模块图片列表
	 */
	public ArrayList<ModelImgBean> getModelImgListByModel(PlayImgReqBean bean) {
		ArrayList<ModelImgBean> modelimgList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			Object obj = client.queryForList("getModelImgListByModel", bean);
			if (obj != null) {
				modelimgList = (ArrayList<ModelImgBean>) obj;
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
		return modelimgList;
	}

	/*
	 * 获取模块图片数目
	 */
	public Integer getModelImgCnt() {
		Integer cnt = 0;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			cnt = (Integer) client.queryForObject("getModelImgCnt");
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
		return cnt;
	}

	/*
	 * 更新模块图片信息
	 */
	public Boolean updateModelImg(ModelImgBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("updateModelImg", bean);
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
	 * 删除模块图片信息
	 */
	public Boolean delModelImgByIid(Long iid) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("delModelImgByIid", iid);
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

		ModelImgBS bs = new ModelImgBS();
	}

}
