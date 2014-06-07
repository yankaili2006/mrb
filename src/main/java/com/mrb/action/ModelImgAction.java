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
import com.mrb.bean.ModelImgBean;
import com.mrb.bean.OperateBean;
import com.mrb.bean.PageBean;
import com.mrb.bs.ModelImgBS;
import com.mrb.bs.OperateBS;
import com.mrb.form.JsonForm;
import com.mrb.pbean.ModelImgReqBean;
import com.mrb.util.PageUtil;

/**
 * @author Administrator 9:06:26 PM
 * 
 *         模块图片注册的Action
 */
public class ModelImgAction extends Action {

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

		ModelImgBS bs = new ModelImgBS();
		Gson gson = new Gson();
		OperateBS operBS = new OperateBS();
		OperateBean operBean = new OperateBean();

		if ("list".equals(act)) { // 获取模块图片列表 for ajax

			PageBean pbean = gson.fromJson(msg, PageBean.class);
			pbean.setMaxpage(5);
			pbean.setPerpage(5);
			pbean.setTotal((bs.getModelImgCnt() - 1) / pbean.getPerpage() + 1);

			ModelImgReqBean reqBean = new ModelImgReqBean();
			reqBean.setStart((pbean.getP() - 1) * pbean.getPerpage());
			reqBean.setNum(pbean.getPerpage());
			ArrayList<ModelImgBean> ulist = bs.getModelImgList(
					reqBean.getStart(), reqBean.getNum());
			req.setAttribute("ulist", ulist);

			StringBuilder html = new StringBuilder(
					"<div class=\"well\"><table class=\"table\"><thead><tr><th>ID</th><th>图片ID</th><th>模块</th><th>模块内显示次序</th><th>图片链接</th><th style=\"width: 50px;\">操作</th></tr></thead><tbody>");
			if (ulist != null && ulist.size() > 0) {
				for (int i = 0; i < ulist.size(); i++) {
					ModelImgBean bean = (ModelImgBean) ulist.get(i);
					html.append("<tr><td>" + bean.getIid() + "</td><td>"
							+ bean.getModel() + "</td><td>" + bean.getIdx()
							+ "</td><td>" + bean.getIuri() + "</td><td>"
							+ bean.getIuri() + "</td>");
					html.append("<td><a href=\"javascript:void(0)\"}\" onclick=\"gotoedit(this);\"><i class=\"icon-pencil\"></i></a>&nbsp;&nbsp;");
					html.append("<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\" onclick=\"setiid(this);\"><i class=\"icon-remove\"></i></a></td></tr>");
				}

			} else {
				html.append("<tr><td>没有模块图片记录！</td><td></td><td></td><td></td><td></td><td></td></tr>");
			}
			html.append("</tbody></table></div>");

			PageUtil util = new PageUtil();
			html.append(util.pagination(pbean));

			result = html.toString();

		} else if ("add".equals(act)) { // 添加模块图片 for 跳转
			ModelImgBean bean = (ModelImgBean) gson.fromJson(msg,
					ModelImgBean.class);
			if (bean != null) {
				if (!bs.addModelImg(bean)) {
					result = "添加失败";
				}
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {
				operBean.setUid(Long.valueOf(suid));
				operBean.setOper("增加模块图片");
				operBS.addOperate(operBean);
				return mapping.findForward("list");
			} else {
				req.setAttribute("modelimg", bean);
				req.setAttribute("result", result);
				return mapping.findForward("add");
			}

		} else if ("edit".equals(act)) {// 编辑模块图片 for 跳转
			ModelImgBean modelimg = null;
			ModelImgBean bean = (ModelImgBean) gson.fromJson(msg,
					ModelImgBean.class);
			if (bean != null) {
				log.info("iid = [" + bean.getIid() + "]");
				modelimg = bs.getModelImgByIid(bean.getIid());
			} else {
				log.error("bean is null");
				result = "参数非法";
			}

			if (modelimg != null) {
				req.setAttribute("modelimg", modelimg);
			} else {
				result = "未找到该模块图片";
			}

			if ("ok".equals(result)) {
				return mapping.findForward(act);
			} else {
				req.setAttribute("result", result);
				return mapping.findForward("list");
			}

		} else if ("del".equals(act)) { // 删除模块图片 for ajax
			ModelImgBean bean = (ModelImgBean) gson.fromJson(msg,
					ModelImgBean.class);
			if (bean != null) {
				log.info("iid = [" + bean.getIid() + "]");
				if (!bs.delModelImgByIid(bean.getIid())) {
					result = "删除失败";
				} else {
					operBean.setUid(Long.valueOf(suid));
					operBean.setOper("删除模块图片");
					operBS.addOperate(operBean);
				}
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			return mapping.findForward("list");

		} else if ("update".equals(act)) { // 更新模块图片 for 跳转
			ModelImgBean bean = (ModelImgBean) gson.fromJson(msg,
					ModelImgBean.class);
			if (bean != null) {
				log.info("iid = [" + bean.getIid() + "]");
				if (!bs.updateModelImg(bean)) {
					result = "更新失败";
				}
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {

				operBean.setUid(Long.valueOf(suid));
				operBean.setOper("更新模块图片");
				operBS.addOperate(operBean);

				ModelImgBean showBean = bs.getModelImgByIid(bean.getIid());
				req.setAttribute("modelimg", showBean);
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
