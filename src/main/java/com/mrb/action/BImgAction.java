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
import com.mrb.bean.BImg2ShowBean;
import com.mrb.bean.BImgBean;
import com.mrb.bean.Brand2ShowBean;
import com.mrb.bean.BrandImgListBean;
import com.mrb.bean.OperateBean;
import com.mrb.bean.PageBean;
import com.mrb.bs.BImgBS;
import com.mrb.bs.BrandBS;
import com.mrb.bs.OperateBS;
import com.mrb.bs.ProjectBS;
import com.mrb.form.JsonForm;
import com.mrb.pbean.BImgReqBean;
import com.mrb.pbean.BImgRespBean;
import com.mrb.pbean.BrandReqBean;
import com.mrb.util.MrbUtil;
import com.mrb.util.PageUtil;

/**
 * @author Administrator 9:06:26 PM
 * 
 *         品牌图片注册的Action
 */
public class BImgAction extends Action {

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

		BImgBS bs = new BImgBS();
		Gson gson = new Gson();
		OperateBS operBS = new OperateBS();
		OperateBean operBean = new OperateBean();

		if ("list".equals(act)) { // 获取品牌图片列表 for ajax

			PageBean pbean = gson.fromJson(msg, PageBean.class);
			pbean.setMaxpage(5);
			pbean.setPerpage(5);
			pbean.setTotal((bs.getBImgCnt() - 1) / pbean.getPerpage() + 1);

			BImgReqBean reqBean = new BImgReqBean();
			reqBean.setStart((pbean.getP() - 1) * pbean.getPerpage());
			reqBean.setNum(pbean.getPerpage());
			ArrayList<BImg2ShowBean> ulist = bs.getBImgList(reqBean);
			req.setAttribute("ulist", ulist);

			StringBuilder html = new StringBuilder(
					"<div class=\"well\"><table class=\"table\"><thead><tr><th>ID</th><th>品牌ID</th><th>品牌标题</th><th>品牌图片</th><th style=\"width: 50px;\">操作</th></tr></thead><tbody>");
			if (ulist != null && ulist.size() > 0) {
				for (int i = 0; i < ulist.size(); i++) {
					BImg2ShowBean bean = (BImg2ShowBean) ulist.get(i);
					html.append("<tr><td>" + bean.getBiid() + "</td><td>"
							+ bean.getBid() + "</td><td>" + bean.getBtitle()
							+ "</td><td>" + bean.getIuri() + "</td>");
					html.append("<td><a href=\"javascript:void(0)\"}\" onclick=\"gotoedit(this);\"><i class=\"icon-pencil\"></i></a>&nbsp;&nbsp;");
					html.append("<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\" onclick=\"setbiid(this);\"><i class=\"icon-remove\"></i></a></td></tr>");
				}

			} else {
				html.append("<tr><td>没有品牌图片记录！</td><td></td><td></td><td></td></tr>");
			}
			html.append("</tbody></table></div>");

			PageUtil util = new PageUtil();
			html.append(util.pagination(pbean));

			result = html.toString();

		} else if ("add".equals(act)) { // 添加品牌图片 for 跳转
			BImgBean bean = (BImgBean) gson.fromJson(msg, BImgBean.class);
			if (bean != null) {
				if (!bs.addBImg(bean)) {
					result = "添加失败";
				}
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {
				operBean.setUid(Long.valueOf(suid));
				operBean.setOper("增加品牌图片");
				operBS.addOperate(operBean);
				return mapping.findForward("list");
			} else {
				req.setAttribute("bimg", bean);
				req.setAttribute("result", result);
				return mapping.findForward("add");
			}

		} else if ("listall".equals(act)) { // 获取品牌图片列表 for phone
			BImgReqBean bean = (BImgReqBean) gson.fromJson(msg,
					BImgReqBean.class);
			ArrayList<BImg2ShowBean> list = null;
			BImgRespBean respBean = new BImgRespBean();
			if (bean != null) {

				BrandBS bbs = new BrandBS();
				BrandReqBean bReqBean = new BrandReqBean();
				bReqBean.setPid(bean.getPid());
				bReqBean.setStart(0);
				bReqBean.setNum(bbs.getBrandCntByPid(bean.getPid()));
				ArrayList<Brand2ShowBean> blist = bbs
						.getBrandListByPid(bReqBean);

				if (blist == null || blist.size() == 0) {
					respBean.setCode("1501");
					respBean.setMsg("查询失败");
				} else {
					ArrayList<BrandImgListBean> brandList = new ArrayList<BrandImgListBean>();

					// 所有品牌
					for (int k = 0; k < blist.size(); k++) {
						Brand2ShowBean b2ShowBean = blist.get(k);

						BrandImgListBean bImgListBean = new BrandImgListBean();
						bImgListBean.setBid(b2ShowBean.getBid());
						bImgListBean.setBtitle(b2ShowBean.getBtitle());

						// 品牌下的所有图片
						list = bs.getBImgListByBid(b2ShowBean.getBid());
						ArrayList<String> imgs = new ArrayList<String>();
						if (list != null && list.size() > 0) {
							for (int i = 0; i < list.size(); i++) {
								BImg2ShowBean bibean = list.get(i);
								imgs.add(MrbUtil.getImgUrl() + "/"
										+ bibean.getIuri());
							}
						}
						bImgListBean.setBimgs(imgs);

						brandList.add(bImgListBean);
					}

					respBean.setCode("0000");
					respBean.setMsg("交易成功");
					respBean.setNum(brandList.size());
					respBean.setBimgs(brandList);
				}
			} else {
				respBean.setCode("1500");
				respBean.setMsg("参数非法");
			}

			result = gson.toJson(respBean);

		} else if ("edit".equals(act)) {// 编辑品牌图片 for 跳转
			BImg2ShowBean bimg = null;
			BImgBean bean = (BImgBean) gson.fromJson(msg, BImgBean.class);
			if (bean != null) {
				log.info("biid = [" + bean.getBiid() + "]");
				bimg = bs.getBImgByBiid(bean.getBiid());
			} else {
				log.error("bean is null");
				result = "参数非法";
			}

			if (bimg != null) {
				req.setAttribute("bimg", bimg);
			} else {
				result = "未找到该品牌图片";
			}

			if ("ok".equals(result)) {
				return mapping.findForward(act);
			} else {
				req.setAttribute("result", result);
				return mapping.findForward("list");
			}

		} else if ("del".equals(act)) { // 删除品牌图片 for ajax
			BImgBean bean = (BImgBean) gson.fromJson(msg, BImgBean.class);
			if (bean != null) {
				log.info("biid = [" + bean.getBiid() + "]");
				if (!bs.delBImgByBiid(bean.getBiid())) {
					result = "删除失败";
				} else {
					operBean.setUid(Long.valueOf(suid));
					operBean.setOper("删除品牌图片");
					operBS.addOperate(operBean);
				}
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			return mapping.findForward("list");

		} else if ("update".equals(act)) { // 更新品牌图片 for 跳转
			BImgBean bean = (BImgBean) gson.fromJson(msg, BImgBean.class);
			if (bean != null) {
				log.info("biid = [" + bean.getBiid() + "]");
				if (!bs.updateBImg(bean)) {
					result = "更新失败";
				}
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {

				operBean.setUid(Long.valueOf(suid));
				operBean.setOper("更新品牌图片");
				operBS.addOperate(operBean);

				BImg2ShowBean showBean = bs.getBImgByBiid(bean.getBiid());
				req.setAttribute("bimg", showBean);
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
