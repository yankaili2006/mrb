/**
 * Feb 25, 2011 
 * BookBS.java 
 */
package com.mrb.pbs;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mrb.bean.UserBean;
import com.mrb.bs.UserBS;
import com.mrb.ibatis.SqlMap;
import com.mrb.pbean.PwdBean;
import com.mrb.util.DateUtil;

/**
 * @author Administrator 7:24:13 PM
 */
public class PwdBS {

	/**
	 * 
	 */
	public PwdBS() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 注册新密码
	 */
	public Integer chgPwd(PwdBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		Integer cnt = 0;
		try {
			client.startTransaction();
			cnt = (Integer) client.queryForObject("chkPwd", bean);
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
			return -100;
		}

		// 未找到该用户，或者密码校验失败
		if (cnt <= 0) {
			return -100;
		}

		UserBean userBean = new UserBean();
		userBean.setUid(bean.getUid());
		userBean.setPwd(bean.getNewpwd());
		client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("updateUserPwd", userBean);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return -200;
		}

		return 0;
	}

	/*
	 * 更新密码信息
	 */
	public Integer setPwd(PwdBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		UserBean userBean = new UserBean();
		userBean.setUid(bean.getUid());
		userBean.setPwd(bean.getNewpwd());

		UserBS userBS = new UserBS();
		if (userBS.getUserById(bean.getUid()) == null) {
			return -100;
		}

		userBean.setOpdate(DateUtil.getNow());
		try {
			client.startTransaction();
			client.update("updateUserPwd", userBean);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return -200;
		}
		return 0;
	}

	/*
	 * 注册新密码
	 */
	public String getPwdByUid(Long uid) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		String pwd = null;
		try {
			client.startTransaction();
			pwd = (String) client.queryForObject("getPwdByUid", uid);
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

		return pwd;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PwdBS bs = new PwdBS();

		PwdBean bean = new PwdBean();
		bean.setUid(1L);
		bean.setOldpwd("1");
		bean.setOldpwd("1024");

		bs.chgPwd(bean);
	}

}
