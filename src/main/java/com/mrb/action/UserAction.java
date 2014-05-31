/**
 * Feb 24, 2011 
 * RegisterAction.java 
 */
package com.mrb.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.mrb.bean.OperateBean;
import com.mrb.bean.PageBean;
import com.mrb.bean.UserBean;
import com.mrb.bean.UserRegRespBean;
import com.mrb.bs.OperateBS;
import com.mrb.bs.UserBS;
import com.mrb.form.JsonForm;
import com.mrb.util.PageUtil;

/**
 * @author Administrator 9:06:26 PM
 * 
 *         用户注册的Action
 */
public class UserAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) {

		Logger log = Logger.getLogger(this.getClass());
		String result = "ok";
		String act = ((JsonForm) form).getAct();
		String msg = ((JsonForm) form).getMsg();
		log.debug("act = " + act + ", msg = " + msg);

		Long suid = (Long) req.getSession().getAttribute("uid");
		if ("add,edit,del,update,updatepwd".contains(act)) {
			if (suid == null || "".equals(suid) || "null".equals(suid)) {
				return mapping.findForward("admin");
			}
		}

		UserBS bs = new UserBS();
		Gson gson = new Gson();
		OperateBS operBS = new OperateBS();
		OperateBean operBean = new OperateBean();

