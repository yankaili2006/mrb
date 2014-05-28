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
import com.mrb.bean.CodeBean;
import com.mrb.ibatis.SqlMap;
import com.mrb.pbean.FeedBean;

/**
 * @author Administrator 7:24:13 PM
 */
public class OtherBS {

	/**
	 * 
	 */
	public OtherBS() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 
	 */
	public Boolean addFeed(FeedBean bean) {
		SqlMapClient client = SqlMap.getSqlMapInstance();
		try {
			client.startTransaction();

			long fid = System.currentTimeMillis() % 1000000;
			bean.setFid(fid);

			SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
			String now = dfm.format(new Date());
			bean.setDate(Long.valueOf(now));

			client.update("addFeed", bean);
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OtherBS bs = new OtherBS();

	}

}
