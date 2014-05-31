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
import com.mrb.bean.OperateBean;
import com.mrb.ibatis.SqlMap;
import com.mrb.pbean.BrandReqBean;
import com.mrb.pbean.PReviewBean;

/**
 * @author Administrator 7:24:13 PM
 */
public class OperateBS {

	/**
	 * 
	 */
	public OperateBS() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 注册新项目
	 */
	public Boolean addOperate(OperateBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();

			long pid = System.currentTimeMillis() % 1000000;
			bean.setOpid(pid);

			SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
			String now = dfm.format(new Date());
			bean.setDate(Long.valueOf(now));

			client.update("addOperate", bean);
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
	 * 获取项目列表
	 */
	public ArrayList<OperateBean> getOperateList(Integer index, Integer cnt) {
		ArrayList<OperateBean> operateList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			HashMap map = new HashMap();
			map.put("index", index);
			map.put("cnt", cnt);
			operateList = (ArrayList<OperateBean>) client.queryForList(
					"getOperateList", map);
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
		return operateList;
	}

	/*
	 * 获取项目数目
	 */
	public Integer getOperateCnt() {
		Integer cnt = 0;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			cnt = (Integer) client.queryForObject("getOperateCnt");
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OperateBS bs = new OperateBS();

		OperateBean bean = new OperateBean();

		bs.addOperate(bean);
		System.out.println(bs.getOperateList(1, 5).size());
	}

}
