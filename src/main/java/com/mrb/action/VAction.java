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
import com.google.gson.reflect.TypeToken;
import com.mrb.bean.PageBean;
import com.mrb.bean.VCateBean;
import com.mrb.bean.VideoBean;
import com.mrb.bs.VCateBS;
import com.mrb.bs.VideoBS;
import com.mrb.form.JsonForm;
import com.mrb.pbean.VBean;
import com.mrb.pbean.VCateReqBean;
import com.mrb.pbean.VCateRespBean;
import com.mrb.pbean.VDetailReqBean;
import com.mrb.pbean.VDetailRespBean;
import com.mrb.pbean.VListReqBean;
import com.mrb.pbean.VRespBean;
import com.mrb.util.PageUtil;

/**
 * @author Administrator 9:06:26 PM
 * 
 *         视频注册的Action
 */
public class VAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) {

		Logger log = Logger.getLogger(this.getClass());
		String result = "ok";
		String act = ((JsonForm) form).getAct();
		String msg = ((JsonForm) form).getMsg();
		log.debug("act = " + act + ", msg = " + msg);

		Gson gson = new Gson();
		if ("category".equals(act)) {
			VCateReqBean reqBean = gson.fromJson(msg, VCateReqBean.class);
			VCateBS bs = new VCateBS();
			ArrayList<VCateBean> ulist = bs.getVCateList(0, reqBean.getNum());

			VCateRespBean respBean = new VCateRespBean();
			if (ulist == null) {
				respBean.setCode("6000");
				respBean.setMsg("交易失败");
				respBean.setNum(0);
				respBean.setClist("");
			} else if (ulist.size() >= 0) {
				respBean.setCode("0000");
				respBean.setMsg("交易成功");
				respBean.setNum(ulist.size());
				respBean.setClist(gson.toJson(ulist,
						new TypeToken<ArrayList<VCateBean>>() {
						}.getType()));
			} else {
				respBean.setCode("6000");
				respBean.setMsg("交易失败");
				respBean.setNum(0);
				respBean.setClist("");
			}
			result = gson.toJson(respBean);
		} else if ("list".equals(act)) { // 获取视频列表 for ajax

			VListReqBean reqBean = gson.fromJson(msg, VListReqBean.class);
			VideoBS bs = new VideoBS();
			ArrayList<VBean> ulist = bs.getVList(reqBean.getVcid(),
					reqBean.getStart(), reqBean.getNum());

			VRespBean respBean = new VRespBean();
			if (ulist == null) {
				respBean.setCode("6001");
				respBean.setMsg("交易失败");
				respBean.setStart(0);
				respBean.setNum(0);
				respBean.setVlist("");
			} else if (ulist.size() >= 0) {
				respBean.setCode("0000");
				respBean.setMsg("交易成功");
				respBean.setStart(reqBean.getStart());
				respBean.setNum(ulist.size());
				respBean.setVlist(gson.toJson(ulist,
						new TypeToken<ArrayList<VBean>>() {
						}.getType()));
			} else {
				respBean.setCode("6001");
				respBean.setMsg("交易失败");
				respBean.setStart(0);
				respBean.setNum(0);
				respBean.setVlist("");
			}

			result = gson.toJson(respBean);

		} else if ("detail".equals(act)) { // 获取视频详情 for ajax

			VDetailReqBean reqBean = gson.fromJson(msg, VDetailReqBean.class);
			VideoBS bs = new VideoBS();
			VDetailRespBean respBean = bs.getVDetail(reqBean.getVid(),
					reqBean.getUid());

			if (respBean == null) {
				respBean = new VDetailRespBean();
				respBean.setCode("6001");
				respBean.setMsg("交易失败");
			} else {
				respBean.setCode("0000");
				respBean.setMsg("交易成功");
			}

			result = gson.toJson(respBean);

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
