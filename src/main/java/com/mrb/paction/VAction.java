/**
 * Feb 24, 2011 
 * RegisterAction.java 
 */
package com.mrb.paction;

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
import com.mrb.bean.VCateBean;
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
			VCateReqBean reqBean = null;
			try {
				reqBean = gson.fromJson(msg, VCateReqBean.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			VCateRespBean respBean = new VCateRespBean();
			if (reqBean == null) {
				respBean.setCode("6001");
				respBean.setMsg("参数非法");
			} else if (reqBean.getNum() <= 0) {
				respBean.setCode("6002");
				respBean.setMsg("num值非法");
			} else {
				VCateBS bs = new VCateBS();
				ArrayList<VCateBean> ulist = bs.getVCateList(0,
						reqBean.getNum());

				if (ulist == null || ulist.size() < 0) {
					respBean.setCode("6003");
					respBean.setMsg("查询视频分类信息失败");
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
					respBean.setCode("6004");
					respBean.setMsg("交易失败");
					respBean.setNum(0);
					respBean.setClist("");
				}
			}
			result = gson.toJson(respBean);
		} else if ("list".equals(act)) { // 获取视频列表 for ajax

			VListReqBean reqBean = null;
			try {
				reqBean = gson.fromJson(msg, VListReqBean.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			VRespBean respBean = new VRespBean();
			if (reqBean == null) {
				respBean.setCode("6001");
				respBean.setMsg("参数非法");
			} else if (reqBean.getVcid() <= 0) {
				respBean.setCode("6002");
				respBean.setMsg("视频分类ID值非法");
			} else if (reqBean.getStart() <= 0) {
				respBean.setCode("6003");
				respBean.setMsg("开始索引start值非法");
			} else if (reqBean.getNum() <= 0) {
				respBean.setCode("6004");
				respBean.setMsg("num值非法");
			} else {
				VideoBS bs = new VideoBS();
				ArrayList<VBean> ulist = bs.getVList(reqBean.getVcid(),
						reqBean.getStart(), reqBean.getNum());

				if (ulist == null || ulist.size() < 0) {
					respBean.setCode("6005");
					respBean.setMsg("查询视频列表失败");
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
					respBean.setCode("6006");
					respBean.setMsg("交易失败");
					respBean.setStart(0);
					respBean.setNum(0);
					respBean.setVlist("");
				}
			}
			result = gson.toJson(respBean);

		} else if ("detail".equals(act)) { // 获取视频详情 for ajax

			VDetailReqBean reqBean = null;
			try {
				reqBean = gson.fromJson(msg, VDetailReqBean.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			VDetailRespBean respBean = new VDetailRespBean();
			if (reqBean == null) {
				respBean.setCode("6001");
				respBean.setMsg("参数非法");
			} else if (reqBean.getUid() <= 0) {
				respBean.setCode("6002");
				respBean.setMsg("用户ID值非法");
			} else if (reqBean.getVid() == null || "".equals(reqBean.getVid())) {
				respBean.setCode("6003");
				respBean.setMsg("视频ID值非法");
			} else {
				VideoBS bs = new VideoBS();
				respBean = bs.getVDetail(reqBean);

				if (respBean == null) {
					respBean = new VDetailRespBean();
					respBean.setCode("6004");
					respBean.setMsg("查询视频详细信息失败");
				} else {
					respBean.setCode("0000");
					respBean.setMsg("交易成功");
				}
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
