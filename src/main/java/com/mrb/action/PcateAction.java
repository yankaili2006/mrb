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
import com.mrb.bean.PcateBean;
import com.mrb.bs.OperateBS;
import com.mrb.bs.PcateBS;
import com.mrb.form.JsonForm;
import com.mrb.util.PageUtil;

/**
 * @author Administrator 9:06:26 PM
 * 
 *         分类注册的Action
 */
public class PcateAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) {

		Logger log = Logger.getLogger(this.getClass());
		String result = "ok";
		String act = ((JsonForm) form).getAct();
		String msg = ((JsonForm) form).getMsg();
		log.debug("act = " + act + ", msg = " + msg);

		// act = act == null ? "list" : act;

		Long suid = (Long) req.getSession().getAttribute("uid");
		if ("add,edit,del,update".contains(act)) {
			if (suid == null || "".equals(suid) || "null".equals(suid)) {
				return mapping.findForward("admin");
			}
		}

		PcateBS bs = new PcateBS();
		Gson gson = new Gson();
		OperateBS operBS = new OperateBS();
		OperateBean operBean = new OperateBean();

		if ("list".equals(act)) { // 获取分类列表 for ajax
			ArrayList<PcateBean> ulist = null;
			PageBean pbean = null;
			try {
				pbean = gson.fromJson(msg, PageBean.class);
				pbean.setMaxpage(5);
				pbean.setPerpage(5);
				pbean.setTotal((bs.getPcateCnt() - 1) / pbean.getPerpage() + 1);

				ulist = bs.getPcateList(
						(pbean.getP() - 1) * pbean.getPerpage(),
						pbean.getPerpage());
				req.setAttribute("ulist", ulist);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			StringBuilder html = new StringBuilder(
					"<div class=\"well\"><table class=\"table\"><thead><tr><th>分类ID</th><th>分类名</th><th>注册日期</th><th>显示次序</th><th style=\"width: 50px;\">操作</th></tr></thead><tbody>");
			if (ulist != null && ulist.size() > 0) {
				for (int i = 0; i < ulist.size(); i++) {
					PcateBean bean = (PcateBean) ulist.get(i);
					html.append("<tr><td>" + bean.getCid() + "</td><td>"
							+ bean.getName() + "</td><td>" + bean.getDate()
							+ "</td><td>" + bean.getIdx() + "</td>");
					html.append("<td><a href=\"javascript:void(0)\"}\" onclick=\"gotoedit(this);\"><i class=\"icon-pencil\"></i></a>&nbsp;&nbsp;");
					html.append("<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\" onclick=\"setcid(this);\"><i class=\"icon-remove\"></i></a></td></tr>");

				}

			} else {
				html.append("<tr><td>没有分类记录！</td><td></td><td></td><td></td></tr>");
			}
			html.append("</tbody></table></div>");

			PageUtil util = new PageUtil();
			html.append(util.pagination(pbean));

			result = html.toString();
		} else if ("add".equals(act)) { // 添加分类 for 跳转
			PcateBean bean = (PcateBean) gson.fromJson(msg, PcateBean.class);
			if (bean != null) {
				if (!bs.addPcate(bean)) {
					result = "操作失败!";
				}

			} else {
				log.error("bean is null");
				result = "参数错误";
			}

			if (!"ok".equals(result)) {
				operBean.setUid(Long.valueOf(suid));
				operBean.setOper("增加项目分类");
				operBS.addOperate(operBean);

				req.setAttribute("result", result);
				return mapping.findForward(act);
			} else {
				return mapping.findForward("list");
			}

		} else if ("edit".equals(act)) {// 编辑分类 for 跳转
			PcateBean pcate = null;
			PcateBean bean = (PcateBean) gson.fromJson(msg, PcateBean.class);
			if (bean != null) {
				log.info("cid = [" + bean.getCid() + "]");
				pcate = bs.getPcateById(bean.getCid());

				if (pcate != null) {
					req.setAttribute("pcate", pcate);
				} else {
					result = "未找到该分类";
				}
			} else {
				log.error("bean is null");
				result = "参数错误";
			}

			if ("ok".equals(result)) {
				return mapping.findForward(act);
			}
		} else if ("update".equals(act)) { // 更新分类 for 跳转
			PcateBean bean = (PcateBean) gson.fromJson(msg, PcateBean.class);
			if (bean != null) {
				log.info("cid = [" + bean.getCid() + "]");
				if (!bs.updatePcate(bean)) {
					result = "更新失败";
				} else {
					operBean.setUid(Long.valueOf(suid));
					operBean.setOper("更新项目分类");
					operBS.addOperate(operBean);
					req.setAttribute("pcate", bean);
				}
			} else {
				log.error("bean is null");
				result = "参数错误";
			}

			req.setAttribute("result", result);
			return mapping.findForward("edit");

		} else if ("del".equals(act)) { // 删除分类 for ajax
			PcateBean bean = (PcateBean) gson.fromJson(msg, PcateBean.class);
			if (bean != null) {
				log.info("cid = [" + bean.getCid() + "]");
				if (!bs.delPcateById(bean.getCid())) {
					result = "操作失败";
				} else {
					operBean.setUid(Long.valueOf(suid));
					operBean.setOper("删除项目分类");
					operBS.addOperate(operBean);
				}
			} else {
				result = "未找到该分类";
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
