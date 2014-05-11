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
import com.mrb.bean.Book2ShowBean;
import com.mrb.bean.UserBean;
import com.mrb.ibatis.SqlMap;

/**
 * @author Administrator 7:24:13 PM
 */
public class UserBS {

	/**
	 * 
	 */
	public UserBS() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 注册新用户
	 */
	public Boolean addUser(UserBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			
			long uid = System.currentTimeMillis() % 1000000;
			bean.setUid(uid);
			bean.setType(0);
			SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
			String now = dfm.format(new Date());
			bean.setDate(Long.valueOf(now));
			bean.setOpdate(Long.valueOf(now));
			bean.setOperid(1L);

			client.update("addUser", bean);
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
	public UserBean getUserById(Long id) {
		UserBean bean = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			bean = (UserBean) client.queryForObject("getUserById", id);
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
	public ArrayList<UserBean> getUserList() {
		ArrayList<UserBean> userList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			userList = (ArrayList<UserBean>) client.queryForList("getUserList");
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	/*
	 * 更新用户信息
	 */
	public Boolean updateUser(UserBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("updateUser", bean);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/*
	 * 更新用户信息
	 */
	public Boolean updateUserPwd(UserBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("updateUserPwd", bean);
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
	public Boolean delUserById(Long id) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("delUserById", id);
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

		UserBS bs = new UserBS();
		UserBean bean = new UserBean();
		bean.setUname("yankaili2006");
		bean.setPwd("000000");
		bean.setPhone("15901411984");
		bean.setStatus("Z");

		bs.addUser(bean);

		System.out.println(bs.getUserById(635281L).getUname());

		System.out.println(bs.getUserList().size());

	}

}
