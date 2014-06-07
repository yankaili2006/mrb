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
import com.google.gson.reflect.TypeToken;
import com.mrb.bean.OperateBean;
import com.mrb.bean.PageBean;
import com.mrb.bean.CityBean;
import com.mrb.bs.CityBS;
import com.mrb.bs.OperateBS;
import com.mrb.form.JsonForm;
import com.mrb.pbean.City4PhoneBean;
import com.mrb.pbean.CityReqBean;
import com.mrb.pbean.CityRespBean;
import com.mrb.util.PageUtil;

/**
 * @author Administrator 9:06:26 PM
 * 
 *         城市注册的Action
 */
public class CityAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) {

		Logger log = Logger.getLogger(this.getClass());
		String result = "ok";
		String act = ((JsonForm) form).getAct();
		String msg = ((JsonForm) form).getMsg();
		log.debug("act = " + act + ", msg = " + msg);

		Long suid = (Long) req.getSession().getAttribute("uid");
		if ("add,edit,del,update".contains(act)) {
			if (suid == null || "".equals(suid) || "null".equals(suid)) {
				return mapping.findForward("admin");
			}
		}

		CityBS bs = new CityBS();
		Gson gson = new Gson();
		OperateBS operBS = new OperateBS();
		OperateBean operBean = new OperateBean();

		if ("list".equals(act)) { // 获取城市列表 for ajax

			PageBean pbean = gson.fromJson(msg, PageBean.class);
			pbean.setMaxpage(5);
			pbean.setPerpage(5);
			pbean.setTotal((bs.getCityCnt() - 1) / pbean.getPerpage() + 1);

			ArrayList<CityBean> ulist = bs.getCityList((pbean.getP() - 1)
					* pbean.getPerpage(), pbean.getPerpage());
			req.setAttribute("ulist", ulist);

			StringBuilder html = new StringBuilder(
					"<div class=\"well\"><table class=\"table\"><thead><tr><th>城市ID</th><th>城市名</th><th>城市等级</th<th style=\"width: 26px;\"></th></tr></thead><tbody>");
			if (ulist != null && ulist.size() > 0) {
				for (int i = 0; i < ulist.size(); i++) {
					CityBean bean = (CityBean) ulist.get(i);
					html.append("<tr><td>" + bean.getCid() + "</td><td>"
							+ bean.getName() + "</td><td>" + bean.getLevel()
							+ "</td>");

					html.append("<td><a href=\"javascript:void(0)\"}\" onclick=\"gotoedit(this);\"><i class=\"icon-pencil\"></i></a>&nbsp;&nbsp;");
					html.append("<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\" onclick=\"setcid(this);\"><i class=\"icon-remove\"></i></a></td></tr>");
				}

			} else {
				html.append("<tr><td>没有城市记录！</td><td></td><td></td></tr>");
			}
			html.append("</tbody></table></div>");

			PageUtil util = new PageUtil();
			html.append(util.pagination(pbean));

			result = html.toString();
		} else if ("listall".equals(act)) { // 获取城市列表 for 手机端

			CityReqBean reqBean = null;
			try {
				reqBean = gson.fromJson(msg, CityReqBean.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			CityRespBean respBean = new CityRespBean();
			if (reqBean == null) {
				respBean.setCode("1601");
				respBean.setMsg("参数非法");
			} else if (reqBean.getLevel() <= 0) {
				respBean.setCode("1602");
				respBean.setMsg("城市层次非法");
			} else if (reqBean.getStart() < 0) {
				respBean.setCode("1603");
				respBean.setMsg("开始索引start值非法");
			} else if (reqBean.getNum() < 0) {
				respBean.setCode("1604");
				respBean.setMsg("num值非法");
			} else {
				ArrayList<City4PhoneBean> ulist = bs.getCList(
						reqBean.getLevel(), reqBean.getStart(),
						reqBean.getNum());

				if (ulist == null || ulist.size() < 0) {
					respBean.setCode("1605");
					respBean.setMsg("查询城市列表失败");
				} else if (ulist.size() >= 0) {
					respBean.setCode("0000");
					respBean.setMsg("交易成功");
					respBean.setStart(reqBean.getStart());
					respBean.setNum(ulist.size());
					respBean.setClist(ulist);
				} else {
					respBean.setCode("1600");
					respBean.setMsg("交易失败");
				}
			}
			result = gson.toJson(respBean);

		} else if ("add".equals(act)) { // 添加城市 for 跳转
			CityBean bean = (CityBean) gson.fromJson(msg, CityBean.class);
			if (bean != null) {
				bs.addCity(bean);
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {
				operBean.setUid(Long.valueOf(suid));
				operBean.setOper("增加城市");
				operBS.addOperate(operBean);
				return mapping.findForward("list");
			} else {
				req.setAttribute("city", bean);
				req.setAttribute("result", result);
				return mapping.findForward("add");
			}

		} else if ("edit".equals(act)) {// 编辑城市 for 跳转
			CityBean city = null;
			CityBean bean = (CityBean) gson.fromJson(msg, CityBean.class);
			if (bean != null) {
				log.info("cid = [" + bean.getCid() + "]");
				city = bs.getCityById(bean.getCid());
			} else {
				log.error("bean is null");
				result = "参数非法";
			}

			if (city != null) {
				req.setAttribute("city", city);
			} else {
				result = "未找到该城市";
			}

			if ("ok".equals(result)) {
				return mapping.findForward(act);
			} else {
				req.setAttribute("result", result);
				return mapping.findForward("list");
			}

		} else if ("del".equals(act)) { // 删除城市 for ajax
			CityBean bean = (CityBean) gson.fromJson(msg, CityBean.class);
			if (bean != null) {
				log.info("cid = [" + bean.getCid() + "]");
				if (!bs.delCityById(bean)) {
					result = "删除失败";
				} else {
					operBean.setUid(Long.valueOf(suid));
					operBean.setOper("删除城市");
					operBS.addOperate(operBean);
				}
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			return mapping.findForward("list");

		} else if ("update".equals(act)) { // 更新城市 for 跳转
			CityBean bean = (CityBean) gson.fromJson(msg, CityBean.class);
			if (bean != null) {
				log.info("cid = [" + bean.getCid() + "]");
				bs.updateCity(bean);
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {
				operBean.setUid(Long.valueOf(suid));
				operBean.setOper("更新城市");
				operBS.addOperate(operBean);

				req.setAttribute("city", bean);
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
