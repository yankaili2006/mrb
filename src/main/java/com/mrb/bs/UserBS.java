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
import com.mrb.bean.UserBean;
import com.mrb.ibatis.SqlMap;
import com.mrb.util.DateUtil;

/**
 * @author Administrator 7:24:13 PM
 */
public class UserBS {

	Logger log = Logger.getLogger(this.getClass());

	/**
	 * 
	 */
	public UserBS() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 注册新用户
	 */
	public int addUser(UserBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {

			client.startTransaction();
			Object existObj = client.queryForObject("getUserByName",
					bean.getUname());
			client.commitTransaction();
			client.endTransaction();
			// 用户名已经存在
			if (existObj != null) {
				log.error("User already exist, uname = [" + bean.getUname()
						+ "]");
				return -2;
			}

			client.startTransaction();
			existObj = client.queryForObject("getUserByPhone", bean.getPhone());
			client.commitTransaction();
			client.endTransaction();
			// 手机号已经存在
			if (existObj != null) {
				log.error("User already exist, phone = [" + bean.getPhone()
						+ "]");
				return -3;
			}

			client.startTransaction();
			bean.setType(0);
			bean.setStatus("Z");

			bean.setDate(DateUtil.getNow());
			bean.setOpdate(DateUtil.getNow());
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
			return -1;
		}
		return 0;
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
	 * 通过uname获取用户信息
	 */
	public UserBean getUserByName(String name) {
		UserBean bean = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			bean = (UserBean) client.queryForObject("getUserByName", name);
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
	 * 通过uname获取用户信息 for update
	 */
	public UserBean getUserByName4Update(UserBean reqBean) {
		UserBean bean = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			bean = (UserBean) client.queryForObject("getUserByName4Update",
					reqBean);
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
	 * 通过phone获取用户信息
	 */
	public UserBean getUserByPhone(String phone) {
		UserBean bean = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			bean = (UserBean) client.queryForObject("getUserByPhone", phone);
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
	 * 通过phone获取用户信息 for update
	 */
	public UserBean getUserByPhone4Update(UserBean reqBean) {
		UserBean bean = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			bean = (UserBean) client.queryForObject("getUserByPhone4Update",
					reqBean);
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
	public int updateUser(UserBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {

			client.startTransaction();
			Object existObj = client.queryForObject("getUserByName4Update",
					bean);
			client.commitTransaction();
			client.endTransaction();
			// 用户名已经存在
			if (existObj != null) {
				log.error("User already exist, uname = [" + bean.getUname()
						+ "]");
				return -2;
			}

			client.startTransaction();
			existObj = client.queryForObject("getUserByPhone4Update",
					bean);
			client.commitTransaction();
			client.endTransaction();
			// 手机号已经存在
			if (existObj != null) {
				log.error("User already exist, phone = [" + bean.getPhone()
						+ "]");
				return -3;
			}

			client.startTransaction();
			client.update("updateUser", bean);
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
