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
import com.mrb.bean.PwdBean;
import com.mrb.bean.UserBean;
import com.mrb.ibatis.SqlMap;

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
	public Boolean chgPwd(PwdBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		Integer cnt = 0;
		try {
			client.startTransaction();
			cnt = (Integer) client.queryForObject("chkPwd", bean);
			client.commitTransaction();

			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			cnt = 0;
		}

		if (cnt <= 0) {
			return false;
		} else {
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
				return false;
			}
		}

		return true;
	}

	/*
	 * 更新密码信息
	 */
	public Boolean setPwd(PwdBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		UserBean userBean = new UserBean();
		userBean.setUid(bean.getUid());
		userBean.setPwd(bean.getNewpwd());

		SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
		String now = dfm.format(new Date());
		userBean.setOpdate(Long.valueOf(now));
		try {
			client.startTransaction();
			client.update("updateUserPwd", userBean);
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
		PwdBS bs = new PwdBS();

		PwdBean bean = new PwdBean();
		bean.setUid(1L);
		bean.setOldpwd("1");
		bean.setOldpwd("1024");

		bs.chgPwd(bean);
	}

}
