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
import com.mrb.bean.Brand2ShowBean;
import com.mrb.bean.OperateBean;
import com.mrb.bean.PageBean;
import com.mrb.bean.ProjectBean;
import com.mrb.bs.BrandBS;
import com.mrb.bs.OperateBS;
import com.mrb.bs.PcateBS;
import com.mrb.bs.ProjectBS;
import com.mrb.form.JsonForm;
import com.mrb.pbean.BrandReqBean;
import com.mrb.pbean.BrandRespBean;
import com.mrb.pbean.PCate4PhoneBean;
import com.mrb.pbean.PCateRespBean;
import com.mrb.pbean.PReviewBean;
import com.mrb.pbean.PReviewRespBean;
import com.mrb.pbean.Project4PhoneBean;
import com.mrb.pbean.ProjectInfoReqBean;
import com.mrb.pbean.ProjectInfoRespBean;
import com.mrb.pbean.ProjectReqBean;
import com.mrb.pbean.ProjectRespBean;
import com.mrb.pbean.ResBean;
import com.mrb.util.DateUtil;
import com.mrb.util.PageUtil;

/**
 * @author Administrator 9:06:26 PM
 * 
 *         项目注册的Action
 */
public class ProjectAction extends Action {

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

		ProjectBS bs = new ProjectBS();
		Gson gson = new Gson();
		OperateBS operBS = new OperateBS();
		OperateBean operBean = new OperateBean();

