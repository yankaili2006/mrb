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
import com.mrb.bean.VCateBean;
import com.mrb.bean.VideoBean;
import com.mrb.bean.VideoReqBean;
import com.mrb.bs.OperateBS;
import com.mrb.bs.VCateBS;
import com.mrb.bs.VideoBS;
import com.mrb.form.JsonForm;
import com.mrb.util.DateUtil;
import com.mrb.util.PageUtil;

/**
 * @author Administrator 9:06:26 PM
 * 
 *         视频注册的Action
 */
public class VideoAction extends Action {

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

		VideoBS bs = new VideoBS();
		Gson gson = new Gson();
		OperateBS operBS = new OperateBS();
		OperateBean operBean = new OperateBean();

		if ("list".equals(act)) { // 获取视频列表 for ajax

			PageBean pbean = gson.fromJson(msg, PageBean.class);
			pbean.setMaxpage(5);
			pbean.setPerpage(5);
			pbean.setTotal((bs.getVideoCnt() - 1) / pbean.getPerpage() + 1);

			ArrayList<VideoBean> ulist = bs.getVideoList((pbean.getP() - 1)
					* pbean.getPerpage(), pbean.getPerpage());
			req.setAttribute("ulist", ulist);

			StringBuilder html = new StringBuilder(
					"<div class=\"well\"><table class=\"table\"><thead><tr><th>视频ID</th><th>视频名称</th><th>视频缩略图</th><th>上传时间</th><th style=\"width: 50px;\">操作</th></tr></thead><tbody>");
			if (ulist != null && ulist.size() > 0) {
				for (int i = 0; i < ulist.size(); i++) {
					VideoBean bean = (VideoBean) ulist.get(i);
					html.append("<tr><td>" + bean.getVid() + "</td><td>"
							+ bean.getTitle() + "</td><td><img src=\""
							+ bean.getThumbnail_url() + "\"></td><td>"
							+ bean.getDate() + "</td>");
					html.append("<td><a href=\"javascript:void(0)\"}\" onclick=\"gotoedit(this);\"><i class=\"icon-pencil\"></i></a>&nbsp;&nbsp;");
					html.append("<a href=\"#myModal\" role=\"button\" data-toggle=\"modal\" onclick=\"setvid(this);\"><i class=\"icon-remove\"></i></a></td></tr>");
				}

			} else {
				html.append("<tr><td>没有视频记录！</td><td></td><td></td><td></td></tr>");
			}
			html.append("</tbody></table></div>");

			PageUtil util = new PageUtil();
			html.append(util.pagination(pbean));

			result = html.toString();

		} else if ("add".equals(act)) { // 添加视频 for 跳转
			VideoReqBean bean = (VideoReqBean) gson.fromJson(msg,
					VideoReqBean.class);
			if (bean != null) {
				if (!bs.addVideo(bean)) {
					result = "添加失败";
				}
			} else {
				result = "参数非法";
			}

			req.setAttribute("result", result);
			if ("ok".equals(result)) {
				operBean.setUid(Long.valueOf(suid));
				operBean.setOper("增加视频");
				operBS.addOperate(operBean);

				return mapping.findForward("list");
			} else {
				req.setAttribute("video", bean);
				req.setAttribute("result", result);
				return mapping.findForward("add");
			}

		} else if ("edit".equals(act)) {// 编辑视频 for 跳转
			VideoBean video = null;
			VideoBean bean = (VideoBean) gson.fromJson(msg, VideoBean.class);
			if (bean != null) {
				log.info("vid = [" + bean.getVid() + "]");
				video = bs.getVideoById(bean.getVid());
			} else {
				log.error("bean is null");
				result = "参数非法";
			}

			if (video != null) {
				req.setAttribute("video", video);

				VCateBS vcateBS = new VCateBS();
				Integer cnt = vcateBS.getVCateCnt();
				ArrayList<VCateBean> vclist = vcateBS.getVCateList(0, cnt);
				if (vclist != null && vclist.size() > 0) {
					req.setAttribute("vclist", vclist);
				}

			} else {
				result = "未找到该视频";
			}

			if ("ok".equals(result)) {
				return mapping.findForward(act);
			} else {
				req.setAttribute("result", result);
				return mapping.findForward("list");
			}

		} else if ("del".equals(act)) { // 删除视频 for ajax
			VideoBean bean = (VideoBean) gson.fromJson(msg, VideoBean.class);
			if (bean != null) {
				log.info("vid = [" + bean.getVid() + "]");
				if (!bs.delVideoById(bean.getVid())) {
					result = "删除失败";
				} else {
					operBean.setUid(Long.valueOf(suid));
					operBean.setOper("删除视频");
					operBS.addOperate(operBean);
				}
			} else {
				result = "参数非法";
			}
			req.setAttribute("result", result);
			return mapping.findForward("list");

		} else if ("update".equals(act)) { // 更新视频 for 跳转
			VideoBean bean = (VideoBean) gson.fromJson(msg, VideoBean.class);
			if (bean != null) {
				log.info("vid = [" + bean.getVid() + "]");
				bean.setOpdate(DateUtil.getNow());
				if (!bs.updateVideo(bean)) {
					result = "更新失败";
				}
			} else {
				result = "参数非法";
			}

			VideoBean resBean = bs.getVideoById(bean.getVid());
			req.setAttribute("result", result);
			if ("ok".equals(result)) {
				operBean.setUid(Long.valueOf(suid));
				operBean.setOper("更新视频");
				operBS.addOperate(operBean);
				req.setAttribute("video", resBean);

				VCateBS vcateBS = new VCateBS();
				Integer cnt = vcateBS.getVCateCnt();
				ArrayList<VCateBean> vclist = vcateBS.getVCateList(0, cnt);
				if (vclist != null && vclist.size() > 0) {
					req.setAttribute("vclist", vclist);
				}
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
