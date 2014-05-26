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

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mrb.bean.CityBean;
import com.mrb.ibatis.SqlMap;
import com.mrb.pbean.City4PhoneBean;

/**
 * @author Administrator 7:24:13 PM
 */
public class CityBS {
	
	Logger log = Logger.getLogger(this.getClass());

	/**
	 * 
	 */
	public CityBS() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 注册新城市
	 */
	public Long addCity(CityBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		long cid = 0;
		try {
			client.startTransaction();

			cid = System.currentTimeMillis();
			bean.setCid(cid);

			client.update("addCity", bean);
			client.commitTransaction();

			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			log.debug(e.getMessage());
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();

				log.debug(e1.getMessage());
			}
			cid = -1;
		}
		return cid;
	}

	/*
	 * 通过uid获取城市信息
	 */
	public CityBean getCityById(Long id) {
		CityBean bean = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			bean = (CityBean) client.queryForObject("getCityById", id);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return bean;
	}

	/*
	 * 获取城市列表
	 */
	public ArrayList<CityBean> getCityList(Integer index, Integer cnt) {
		ArrayList<CityBean> CityList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("index", index);
			map.put("cnt", cnt);
			CityList = (ArrayList<CityBean>) client.queryForList("getCityList",
					map);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return CityList;
	}

	/*
	 * 获取城市列表 for phone
	 */
	public ArrayList<City4PhoneBean> getCList(Integer level, Integer index,
			Integer cnt) {
		ArrayList<City4PhoneBean> CityList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("level", level);
			map.put("index", index);
			map.put("cnt", cnt);
			CityList = (ArrayList<City4PhoneBean>) client.queryForList(
					"getCList", map);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return CityList;
	}

	/*
	 * 获取城市数目
	 */
	public Integer getCityCnt() {
		Integer cnt = 0;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			cnt = (Integer) client.queryForObject("getCityCnt");
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return cnt;
	}

	/*
	 * 更新城市信息
	 */
	public Boolean updateCity(CityBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("updateCity", bean);
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
	 * 删除城市信息
	 */
	public Boolean delCityById(CityBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("delCityById", bean);
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CityBS bs = new CityBS();
		CityBean bean = new CityBean();
		bean.setName("兰州");
		bean.setLevel(1);

		bs.addCity(bean);
	}

}
