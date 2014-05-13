/**
 * Feb 24, 2011 
 * RegisterAction.java 
 */
package com.mrb.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.mrb.bean.UserBean;
import com.mrb.bs.UserBS;
import com.mrb.form.JsonForm;

/**
 * @author Administrator 9:06:26 PM
 * 
 * 用户注册的Action
 */
public class UserAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) {

		Logger log = Logger.getLogger(this.getClass());
		String result = "ok";
		String act = ((JsonForm) form).getAct();
		String msg = ((JsonForm) form).getMsg();
		log.debug("act = " + act + ", msg = " + msg);

		UserBS bs = new UserBS();
		Gson gson = new Gson();
		if ("list".equals(act)) { // 获取用户列表 for ajax

			ArrayList<UserBean> ulist = bs.getUserList();
			log.debug("ulist.size(): " + ulist.size());
			req.setAttribute("ulist", ulist);

			StringBuilder html = new StringBuilder(
					"<table class=\"table\"><thead><tr><th>用户ID</th><th>用户名</th><th>手机号</th><th>注册日期</th><th>状态</th><th style=\"width: 26px;\"></th></tr></thead><tbody>");
			if (ulist != null && ulist.size() > 0) {
				for (int i = 0; i < ulist.size(); i++) {
					UserBean bean = (UserBean) ulist.get(i);
					html.append("<tr><td>" + bean.getUid() + "</td><td>"
							+ bean.getUname() + "</td><td>" + bean.getPhone()
							+ "</td><td>" + bean.getDate() + "</td>");
					if ("Z".equals(bean.getStatus())) {
						html.append("</td><td>正常</td>");
					} else if ("C".equals(bean.getStatus())) {
						html.append("</td><td>注销</td>");
					} else {
						html.append("</td><td>状态异常</td>");
					}
					html
							.append("<td><a href=\"javascript:void(0)\"}\" onclick=\"gotoedit(this);\"><i class=\"icon-pencil\"></i></a>&nbsp;&nbsp;");
					html
							.append("<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\" onclick=\"setuid(this);\"><i class=\"icon-remove\"></i></a></td></tr>");
				}

			} else {
				html
						.append("<tr><td>没有用户记录！</td><td></td><td></td><td></td></tr>");
			}
			html.append("</tbody></table>");
			result = html.toString();
			
		} else if ("add".equals(act)) { // 添加用户 for 跳转
			UserBean bean = (UserBean) gson.fromJson(msg, UserBean.class);
			if (bean != null) {
				bs.addUser(bean);
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {
				return mapping.findForward("list");
			}
			else{
				req.setAttribute("user", bean);
				req.setAttribute("result", result);
				return mapping.findForward("add");
			}
		} else if ("edit".equals(act)) {// 编辑用户 for 跳转
			UserBean user = null;
			UserBean bean = (UserBean) gson.fromJson(msg, UserBean.class);
			if (bean != null) {
				log.info("uid = [" + bean.getUid() + "]");
				user = bs.getUserById(bean.getUid());
			} else {
				log.error("bean is null");
				result = "参数非法";
			}

			if (user != null) {
				req.setAttribute("user", user);
			} else {
				result = "未找到该用户";
			}

			if ("ok".equals(result)) {
				return mapping.findForward(act);
			}
			else{
				req.setAttribute("result", result);
				return mapping.findForward("list");			
			}
			
		} else if ("del".equals(act)) { // 删除用户 for ajax
			UserBean bean = (UserBean) gson.fromJson(msg, UserBean.class);
			if (bean != null) {
				log.info("uid = [" + bean.getUid() + "]");
				bs.delUserById(bean.getUid());
			} else {
				result = "参数非法";
			}
			
		} else if ("update".equals(act)) { // 更新用户 for 跳转
			UserBean bean = (UserBean) gson.fromJson(msg, UserBean.class);
			if (bean != null) {
				log.info("uid = [" + bean.getUid() + "]");
				SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
				String now = dfm.format(new Date());
				bean.setOpdate(Long.valueOf(now));
				bs.updateUser(bean);
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {
				req.setAttribute("user", bean);
				return mapping.findForward("edit");
			}
			else{
				req.setAttribute("result", result);
				return mapping.findForward("list");
			}
		
		} else if ("updatepwd".equals(act)) { // 更新用户 for 跳转
			UserBean bean = (UserBean) gson.fromJson(msg, UserBean.class);
			UserBean user = new UserBean();
			if (bean != null) {
				SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
				String now = dfm.format(new Date());
				user.setOpdate(Long.valueOf(now));
				user.setUid(bean.getUid());
				user.setPwd(bean.getPwd());
				bs.updateUserPwd(user);
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {
				user = bs.getUserById(bean.getUid());
				req.setAttribute("user", user);
				return mapping.findForward("edit");
			}
			else{
				req.setAttribute("result", result);
				return mapping.findForward("list");
			}

		} else {
			result = "不支持的操作类型";
		}

		res.setCharacterEncoding("UTF-8");
		PrintWriter pw = null;
		try {
			pw = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (pw != null) {
			pw.write(result);
		}

		return null;
	}

}