		if ("list".equals(act)) { // 获取项目列表 for ajax
			PageBean pbean = null;
			try {
				pbean = gson.fromJson(msg, PageBean.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			if (pbean == null) {
				result = "参数非法";
			} else {
				pbean.setPerpage(5);
				pbean.setMaxpage(5);
				pbean.setTotal((bs.getProjectCnt() - 1) / pbean.getPerpage()
						+ 1);

				ArrayList<ProjectBean> ulist = bs.getProjectList(
						(pbean.getP() - 1) * pbean.getPerpage(),
						pbean.getPerpage());
				req.setAttribute("ulist", ulist);

				StringBuilder html = new StringBuilder(
						"<div class=\"well\"><table class=\"table\"><thead><tr><th>项目ID</th><th>项目名</th><th>图标</th><th>定位</th><th>地址</th><th>时间</th><th style=\"width: 26px;\"></th></tr></thead><tbody>");
				if (ulist != null && ulist.size() > 0) {
					for (int i = 0; i < ulist.size(); i++) {
						ProjectBean bean = (ProjectBean) ulist.get(i);
						html.append("<tr><td>" + bean.getPid() + "</td><td>"
								+ bean.getName() + "</td><td>" + bean.getIuri()
								+ "</td><td>" + bean.getLevel() + "</td><td>"
								+ bean.getArea() + "</td><td>");

						html.append("<td>" + bean.getDate() + "</td>");

						html.append("<td><a href=\"javascript:void(0)\"}\" onclick=\"gotoedit(this);\"><i class=\"icon-pencil\"></i></a>&nbsp;&nbsp;");
						html.append("<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\" onclick=\"setpid(this);\"><i class=\"icon-remove\"></i></a></td></tr>");
					}

				} else {
					html.append("<tr><td>没有项目记录！</td><td></td><td></td><td></td></tr>");
				}

				PageUtil util = new PageUtil();
				html.append(util.pagination(pbean));

				html.append("</tbody></table></div>");

				result = html.toString();
			}

		} else if ("listall".equals(act)) { // 获取项目列表 for 手机端

			ProjectReqBean reqBean = null;
			try {
				reqBean = gson.fromJson(msg, ProjectReqBean.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			ProjectRespBean respBean = new ProjectRespBean();
			if (reqBean == null) {
				respBean.setCode("1701");
				respBean.setMsg("参数非法");
			} else if (reqBean.getPcid() < 0) {
				respBean.setCode("1702");
				respBean.setMsg("项目分类ID非法");
			} else if (reqBean.getCid() < 0) {
				respBean.setCode("1703");
				respBean.setMsg("城市ID非法");
			} else if (reqBean.getStart() < 0) {
				respBean.setCode("1704");
				respBean.setMsg("开始索引start值非法");
			} else if (reqBean.getNum() < 0) {
				respBean.setCode("1705");
				respBean.setMsg("num值非法");
			} else {
				ArrayList<Project4PhoneBean> ulist = bs
						.getProjec4PhonetList(reqBean);

				if (ulist == null || ulist.size() < 0) {
					respBean.setCode("1706");
					respBean.setMsg("查询项目列表失败");
				} else if (ulist.size() >= 0) {
					respBean.setCode("0000");
					respBean.setMsg("交易成功");
					respBean.setStart(reqBean.getStart());
					respBean.setNum(ulist.size());
					respBean.setPlist(ulist);
				} else {
					respBean.setCode("1700");
					respBean.setMsg("交易失败");
				}
			}
			result = gson.toJson(respBean);
		} else if ("story".equals(act)) { // 获取品牌故事 for 手机端

			BrandReqBean reqBean = null;
			try {
				reqBean = gson.fromJson(msg, BrandReqBean.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			BrandBS brandBS = new BrandBS();
			BrandRespBean respBean = new BrandRespBean();
			if (reqBean == null) {
				respBean.setCode("1701");
				respBean.setMsg("参数非法");
			} else if (reqBean.getPid() < 0) {
				respBean.setCode("1702");
				respBean.setMsg("项目ID非法");
			} else if (reqBean.getStart() < 0) {
				respBean.setCode("1704");
				respBean.setMsg("开始索引start值非法");
			} else if (reqBean.getNum() < 0) {
				respBean.setCode("1705");
				respBean.setMsg("num值非法");
			} else {
				ArrayList<Brand2ShowBean> ulist = brandBS
						.getBrandListByPid(reqBean);

				if (ulist == null || ulist.size() < 0) {
					respBean.setCode("1706");
					respBean.setMsg("查询品牌故事失败");
				} else if (ulist.size() >= 0) {
					respBean.setCode("0000");
					respBean.setMsg("交易成功");
					respBean.setNum(ulist.size());
					respBean.setBlist(gson.toJson(ulist,
							new TypeToken<ArrayList<Brand2ShowBean>>() {
							}.getType()));
				} else {
					respBean.setCode("1700");
					respBean.setMsg("交易失败");
				}
			}
			result = gson.toJson(respBean);
		} else if ("info".equals(act)) { // 获取项目参数 for 手机端

			ProjectInfoReqBean reqBean = null;
			try {
				reqBean = gson.fromJson(msg, ProjectInfoReqBean.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			ProjectInfoRespBean respBean = new ProjectInfoRespBean();
			if (reqBean == null) {
				respBean.setCode("1701");
				respBean.setMsg("参数非法");
			} else if (reqBean.getPid() < 0) {
				respBean.setCode("1702");
				respBean.setMsg("项目ID非法");
			} else {

				ProjectBean pBean = bs.getProjectById(reqBean.getPid());
				respBean.setArea(pBean.getArea());
				respBean.setBuild(pBean.getBuild());
				respBean.setChain(pBean.getChain());
				respBean.setFee(pBean.getFee());
				respBean.setLevel(pBean.getLevel());
				respBean.setName(pBean.getName());
				respBean.setPack(pBean.getPack());
				respBean.setSale(pBean.getSale());
				respBean.setStore(pBean.getStore());

				if (pBean == null) {
					respBean.setCode("1706");
					respBean.setMsg("查询品牌故事失败");
				} else {
					respBean.setCode("0000");
					respBean.setMsg("交易成功");
				}
			}
			result = gson.toJson(respBean);

		} else if ("category".equals(act)) { // 获取项目分类列表 for 手机端

			PcateBS pbs = new PcateBS();

			PCateRespBean respBean = new PCateRespBean();

			ArrayList<PCate4PhoneBean> ulist = pbs.getPCate4PhonetList();

			if (ulist == null || ulist.size() < 0) {
				respBean.setCode("1706");
				respBean.setMsg("查询项目分类列表失败");
			} else if (ulist.size() >= 0) {
				respBean.setCode("0000");
				respBean.setMsg("交易成功");
				respBean.setNum(ulist.size());
				respBean.setClist(ulist);
			} else {
				respBean.setCode("1700");
				respBean.setMsg("交易失败");
			}
			result = gson.toJson(respBean);

		} else if ("review".equals(act)) { // 获取项目评论 for 手机端

			BrandReqBean reqBean = null;
			try {
				reqBean = gson.fromJson(msg, BrandReqBean.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			PReviewRespBean respBean = new PReviewRespBean();
			if (reqBean == null) {
				respBean.setCode("1801");
				respBean.setMsg("参数非法");
			} else if (reqBean.getNum() < 0) {
				respBean.setCode("1802");
				respBean.setMsg("参数非法");
			} else {
				ArrayList<PReviewBean> ulist = bs.getPReviewList(reqBean);

				if (ulist == null || ulist.size() < 0) {
					respBean.setCode("1706");
					respBean.setMsg("查询项目分类列表失败");
				} else if (ulist.size() >= 0) {
					respBean.setCode("0000");
					respBean.setMsg("交易成功");
					respBean.setNum(ulist.size());
					respBean.setRlist(ulist);
				} else {
					respBean.setCode("1700");
					respBean.setMsg("交易失败");
				}
			}
			result = gson.toJson(respBean);

		} else if ("doreview".equals(act)) { // 评论项目 for 手机端

			PReviewBean reqBean = null;
			try {
				reqBean = gson.fromJson(msg, PReviewBean.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			ResBean respBean = new ResBean();
			if (reqBean == null) {
				respBean.setCode("1801");
				respBean.setMsg("参数非法");
			} else if (reqBean.getUid() <= 0) {
				respBean.setCode("1802");
				respBean.setMsg("用户ID非法");
			} else if (reqBean.getPid() <= 0) {
				respBean.setCode("1803");
				respBean.setMsg("项目ID非法");
			} else {
				bs.addPReview(reqBean);
				respBean.setCode("0000");
				respBean.setMsg("交易成功");
			}
			result = gson.toJson(respBean);

		} else if ("add".equals(act)) { // 添加项目 for 跳转
			ProjectBean bean = (ProjectBean) gson.fromJson(msg,
					ProjectBean.class);
			if (bean != null) {
				if (!bs.addProject(bean)) {
					result = "添加失败";
				}
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {
				operBean.setUid(Long.valueOf(suid));
				operBean.setOper("增加项目");
				operBS.addOperate(operBean);

				return mapping.findForward("list");
			} else {
				req.setAttribute("project", bean);
				req.setAttribute("result", result);
				return mapping.findForward("add");
			}
		} else if ("edit".equals(act)) {// 编辑项目 for 跳转
			ProjectBean project = null;
			ProjectBean bean = (ProjectBean) gson.fromJson(msg,
					ProjectBean.class);
			if (bean != null) {
				log.info("pid = [" + bean.getPid() + "]");
				project = bs.getProjectById(bean.getPid());
			} else {
				log.error("bean is null");
				result = "参数非法";
			}

			if (project != null) {
				req.setAttribute("project", project);
			} else {
				result = "未找到该项目";
			}

			if ("ok".equals(result)) {
				return mapping.findForward(act);
			} else {
				req.setAttribute("result", result);
				return mapping.findForward("list");
			}

		} else if ("del".equals(act)) { // 删除项目 for ajax
			ProjectBean bean = (ProjectBean) gson.fromJson(msg,
					ProjectBean.class);
			if (bean != null) {
				log.info("pid = [" + bean.getPid() + "]");
				if (!bs.delProjectById(bean.getPid())) {
					result = "删除失败";
				} else {
					operBean.setUid(Long.valueOf(suid));
					operBean.setOper("删除项目");
					operBS.addOperate(operBean);
				}

			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			return mapping.findForward("list");

		} else if ("update".equals(act)) { // 更新项目 for 跳转
			ProjectBean bean = (ProjectBean) gson.fromJson(msg,
					ProjectBean.class);
			if (bean != null) {
				log.info("pid = [" + bean.getPid() + "]");
				bean.setDate(DateUtil.getNow());
				if (!bs.updateProject(bean)) {
					result = "更新失败";
				}
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {
				operBean.setUid(Long.valueOf(suid));
				operBean.setOper("更新项目");
				operBS.addOperate(operBean);
				req.setAttribute("project", bean);

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
