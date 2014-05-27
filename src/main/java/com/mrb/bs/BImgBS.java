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
import com.mrb.bean.BImg2ShowBean;
import com.mrb.bean.BImgBean;
import com.mrb.ibatis.SqlMap;
import com.mrb.pbean.BImgReqBean;

/**
 * @author Administrator 7:24:13 PM
 */
public class BImgBS {

	/*
	 * 注册新品牌图片
	 */
	public Boolean addBImg(BImgBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();

			long biid = System.currentTimeMillis() % 1000000;
			bean.setBiid(biid);

			String iuri = bean.getIuri();
			if (iuri.contains("/")) {
				iuri = iuri.substring(iuri.lastIndexOf("/") + 1, iuri.length());
			}
			if (iuri.contains("\\")) {
				iuri = iuri
						.substring(iuri.lastIndexOf("\\") + 1, iuri.length());
			}
			bean.setIuri(iuri);

			client.update("addBImg", bean);
			client.commitTransaction();

			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * 通过uid获取品牌图片信息
	 */
	public BImg2ShowBean getBImgByBiid(Long id) {
		BImg2ShowBean bean = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			bean = (BImg2ShowBean) client.queryForObject("getBImgByBiid", id);
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
	 * 获取品牌图片列表
	 */
	public ArrayList<BImg2ShowBean> getBImgList(BImgReqBean bean) {
		ArrayList<BImg2ShowBean> bimgList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			Object obj = client.queryForList("getBImgList", bean);
			if (obj != null) {
				bimgList = (ArrayList<BImg2ShowBean>) obj;
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
		return bimgList;
	}

	/*
	 * 获取品牌图片列表 by pid
	 */
	public ArrayList<BImg2ShowBean> getBImgListByBid(BImgReqBean bean) {
		ArrayList<BImg2ShowBean> bimgList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			Object obj = client.queryForList("getBImgListByBid", bean);
			if (obj != null) {
				bimgList = (ArrayList<BImg2ShowBean>) obj;
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
		return bimgList;
	}

	/*
	 * 获取品牌图片数目
	 */
	public Integer getBImgCnt() {
		Integer cnt = 0;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			cnt = (Integer) client.queryForObject("getBImgCnt");
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
	 * 更新品牌图片信息
	 */
	public Boolean updateBImg(BImgBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("updateBImg", bean);
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
	 * 删除品牌图片信息
	 */
	public Boolean delBImgByBiid(Long id) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("delBImgByBiid", id);
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

		BImgBS bs = new BImgBS();
	}

}
