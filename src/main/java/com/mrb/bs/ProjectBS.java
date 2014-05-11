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
import com.mrb.bean.ProjectBean;
import com.mrb.ibatis.SqlMap;

/**
 * @author Administrator 7:24:13 PM
 */
public class ProjectBS {

	/**
	 * 
	 */
	public ProjectBS() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 注册新项目
	 */
	public Boolean addProject(ProjectBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();

			long pid = System.currentTimeMillis() % 1000000;
			bean.setPid(pid);

			SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
			String now = dfm.format(new Date());
			bean.setDate(Long.valueOf(now));
			bean.setOperid(1L);

			client.update("addProject", bean);
			client.commitTransaction();

			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * 通过uid获取项目信息
	 */
	public ProjectBean getProjectById(Long id) {
		ProjectBean bean = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			bean = (ProjectBean) client.queryForObject("getProjectById", id);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	/*
	 * 获取项目列表
	 */
	public ArrayList<ProjectBean> getProjectList() {
		ArrayList<ProjectBean> projectList = null;
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			projectList = (ArrayList<ProjectBean>) client.queryForList("getProjectList");
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return projectList;
	}

	/*
	 * 更新项目信息
	 */
	public Boolean updateProject(ProjectBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("updateProject", bean);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/*
	 * 更新项目信息
	 */
	public Boolean updateProjectPwd(ProjectBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("updateProjectPwd", bean);
			client.commitTransaction();
			client.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * 删除项目信息
	 */
	public Boolean delProjectById(Long id) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();
			client.update("delProjectById", id);
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
		ProjectBS bs = new ProjectBS();
		
		ProjectBean bean = new ProjectBean();
		bean.setName("欧莱雅");
		bean.setLevel("高端客户");
		bean.setArea("北京");
		bean.setBuild("1988年");
		bean.setIid(10000L);

		bs.addProject(bean);
		System.out.println(bs.getProjectList().size());
	}

}