		if ("list".equals(act)) { // 获取用户列表 for ajax

			PageBean pbean = gson.fromJson(msg, PageBean.class);
			pbean.setMaxpage(5);
			pbean.setPerpage(5);
			pbean.setTotal((bs.getUserCnt() - 1) / pbean.getPerpage() + 1);

			ArrayList<UserBean> ulist = bs.getUserList((pbean.getP() - 1)
					* pbean.getPerpage(), pbean.getPerpage());
			req.setAttribute("ulist", ulist);

			StringBuilder html = new StringBuilder(
					"<div class=\"well\"><table class=\"table\"><thead><tr><th>用户ID</th><th>用户名</th><th>手机号</th><th>注册日期</th><th>状态</th><th style=\"width: 26px;\"></th></tr></thead><tbody>");
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
					html.append("<td><a href=\"javascript:void(0)\"}\" onclick=\"gotoedit(this);\"><i class=\"icon-pencil\"></i></a>&nbsp;&nbsp;");
					html.append("<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\" onclick=\"setuid(this);\"><i class=\"icon-remove\"></i></a></td></tr>");
				}

			} else {
				html.append("<tr><td>没有用户记录！</td><td></td><td></td><td></td></tr>");
			}
			html.append("</tbody></table></div>");

			PageUtil util = new PageUtil();
			html.append(util.pagination(pbean));

			result = html.toString();

		} else if ("add".equals(act)) { // 添加用户 for 跳转
			UserBean bean = (UserBean) gson.fromJson(msg, UserBean.class);
			if (bean != null) {
				if (!bs.addUser(bean)) {
					result = "添加失败";
				}
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {
				operBean.setUid(Long.valueOf(suid));
				operBean.setOper("增加用户");
				operBS.addOperate(operBean);

				return mapping.findForward("list");
			} else {
				req.setAttribute("user", bean);
				req.setAttribute("result", result);
				return mapping.findForward("add");
			}
		} else if ("register".equals(act)) { // 注册用户 for 手机端
			UserBean bean = null;
			try {
				bean = (UserBean) gson.fromJson(msg, UserBean.class);
			} catch (Exception e) {
				log.error(e.getStackTrace());
			}

			UserRegRespBean respBean = new UserRegRespBean();

			if (bean == null) {
				respBean.setCode("1001");
				respBean.setMsg("参数非法");
			} else if ((bean.getUname() == null || "".equals(bean.getUname()))
					&& (bean.getPhone() == null || "".equals(bean.getPhone()))) {
				respBean.setCode("1002");
				respBean.setMsg("用户名和手机号不能同时为空");
			} else if (bean.getPwd() == null || "".equals(bean.getPwd())) {
				respBean.setCode("1003");
				respBean.setMsg("密码为空");
			} else {

				// 添加用户
				boolean rst = bs.addUser(bean);
				Long uid = System.currentTimeMillis() % 1000000;

				bean.setUid(uid);
				if (rst) {
					respBean.setCode("0000");
					respBean.setMsg("注册成功");
					respBean.setUid(uid);
					respBean.setPhone(bean.getPhone());
					respBean.setUser(bean.getUname());
				} else {
					respBean.setCode("1000");
					respBean.setMsg("注册失败");
				}
			}

			result = gson.toJson(respBean);

		} else if ("login".equals(act)) { // 用户登录 for 手机端
			UserBean bean = null;
			try {
				bean = (UserBean) gson.fromJson(msg, UserBean.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			UserRegRespBean respBean = new UserRegRespBean();

			if (bean == null) {
				respBean.setCode("1001");
				respBean.setMsg("参数非法");
			} else if (bean.getPhone() == null || "".equals(bean.getPhone())) {
				respBean.setCode("1002");
				respBean.setMsg("手机号不能为空");
			} else {
				UserBean resultBean = bs.loginUser(bean);
				if (resultBean != null) {
					respBean.setCode("0000");
					respBean.setMsg("登录成功");
					respBean.setUid(resultBean.getUid());
				} else {
					respBean.setCode("1009");
					respBean.setMsg("登录失败");
				}
			}

			result = gson.toJson(respBean);

		} else if ("loginuserphone".equals(act)) { // 用户登录 for 管理端
			// 可以使用用户名或者手机号码登录
			UserBean bean = null;
			try {
				bean = (UserBean) gson.fromJson(msg, UserBean.class);
			} catch (Exception e) {
				log.error(e.getStackTrace());
			}

			if (bean != null) {
				UserBean resultBean = bs.loginUPUser(bean);
				if (resultBean != null) {
					req.getSession().setAttribute("uid", resultBean.getUid());
					req.getSession().setAttribute("uname",
							resultBean.getUname());
					return mapping.findForward("home");
				} else {
					result = "登录失败";
				}
			} else {
				result = "参数非法";
			}
		} else if ("loginweb".equals(act)) { // 用户登录 for 管理端
			UserBean bean = (UserBean) gson.fromJson(msg, UserBean.class);
			if (bean != null) {
				UserBean resultBean = bs.loginUser(bean);
				if (resultBean != null) {
					req.getSession().setAttribute("uid", resultBean.getUid());
					req.getSession().setAttribute("uname",
							resultBean.getUname());
					return mapping.findForward("home");
				} else {
					result = "登录失败";
				}
			} else {
				result = "参数非法";
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
			} else {
				req.setAttribute("result", result);
				return mapping.findForward("list");
			}

		} else if ("del".equals(act)) { // 删除用户 for ajax
			UserBean bean = (UserBean) gson.fromJson(msg, UserBean.class);
			if (bean != null) {
				log.info("uid = [" + bean.getUid() + "]");
				if (!bs.delUserById(bean.getUid())) {
					result = "删除失败";
				} else {
					operBean.setOper("删除用户");
					operBS.addOperate(operBean);
				}
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			return mapping.findForward("list");

		} else if ("update".equals(act)) { // 更新用户 for 跳转
			UserBean bean = (UserBean) gson.fromJson(msg, UserBean.class);
			if (bean != null) {
				log.info("uid = [" + bean.getUid() + "]");
				SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
				String now = dfm.format(new Date());
				bean.setOpdate(Long.valueOf(now));
				if (!bs.updateUser(bean)) {
					result = "更新失败";
				}
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {
				operBean.setUid(Long.valueOf(suid));
				operBean.setOper("更新用户");
				operBS.addOperate(operBean);
				req.setAttribute("user", bean);

				return mapping.findForward("edit");
			} else {
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
				if (bs.updateUserPwd(user)) {
					result = "更新密码失败";
				}
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {
				operBean.setUid(Long.valueOf(suid));
				operBean.setOper("更新用户密码");
				operBS.addOperate(operBean);

				user = bs.getUserById(bean.getUid());
				req.setAttribute("user", user);
				return mapping.findForward("edit");
			} else {
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
