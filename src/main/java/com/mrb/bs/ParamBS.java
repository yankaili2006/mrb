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
import com.mrb.bean.ParamBean;
import com.mrb.ibatis.SqlMap;

/**
 * @author Administrator 7:24:13 PM
 */
public class ParamBS {

	Logger log = Logger.getLogger(this.getClass());

	/**
	 * 
	 */
	public ParamBS() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 注册新配置
	 */
	public int addParam(ParamBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {

			client.startTransaction();
			Object existObj = client.queryForObject("getParamById",
					bean.getParam_id());
			client.commitTransaction();
			client.endTransaction();
			// 配置名已经存在
			if (existObj != null) {
				log.error("Param already exist, param_id = [" + bean.getParam_id()
						+ "]");
				return -2;
			}

			client.startTransaction();

			client.update("addParam", bean);
			client.commitTransaction();

			client.endTransaction();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();

			}
			return -1;
		}
		return 0;
	}

	/*
	 * 通过uid获取配置信息
	 */
	public ParamBean getParamById(String id) {
		ParamBean bean = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			bean = (ParamBean) client.queryForObject("getParamById", id);
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
	 * 获取配置列表
	 */
	public ArrayList<ParamBean> getParamList(Integer index, Integer cnt) {
		ArrayList<ParamBean> paramList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("index", index);
			map.put("cnt", cnt);
			paramList = (ArrayList<ParamBean>) client.queryForList("getParamList",
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
		return paramList;
	}

	/*
	 * 获取配置数目
	 */
	public Integer getParamCnt() {
		Integer cnt = 0;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			cnt = (Integer) client.queryForObject("getParamCnt");
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
	 * 更新配置信息
	 */
	public int updateParam(ParamBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {

			client.startTransaction();
			client.update("updateParam", bean);
			client.commitTransaction();
			client.endTransaction();

		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				log.error(e1.getMessage());
				e1.printStackTrace();
			}

			return -1;
		}
		
		return 0;
	}


	/*
	 * 删除配置信息
	 */
	public Boolean delParamById(String id) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("delParamById", id);
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

		ParamBS bs = new ParamBS();

	}

}
