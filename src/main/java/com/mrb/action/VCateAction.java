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
import com.google.gson.reflect.TypeToken;
import com.mrb.bean.PageBean;
import com.mrb.bean.VCateBean;
import com.mrb.bs.VCateBS;
import com.mrb.form.JsonForm;
import com.mrb.pbean.VBean;
import com.mrb.util.PageUtil;

/**
 * @author Administrator 9:06:26 PM
 * 
 *         视频分类注册的Action
 */
public class VCateAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) {

		Logger log = Logger.getLogger(this.getClass());
		String result = "ok";
		String act = ((JsonForm) form).getAct();
		String msg = ((JsonForm) form).getMsg();
		log.debug("act = " + act + ", msg = " + msg);

		VCateBS bs = new VCateBS();
		Gson gson = new Gson();
		if ("list".equals(act)) { // 获取视频分类列表 for ajax

			PageBean pbean = gson.fromJson(msg, PageBean.class);
			pbean.setMaxpage(5);
			pbean.setPerpage(5);
			pbean.setTotal((bs.getVCateCnt() - 1) / pbean.getPerpage() + 1);

			ArrayList<VCateBean> ulist = bs.getVCateList((pbean.getP() - 1)
					* pbean.getPerpage(), pbean.getPerpage());
			req.setAttribute("ulist", ulist);

			StringBuilder html = new StringBuilder(
					"<div class=\"well\"><table class=\"table\"><thead><tr><th>视频分类ID</th><th>分类名称</th><th>创建日期</th><th>操作人</th><th style=\"width: 26px;\"></th></tr></thead><tbody>");
			if (ulist != null && ulist.size() > 0) {
				for (int i = 0; i < ulist.size(); i++) {
					VCateBean bean = (VCateBean) ulist.get(i);
					html.append("<tr><td>" + bean.getVcid() + "</td><td>"
							+ bean.getName() + "</td><td>" + bean.getDate()
							+ "</td><td>" + bean.getOperid() + "</td>");

					html.append("<td><a href=\"javascript:void(0)\"}\" onclick=\"gotoedit(this);\"><i class=\"icon-pencil\"></i></a>&nbsp;&nbsp;");
					html.append("<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\" onclick=\"setvcid(this);\"><i class=\"icon-remove\"></i></a></td></tr>");
				}

			} else {
				html.append("<tr><td>没有视频分类记录！</td><td></td><td></td><td></td></tr>");
			}
			html.append("</tbody></table></div>");

			PageUtil util = new PageUtil();
			html.append(util.pagination(pbean));

			result = html.toString();
		} else if ("add".equals(act)) { // 添加视频分类 for 跳转
			VCateBean bean = (VCateBean) gson.fromJson(msg, VCateBean.class);
			if (bean != null) {
				if(!bs.addVCate(bean)){
					result = "添加失败";
				}
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {
				return mapping.findForward("list");
			} else {
				req.setAttribute("vcate", bean);
				req.setAttribute("result", result);
				return mapping.findForward("add");
			}
		} else if ("edit".equals(act)) {// 编辑视频分类 for 跳转
			VCateBean vcate = null;
			VCateBean bean = (VCateBean) gson.fromJson(msg, VCateBean.class);
			if (bean != null) {
				log.info("vcid = [" + bean.getVcid() + "]");
				vcate = bs.getVCateById(bean.getVcid());
			} else {
				log.error("bean is null");
				result = "参数非法";
			}

			if (vcate != null) {
				req.setAttribute("vcate", vcate);
			} else {
				result = "未找到该视频分类";
			}

			if ("ok".equals(result)) {
				return mapping.findForward(act);
			} else {
				req.setAttribute("result", result);
				return mapping.findForward("list");
			}

		} else if ("del".equals(act)) { // 删除视频分类 for ajax
			VCateBean bean = (VCateBean) gson.fromJson(msg, VCateBean.class);
			if (bean != null) {
				log.info("vcid = [" + bean.getVcid() + "]");
				if(!bs.delVCateById(bean.getVcid())){
					result = "删除失败";
				}
			} else {
				result = "参数非法";
			}
			req.setAttribute("result", result);
			return mapping.findForward("list");

		} else if ("update".equals(act)) { // 更新视频分类 for 跳转
			VCateBean bean = (VCateBean) gson.fromJson(msg, VCateBean.class);
			if (bean != null) {
				log.info("Vcid = [" + bean.getVcid() + "]");
				SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
				String now = dfm.format(new Date());
				bean.setDate(Long.valueOf(now));
				if(!bs.updateVCate(bean)){
					result = "更新失败";
				}
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {
				req.setAttribute("vcate", bean);
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
