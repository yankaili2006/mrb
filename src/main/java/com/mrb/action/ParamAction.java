/**
 * Feb 24, 2011 
 * RegisterAction.java 
 */
package com.mrb.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
import com.mrb.bean.ParamBean;
import com.mrb.bs.OperateBS;
import com.mrb.bs.ParamBS;
import com.mrb.form.JsonForm;
import com.mrb.util.PageUtil;

/**
 * @author Administrator 9:06:26 PM
 * 
 *         配置注册的Action
 */
public class ParamAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) {

		Logger log = Logger.getLogger(this.getClass());
		String result = "ok";
		String act = ((JsonForm) form).getAct();
		String msg = ((JsonForm) form).getMsg();
		log.debug("act = " + act + ", msg = " + msg);

		Long suid = (Long) req.getSession().getAttribute("uid");
		if (suid == null || "".equals(suid) || "null".equals(suid)) {
			return mapping.findForward("admin");
		}

		ParamBS bs = new ParamBS();
		Gson gson = new Gson();
		OperateBS operBS = new OperateBS();
		OperateBean operBean = new OperateBean();

		if ("list".equals(act)) { // 获取配置列表 for ajax

			PageBean pbean = gson.fromJson(msg, PageBean.class);
			pbean.setMaxpage(5);
			pbean.setPerpage(5);
			pbean.setTotal((bs.getParamCnt() - 1) / pbean.getPerpage() + 1);

			ArrayList<ParamBean> plist = bs.getParamList((pbean.getP() - 1)
					* pbean.getPerpage(), pbean.getPerpage());
			req.setAttribute("plist", plist);

			StringBuilder html = new StringBuilder(
					"<div class=\"well\"><table class=\"table\"><thead><tr><th>配置ID</th><th>配置名</th><th>配置值</th><th style=\"width:50px;\">操作</th></tr></thead><tbody>");
			if (plist != null && plist.size() > 0) {
				for (int i = 0; i < plist.size(); i++) {
					ParamBean bean = (ParamBean) plist.get(i);
					html.append("<tr><td>" + bean.getParam_id() + "</td><td>"
							+ bean.getParam_name() + "</td><td>"
							+ bean.getParam_value() + "</td>");
					html.append("<td><a href=\"javascript:void(0)\"}\" onclick=\"gotoedit(this);\"><i class=\"icon-pencil\"></i></a>&nbsp;&nbsp;");
					html.append("<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\" onclick=\"setparam_id(this);\"><i class=\"icon-remove\"></i></a></td></tr>");
				}

			} else {
				html.append("<tr><td>没有配置记录！</td><td></td><td></td></tr>");
			}
			html.append("</tbody></table></div>");

			PageUtil util = new PageUtil();
			html.append(util.pagination(pbean));

			result = html.toString();

		} else if ("add".equals(act)) { // 添加配置 for 跳转
			ParamBean bean = (ParamBean) gson.fromJson(msg, ParamBean.class);
			if (bean != null) {

				int rst = bs.addParam(bean);
				if (rst >= 0) {
				} else if (rst == -2) {
					result = "配置名已经存在";
				} else {
					result = "未知错误";
				}

			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {
				operBean.setUid(Long.valueOf(suid));
				operBean.setOper("增加配置");
				operBS.addOperate(operBean);

				result = "添加成功";

				return mapping.findForward("list");
			} else {
				req.setAttribute("param", bean);
				req.setAttribute("result", result);
				return mapping.findForward("add");
			}
		} else if ("edit".equals(act)) {// 编辑配置 for 跳转
			ParamBean param = null;
			ParamBean bean = (ParamBean) gson.fromJson(msg, ParamBean.class);
			if (bean != null) {
				log.info("param_id = [" + bean.getParam_id() + "]");
				param = bs.getParamById(bean.getParam_id());
			} else {
				log.error("bean is null");
				result = "参数非法";
			}

			if (param != null) {
				req.setAttribute("param", param);
			} else {
				result = "未找到该配置";
			}

			if ("ok".equals(result)) {
				return mapping.findForward(act);
			} else {
				req.setAttribute("result", result);
				return mapping.findForward("list");
			}

		} else if ("update".equals(act)) { // 更新配置 for 跳转
			ParamBean bean = (ParamBean) gson.fromJson(msg, ParamBean.class);
			if (bean != null) {

				int rst = bs.updateParam(bean);
				if (rst >= 0) {
				} else if (rst == -1) {
					result = "修改失败";
				} else {
					result = "未知错误";
				}
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {
				operBean.setUid(Long.valueOf(suid));
				operBean.setOper("更新配置");
				operBS.addOperate(operBean);
				req.setAttribute("param", bean);

				result = "修改成功";

				return mapping.findForward("edit");
			} else {
				req.setAttribute("result", result);
				return mapping.findForward("list");
			}
		} else if ("del".equals(act)) { // 删除配置 for ajax
			ParamBean bean = (ParamBean) gson.fromJson(msg, ParamBean.class);
			if (bean != null) {
				log.info("param_id = [" + bean.getParam_id() + "]");
				if (!bs.delParamById(bean.getParam_id())) {
					result = "删除失败";
				} else {
					operBean.setOper("删除配置");
					operBS.addOperate(operBean);
				}
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			return mapping.findForward("list");

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
