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
import com.mrb.bean.BrandBean;
import com.mrb.ibatis.SqlMap;

/**
 * @author Administrator 7:24:13 PM
 */
public class BrandBS {

	/**
	 * 
	 */
	public BrandBS() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 注册新品牌
	 */
	public Boolean addBrand(BrandBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();

			long bid = System.currentTimeMillis() % 1000000;
			bean.setBid(bid);
			SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
			String now = dfm.format(new Date());
			bean.setDate(Long.valueOf(now));
			bean.setOperid(1L);

			client.update("addBrand", bean);
			client.commitTransaction();

			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * 通过uid获取品牌信息
	 */
	public BrandBean getBrandById(Long id) {
		BrandBean bean = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			bean = (BrandBean) client.queryForObject("getBrandById", id);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	/*
	 * 获取品牌列表
	 */
	public ArrayList<BrandBean> getBrandList() {
		ArrayList<BrandBean> brandList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			brandList = (ArrayList<BrandBean>) client.queryForList("getBrandList");
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return brandList;
	}

	/*
	 * 更新品牌信息
	 */
	public Boolean updateBrand(BrandBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("updateBrand", bean);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/*
	 * 更新品牌信息
	 */
	public Boolean updateBrandPwd(BrandBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("updateBrandPwd", bean);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * 删除品牌信息
	 */
	public Boolean delBrandById(Long id) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("delBrandById", id);
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

		BrandBS bs = new BrandBS();
		BrandBean bean = new BrandBean();
		bean.setPid(1000L);
		bean.setBtitle("眼部护理系列");
		bean.setBinfo("产品简介");
		bean.setName("祛皱眼部精华");
		bean.setPrice("1280元/套");
		bean.setFunction("功效");
		bean.setSummary("其他说明");

		bs.addBrand(bean);

		System.out.println(bs.getBrandList().size());
	}

}
