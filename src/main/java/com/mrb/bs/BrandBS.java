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
import com.mrb.bean.Brand2ShowBean;
import com.mrb.bean.BrandBean;
import com.mrb.ibatis.SqlMap;
import com.mrb.pbean.BrandReqBean;
import com.mrb.util.DateUtil;

/**
 * @author Administrator 7:24:13 PM
 */
public class BrandBS {

	/*
	 * 注册新品牌
	 */
	public Boolean addBrand(BrandBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();

			long bid = System.currentTimeMillis() % 1000000;
			bean.setBid(bid);
			bean.setDate(DateUtil.getNow());

			String iuri = bean.getIuri();
			if (iuri.contains("/")) {
				iuri = iuri.substring(iuri.lastIndexOf("/") + 1, iuri.length());
			}
			if (iuri.contains("\\")) {
				iuri = iuri
						.substring(iuri.lastIndexOf("\\") + 1, iuri.length());
			}
			bean.setIuri(iuri);

			client.update("addBrand", bean);
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
	 * 通过uid获取品牌信息
	 */
	public Brand2ShowBean getBrandById(Long id) {
		Brand2ShowBean bean = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			bean = (Brand2ShowBean) client.queryForObject("getBrandById", id);
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
	 * 获取品牌列表
	 */
	public ArrayList<Brand2ShowBean> getBrandList(BrandReqBean bean) {
		ArrayList<Brand2ShowBean> brandList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			Object obj = client.queryForList("getBrandList", bean);
			if (obj != null) {
				brandList = (ArrayList<Brand2ShowBean>) obj;
			}
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
		return brandList;
	}

	/*
	 * 获取品牌列表 by pid
	 */
	public ArrayList<Brand2ShowBean> getBrandListByPid(BrandReqBean bean) {
		ArrayList<Brand2ShowBean> brandList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			Object obj = client.queryForList("getBrandListByPid", bean);
			if (obj != null) {
				brandList = (ArrayList<Brand2ShowBean>) obj;
			}
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
		return brandList;
	}

	/*
	 * 获取品牌数目
	 */
	public Integer getBrandCnt() {
		Integer cnt = 0;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			cnt = (Integer) client.queryForObject("getBrandCnt");
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
		return cnt;
	}

	/*
	 * 获取品牌数目 by pid
	 */
	public Integer getBrandCntByPid(Long pid) {
		Integer cnt = 0;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			cnt = (Integer) client.queryForObject("getBrandCntByPid", pid);
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
		return cnt;
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
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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

		BrandBS bs = new BrandBS();
		BrandBean bean = new BrandBean();
		bean.setPid(283222L);
		bean.setBtitle("眼部护理系列");
		bean.setBinfo("产品简介");
		bean.setName("祛皱眼部精华");
		bean.setPrice("1280元/套");
		bean.setFunction("功效");
		bean.setSummary("其他说明");

		bs.addBrand(bean);

		// System.out.println(bs.getBrandList(0, 5).size());
	}

}
