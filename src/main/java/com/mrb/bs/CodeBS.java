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
import com.mrb.bean.CodeBean;
import com.mrb.ibatis.SqlMap;

/**
 * @author Administrator 7:24:13 PM
 */
public class CodeBS {

	/**
	 * 
	 */
	public CodeBS() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 注册新校验码
	 */
	public Boolean addCode(CodeBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();

			long uid = System.currentTimeMillis() % 1000000;
			bean.setUid(uid);
			bean.setStatus("0");

			SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
			String now = dfm.format(new Date());
			bean.setDate(Long.valueOf(now));

			client.update("addCode", bean);
			client.commitTransaction();

			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * 校验码
	 */
	public Integer chkCode(CodeBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		Integer cnt = 0;
		try {
			client.startTransaction();
			SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
			String now = dfm.format(new Date());
			// 有效时间五分钟
			Long left = Long.valueOf(now) - 300;
			bean.setDate(left);
			
			cnt = (Integer) client.queryForObject("chkCode", bean);
			client.commitTransaction();

			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			return cnt;
		}
		return cnt;
	}

	/*
	 * 通过cid获取校验码信息
	 */
	public CodeBean getCodeById(Long id) {
		CodeBean bean = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			bean = (CodeBean) client.queryForObject("getCodeById", id);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	/*
	 * 获取校验码列表
	 */
	public ArrayList<CodeBean> getCodeList(Integer index, Integer cnt) {
		ArrayList<CodeBean> CodeList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			HashMap map = new HashMap();
			map.put("index", index);
			map.put("cnt", cnt);
			CodeList = (ArrayList<CodeBean>) client.queryForList("getCodeList",
					map);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CodeList;
	}

	/*
	 * 获取校验码数目
	 */
	public Integer getCodeCnt() {
		Integer cnt = 0;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			cnt = (Integer) client.queryForObject("getCodeCnt");
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	/*
	 * 更新校验码信息
	 */
	public Boolean updateCode(CodeBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("updateCode", bean);
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
		CodeBS bs = new CodeBS();

		CodeBean bean = new CodeBean();
		bean.setCode("001003");
		bean.setPhone("15901411984");
		bean.setStatus("0");

		bs.addCode(bean);
		System.out.println(bs.getCodeList(1, 5).size());
	}

}
