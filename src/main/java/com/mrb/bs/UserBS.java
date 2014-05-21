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
	public Long addUser(UserBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		long uid = 0;
		try {
			client.startTransaction();

			uid = System.currentTimeMillis() % 1000000;
			bean.setUid(uid);
			bean.setType(0);
			bean.setStatus("Z");

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
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();

			}
			uid = -1;
		}
		return uid;
	}

	/*
	 * 用户登录
	 */
	public UserBean loginUser(UserBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		UserBean resultBean = null;
		try {
			client.startTransaction();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("phone", bean.getPhone());
			map.put("pwd", bean.getPwd());
			resultBean = (UserBean) client.queryForObject("loginUser", map);
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
		return resultBean;
	}

	/*
	 * 用户登录, 可以用户名或者密码登录 管理端
	 */
	public UserBean loginUPUser(UserBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		UserBean resultBean = null;
		try {
			client.startTransaction();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("uname", bean.getUname());
			map.put("pwd", bean.getPwd());
			resultBean = (UserBean) client.queryForObject("loginUPUser", map);
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
		return resultBean;
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
			try {
				client.endTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return bean;
	}

	/*
	 * 获取用户列表
	 */
	public ArrayList<UserBean> getUserList(Integer index, Integer cnt) {
		ArrayList<UserBean> userList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("index", index);
			map.put("cnt", cnt);
			userList = (ArrayList<UserBean>) client.queryForList("getUserList",
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
		return userList;
	}

	/*
	 * 获取用户数目
	 */
	public Integer getUserCnt() {
		Integer cnt = 0;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			cnt = (Integer) client.queryForObject("getUserCnt");
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

		UserBS bs = new UserBS();
		UserBean bean = new UserBean();
		bean.setUname("yankaili2006");
		bean.setPwd("000000");
		bean.setPhone("15901411984");
		bean.setStatus("Z");

		bs.addUser(bean);

		System.out.println(bs.getUserById(635281L).getUname());

		System.out.println(bs.getUserList(0, 5).size());

	}

}
