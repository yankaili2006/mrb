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
import com.mrb.bean.VCateBean;
import com.mrb.bs.VCateBS;
import com.mrb.bs.VideoBS;
import com.mrb.form.JsonForm;
import com.mrb.pbean.ResBean;
import com.mrb.pbean.VBean;
import com.mrb.pbean.VCateRespBean;
import com.mrb.pbean.VCollectBean;
import com.mrb.pbean.VCollectReqBean;
import com.mrb.pbean.VCollectRespBean;
import com.mrb.pbean.VDetailReqBean;
import com.mrb.pbean.VDetailRespBean;
import com.mrb.pbean.VDoCollectReqBean;
import com.mrb.pbean.VDoPlayReqBean;
import com.mrb.pbean.VDoReviewReqBean;
import com.mrb.pbean.VHisBean;
import com.mrb.pbean.VHisReqBean;
import com.mrb.pbean.VHisRespBean;
import com.mrb.pbean.VListReqBean;
import com.mrb.pbean.VRelateBean;
import com.mrb.pbean.VRelateReqBean;
import com.mrb.pbean.VRelateRespBean;
import com.mrb.pbean.VRespBean;
import com.mrb.pbean.VReviewBean;
import com.mrb.pbean.VReviewReqBean;
import com.mrb.pbean.VReviewResBean;

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

			VCateRespBean respBean = new VCateRespBean();
			VCateBS bs = new VCateBS();
			ArrayList<VCateBean> ulist = bs.getVCateList(0, bs.getVCateCnt());

			if (ulist == null || ulist.size() < 0) {
				respBean.setCode("6003");
				respBean.setMsg("查询视频分类信息失败");
				respBean.setNum(0);
			} else if (ulist.size() >= 0) {
				respBean.setCode("0000");
				respBean.setMsg("交易成功");
				respBean.setNum(ulist.size());
				respBean.setClist(ulist);
			} else {
				respBean.setCode("6004");
				respBean.setMsg("交易失败");
				respBean.setNum(0);
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
			} else if (reqBean.getStart() < 0) {
				respBean.setCode("6003");
				respBean.setMsg("开始索引start值非法");
			} else if (reqBean.getNum() <= 0) {
				respBean.setCode("6004");
				respBean.setMsg("num值非法");
			} else {
				VideoBS bs = new VideoBS();
				ArrayList<VBean> ulist = bs.getVList(reqBean);

				if (ulist == null || ulist.size() < 0) {
					respBean.setCode("6005");
					respBean.setMsg("查询视频列表失败");
					respBean.setStart(0);
					respBean.setNum(0);
				} else if (ulist.size() >= 0) {
					respBean.setCode("0000");
					respBean.setMsg("交易成功");
					respBean.setStart(reqBean.getStart());
					respBean.setNum(ulist.size());
					respBean.setVlist(ulist);
				} else {
					respBean.setCode("6006");
					respBean.setMsg("交易失败");
					respBean.setStart(0);
					respBean.setNum(0);
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
		} else if ("review".equals(act)) { // 获取视频评论 for ajax

			VReviewReqBean reqBean = null;
			try {
				reqBean = gson.fromJson(msg, VReviewReqBean.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			VReviewResBean respBean = new VReviewResBean();
			if (reqBean == null) {
				respBean.setCode("7001");
				respBean.setMsg("参数非法");
			} else if (reqBean.getVid() == null || "".equals(reqBean.getVid())) {
				respBean.setCode("7002");
				respBean.setMsg("视频ID值非法");
			} else {
				VideoBS bs = new VideoBS();

				ArrayList<VReviewBean> rlist = bs.getVReviewList(reqBean);

				if (rlist == null) {
					respBean.setCode("7003");
					respBean.setMsg("查询视频评论信息失败");
				} else if (rlist.size() <= 0) {
					respBean.setCode("7004");
					respBean.setMsg("无视频评论信息");
				} else {
					respBean.setCode("0000");
					respBean.setMsg("交易成功");
					respBean.setRlist(rlist);
					respBean.setStart(reqBean.getStart());
					respBean.setNum(rlist.size());
				}
			}
			result = gson.toJson(respBean);
		} else if ("his".equals(act)) { // 获取视频评论 for ajax

			VHisReqBean reqBean = null;
			try {
				reqBean = gson.fromJson(msg, VHisReqBean.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			VHisRespBean respBean = new VHisRespBean();
			if (reqBean == null) {
				respBean.setCode("8001");
				respBean.setMsg("参数非法");
			} else if (reqBean.getUid() <= 0) {
				respBean.setCode("8002");
				respBean.setMsg("用户ID值非法");
			} else {
				VideoBS bs = new VideoBS();

				ArrayList<VHisBean> vlist = bs.getVHisList(reqBean);

				if (vlist == null) {
					respBean.setCode("8003");
					respBean.setMsg("查询历史播放信息失败");
				} else if (vlist.size() <= 0) {
					respBean.setCode("8004");
					respBean.setMsg("无历史播放信息");
				} else {
					respBean.setCode("0000");
					respBean.setMsg("交易成功");
					respBean.setVlist(vlist);
					respBean.setStart(reqBean.getStart());
					respBean.setNum(vlist.size());
				}
			}
			result = gson.toJson(respBean);

		} else if ("relate".equals(act)) { // 获取相关视频 for ajax

			VRelateReqBean reqBean = null;
			try {
				reqBean = gson.fromJson(msg, VRelateReqBean.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			VRelateRespBean respBean = new VRelateRespBean();
			if (reqBean == null) {
				respBean.setCode("9001");
				respBean.setMsg("参数非法");
			} else if (reqBean.getVid() == null || "".equals(reqBean.getVid())) {
				respBean.setCode("9002");
				respBean.setMsg("视频ID值非法");
			} else {
				VideoBS bs = new VideoBS();

				ArrayList<VRelateBean> vlist = bs.getVRelateList(reqBean);

				if (vlist == null) {
					respBean.setCode("9003");
					respBean.setMsg("查询相关视频列表失败");
				} else if (vlist.size() <= 0) {
					respBean.setCode("9004");
					respBean.setMsg("无相关视频信息");
				} else {
					respBean.setCode("0000");
					respBean.setMsg("交易成功");
					respBean.setRlist(vlist);
					respBean.setStart(reqBean.getStart());
					respBean.setNum(vlist.size());
				}
			}
			result = gson.toJson(respBean);

		} else if ("collect".equals(act)) { // 获取收藏列表 for ajax

			VCollectReqBean reqBean = null;
			try {
				reqBean = gson.fromJson(msg, VCollectReqBean.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			VCollectRespBean respBean = new VCollectRespBean();
			if (reqBean == null) {
				respBean.setCode("1101");
				respBean.setMsg("参数非法");
			} else if (reqBean.getUid() <= 0) {
				respBean.setCode("1102");
				respBean.setMsg("用户ID值非法");
			} else {
				VideoBS bs = new VideoBS();

				ArrayList<VCollectBean> vlist = bs.getVCollectList(reqBean);

				if (vlist == null) {
					respBean.setCode("1103");
					respBean.setMsg("查询收藏视频列表失败");
				} else if (vlist.size() <= 0) {
					respBean.setCode("1104");
					respBean.setMsg("无收藏视频信息");
				} else {
					respBean.setCode("0000");
					respBean.setMsg("交易成功");
					respBean.setVlist(vlist);
					respBean.setStart(reqBean.getStart());
					respBean.setNum(vlist.size());
				}
			}
			result = gson.toJson(respBean);

		} else if ("docollect".equals(act)) { // 收藏视频 for ajax

			VDoCollectReqBean reqBean = null;
			try {
				reqBean = gson.fromJson(msg, VDoCollectReqBean.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			ResBean respBean = new ResBean();
			if (reqBean == null) {
				respBean.setCode("1201");
				respBean.setMsg("参数非法");
			} else if (reqBean.getUid() <= 0) {
				respBean.setCode("1202");
				respBean.setMsg("用户ID值非法");
			} else if (reqBean.getVid() == null || "".equals(reqBean.getVid())) {
				respBean.setCode("1203");
				respBean.setMsg("视频ID值非法");
			} else {
				VideoBS bs = new VideoBS();

				boolean doResult = bs.doVCollect(reqBean);

				if (!doResult) {
					respBean.setCode("1204");
					respBean.setMsg("收藏视频失败");
				} else {
					respBean.setCode("0000");
					respBean.setMsg("交易成功");
				}
			}
			result = gson.toJson(respBean);

		} else if ("doplay".equals(act)) { // 播放视频 for ajax

			VDoPlayReqBean reqBean = null;
			try {
				reqBean = gson.fromJson(msg, VDoPlayReqBean.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			ResBean respBean = new ResBean();
			if (reqBean == null) {
				respBean.setCode("1301");
				respBean.setMsg("参数非法");
			} else if (reqBean.getUid() <= 0) {
				respBean.setCode("1302");
				respBean.setMsg("用户ID值非法");
			} else if (reqBean.getVid() == null || "".equals(reqBean.getVid())) {
				respBean.setCode("1303");
				respBean.setMsg("视频ID值非法");
			} else {
				VideoBS bs = new VideoBS();

				boolean doResult = bs.doVPlay(reqBean);

				if (!doResult) {
					respBean.setCode("1304");
					respBean.setMsg("添加播放视频记录失败");
				} else {
					respBean.setCode("0000");
					respBean.setMsg("交易成功");
				}
			}
			result = gson.toJson(respBean);

		} else if ("doshare".equals(act)) { // 分享视频 for ajax

			VDoCollectReqBean reqBean = null;
			try {
				reqBean = gson.fromJson(msg, VDoCollectReqBean.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			ResBean respBean = new ResBean();
			if (reqBean == null) {
				respBean.setCode("1401");
				respBean.setMsg("参数非法");
			} else if (reqBean.getUid() <= 0) {
				respBean.setCode("1402");
				respBean.setMsg("用户ID值非法");
			} else if (reqBean.getVid() == null || "".equals(reqBean.getVid())) {
				respBean.setCode("1403");
				respBean.setMsg("视频ID值非法");
			} else {
				VideoBS bs = new VideoBS();

				boolean doResult = bs.doVShare(reqBean);

				if (!doResult) {
					respBean.setCode("1404");
					respBean.setMsg("分享视频失败");
				} else {
					respBean.setCode("0000");
					respBean.setMsg("交易成功");
				}
			}
			result = gson.toJson(respBean);

		} else if ("doreview".equals(act)) { // 评论视频 for ajax

			VDoReviewReqBean reqBean = null;
			try {
				reqBean = gson.fromJson(msg, VDoReviewReqBean.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			ResBean respBean = new ResBean();
			if (reqBean == null) {
				respBean.setCode("1401");
				respBean.setMsg("参数非法");
			} else if (reqBean.getUid() <= 0) {
				respBean.setCode("1402");
				respBean.setMsg("用户ID值非法");
			} else if (reqBean.getVid() == null || "".equals(reqBean.getVid())) {
				respBean.setCode("1403");
				respBean.setMsg("视频ID值非法");
			} else if (reqBean.getText() == null
					|| "".equals(reqBean.getText())) {
				respBean.setCode("1404");
				respBean.setMsg("评论内容非法");
			} else {
				VideoBS bs = new VideoBS();

				boolean doResult = bs.doVReview(reqBean);

				if (!doResult) {
					respBean.setCode("1405");
					respBean.setMsg("评论视频失败");
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
