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
import com.mrb.bean.PcateBean;
import com.mrb.ibatis.SqlMap;
import com.mrb.pbean.PCate4PhoneBean;
import com.mrb.pbean.PCateReqBean;
import com.mrb.util.DateUtil;

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
	 * 注册新项目分类
	 */
	public Boolean addPcate(PcateBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();

			long uid = System.currentTimeMillis() % 1000000;
			bean.setCid(uid);

			bean.setDate(DateUtil.getNow());
			bean.setOperid(1L);

			client.update("addPcate", bean);
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
	 * 通过uid获取项目分类信息
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
	 * 获取项目分类列表
	 */
	public ArrayList<PcateBean> getPcateList(Integer index, Integer cnt) {
		ArrayList<PcateBean> pcateList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			HashMap map = new HashMap();
			map.put("index", index);
			map.put("cnt", cnt);
			pcateList = (ArrayList<PcateBean>) client.queryForList(
					"getPcateList", map);
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
		return pcateList;
	}

	

	/*
	 * 获取项目分类列表
	 */
	public ArrayList<PCate4PhoneBean> getPCate4PhonetList(PCateReqBean bean) {
		ArrayList<PCate4PhoneBean> pcateList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			pcateList = (ArrayList<PCate4PhoneBean>) client.queryForList(
					"getPCate4PhonetList", bean);
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
		return pcateList;
	}
	
	/*
	 * 获取项目分类列表记录数
	 */
	public int getPcateCnt() {
		int cnt = 0;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			cnt = (Integer) client.queryForObject("getPcateCnt");
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
	 * 更新项目分类信息
	 */
	public Boolean updatePcate(PcateBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			bean.setDate(DateUtil.getNow());
			
			client.update("updatePcate", bean);
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
	 * 删除项目分类信息
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

		PcateBS bs = new PcateBS();

		PcateBean bean = new PcateBean();
		bean.setName("疗效护理");
		bs.addPcate(bean);
		System.out.println(bs.getPcateList(0, 5).size());
	}

}
