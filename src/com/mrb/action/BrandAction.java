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
import com.mrb.bean.BrandBean;
import com.mrb.bs.BrandBS;
import com.mrb.form.JsonForm;

/**
 * @author Administrator 9:06:26 PM
 * 
 * 品牌注册的Action
 */
public class BrandAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) {

		Logger log = Logger.getLogger(this.getClass());
		String result = "ok";
		String act = ((JsonForm) form).getAct();
		String msg = ((JsonForm) form).getMsg();
		log.debug("act = " + act + ", msg = " + msg);

		BrandBS bs = new BrandBS();
		Gson gson = new Gson();
		if ("list".equals(act)) { // 获取品牌列表 for ajax

			ArrayList<BrandBean> ulist = bs.getBrandList();
			log.debug("ulist.size(): " + ulist.size());
			req.setAttribute("ulist", ulist);

			StringBuilder html = new StringBuilder(
					"<table class=\"table\"><thead><tr><th>品牌标题</th><th>项目名称</th><th>品牌图标</th><th>名称</th><th>价格</th><th style=\"width: 26px;\"></th></tr></thead><tbody>");
			if (ulist != null && ulist.size() > 0) {
				for (int i = 0; i < ulist.size(); i++) {
					BrandBean bean = (BrandBean) ulist.get(i);
					html.append("<tr><td>" + bean.get + "</td><td>"
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
							.append("<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\" onclick=\"setbid(this);\"><i class=\"icon-remove\"></i></a></td></tr>");
				}

			} else {
				html
						.append("<tr><td>没有品牌记录！</td><td></td><td></td><td></td></tr>");
			}
			html.append("</tbody></table>");
			result = html.toString();
			
		} else if ("add".equals(act)) { // 添加品牌 for 跳转
			BrandBean bean = (BrandBean) gson.fromJson(msg, BrandBean.class);
			if (bean != null) {
				bs.addBrand(bean);
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {
				return mapping.findForward("list");
			}
			else{
				req.setAttribute("brand", bean);
				req.setAttribute("result", result);
				return mapping.findForward("add");
			}
		} else if ("edit".equals(act)) {// 编辑品牌 for 跳转
			BrandBean brand = null;
			BrandBean bean = (BrandBean) gson.fromJson(msg, BrandBean.class);
			if (bean != null) {
				log.info("bid = [" + bean.getBid() + "]");
				brand = bs.getBrandById(bean.getBid());
			} else {
				log.error("bean is null");
				result = "参数非法";
			}

			if (brand != null) {
				req.setAttribute("brand", brand);
			} else {
				result = "未找到该品牌";
			}

			if ("ok".equals(result)) {
				return mapping.findForward(act);
			}
			else{
				req.setAttribute("result", result);
				return mapping.findForward("list");			
			}
			
		} else if ("del".equals(act)) { // 删除品牌 for ajax
			BrandBean bean = (BrandBean) gson.fromJson(msg, BrandBean.class);
			if (bean != null) {
				log.info("bid = [" + bean.getBid() + "]");
				bs.delBrandById(bean.getBid());
			} else {
				result = "参数非法";
			}
			
		} else if ("update".equals(act)) { // 更新品牌 for 跳转
			BrandBean bean = (BrandBean) gson.fromJson(msg, BrandBean.class);
			if (bean != null) {
				log.info("bid = [" + bean.getBid() + "]");
				SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
				String now = dfm.format(new Date());
				bean.setDate(Long.valueOf(now));
				bs.updateBrand(bean);
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {
				req.setAttribute("brand", bean);
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
