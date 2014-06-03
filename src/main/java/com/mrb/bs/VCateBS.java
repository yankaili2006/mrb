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
import com.mrb.bean.VCateBean;
import com.mrb.ibatis.SqlMap;
import com.mrb.util.DateUtil;

/**
 * @author Administrator 7:24:13 PM
 */
public class VCateBS {

	/**
	 * 
	 */
	public VCateBS() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 注册新视频分类
	 */
	public Boolean addVCate(VCateBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();

			long vcid = System.currentTimeMillis() % 1000000;
			bean.setVcid(vcid);
			bean.setDate(DateUtil.getNow());
			bean.setOperid(1L);

			client.update("addVCate", bean);
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
	 * 通过vcid获取视频分类信息
	 */
	public VCateBean getVCateById(Long id) {
		VCateBean bean = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			bean = (VCateBean) client.queryForObject("getVCateById", id);
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
	 * 获取视频分类列表
	 */
	public ArrayList<VCateBean> getVCateList(Integer index, Integer cnt) {
		ArrayList<VCateBean> vcateList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("index", index);
			map.put("cnt", cnt);
			vcateList = (ArrayList<VCateBean>) client.queryForList(
					"getVCateList", map);
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
		return vcateList;
	}

	/*
	 * 获取视频分类数目
	 */
	public Integer getVCateCnt() {
		Integer cnt = 0;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			cnt = (Integer) client.queryForObject("getVCateCnt");
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
	 * 更新视频分类信息
	 */
	public Boolean updateVCate(VCateBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("updateVCate", bean);
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
	 * 删除视频分类信息
	 */
	public Boolean delVCateById(Long id) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("delVCateById", id);
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

		VCateBS bs = new VCateBS();
		VCateBean bean = new VCateBean();
		bean.setName("专家课堂");

		bs.addVCate(bean);

		System.out.println(bs.getVCateList(0, 5).size());

	}

}
