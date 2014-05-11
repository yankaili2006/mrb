/**
 * Feb 25, 2011 
 * BookBS.java 
 */
package com.mrb.bs;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mrb.bean.PcateBean;
import com.mrb.ibatis.SqlMap;

/**
 * @author Administrator 7:24:13 PM
 */
public class PcateBS {

	/**
	 * 
	 */
	public PcateBS() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 注册新用户
	 */
	public Boolean addPcate(PcateBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();

			long uid = System.currentTimeMillis() % 1000000;
			bean.setCid(uid);
			
			SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
			String now = dfm.format(new Date());
			bean.setDate(Long.valueOf(now));
			bean.setOperid(1L);

			client.update("addPcate", bean);
			client.commitTransaction();

			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * 通过uid获取用户信息
	 */
	public PcateBean getPcateById(Long id) {
		PcateBean bean = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			bean = (PcateBean) client.queryForObject("getPcateById", id);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	/*
	 * 获取用户列表
	 */
	public ArrayList<PcateBean> getPcateList() {
		ArrayList<PcateBean> pcateList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			pcateList = (ArrayList<PcateBean>) client.queryForList("getPcateList");
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pcateList;
	}

	/*
	 * 更新用户信息
	 */
	public Boolean updatePcate(PcateBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
			String now = dfm.format(new Date());
			bean.setDate(Long.valueOf(now));
			
			client.update("updatePcate", bean);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * 删除用户信息
	 */
	public Boolean delPcateById(Long id) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("delPcateById", id);
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

		PcateBS bs = new PcateBS();

		PcateBean bean = new PcateBean();
		bean.setName("疗效护理");
		bs.addPcate(bean);
		System.out.println(bs.getPcateList().size());
	}

}
