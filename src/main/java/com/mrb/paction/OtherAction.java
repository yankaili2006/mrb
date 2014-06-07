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
import com.mrb.bean.ModelImgBean;
import com.mrb.bean.ParamBean;
import com.mrb.bs.ModelImgBS;
import com.mrb.bs.ParamBS;
import com.mrb.form.JsonForm;
import com.mrb.pbean.AboutRespBean;
import com.mrb.pbean.FeedBean;
import com.mrb.pbean.PlayImgReqBean;
import com.mrb.pbean.PlayImgRespBean;
import com.mrb.pbean.ResBean;
import com.mrb.pbean.ShopInfoRespBean;
import com.mrb.pbean.UpdateReqBean;
import com.mrb.pbean.UpdateRespBean;
import com.mrb.pbs.OtherBS;
import com.mrb.util.MrbUtil;

/**
 * @author Administrator 9:06:26 PM
 * 
 *         注册的Action
 */
public class OtherAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) {

		Logger log = Logger.getLogger(this.getClass());
		String result = "ok";
		String act = ((JsonForm) form).getAct();
		String msg = ((JsonForm) form).getMsg();
		log.debug("act = " + act + ", msg = " + msg);

		OtherBS bs = new OtherBS();
		Gson gson = new Gson();

		if ("about".equals(act)) { // 关于我们 for 手机
			AboutRespBean resp = new AboutRespBean();

			ParamBS paramBS = new ParamBS();
			ParamBean paramBean = paramBS.getParamById("about");
			String info = "关于我们";
			if (paramBean != null) {
				info = paramBean.getParam_value();
			}
			resp.setCode("0000");
			resp.setMsg("交易成功");
			resp.setInfo(info);

			result = gson.toJson(resp);

		} else if ("update".equals(act)) { // 版本更新 for 手机
			UpdateReqBean bean = null;
			try {
				bean = (UpdateReqBean) gson.fromJson(msg, UpdateReqBean.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getStackTrace());
			}

			UpdateRespBean resp = new UpdateRespBean();
			if (bean == null) {
				resp.setCode("0001");
				resp.setMsg("请求参数非法");
			} else {

				ParamBS paramBS = new ParamBS();
				ParamBean paramBean = paramBS.getParamById("version_"
						+ bean.getPlatform());

				if (paramBean == null) {
					resp.setCode("0000");
					resp.setMsg("交易成功");
					resp.setPlatform(bean.getPlatform());
					resp.setVersion("1.0.0");
					resp.setUri("mrbv1.0.0.pkg");
				} else {
					resp.setCode("0000");
					resp.setMsg("交易成功");
					resp.setPlatform(bean.getPlatform());
					String paramValue = paramBean.getParam_value();
					if (paramValue != null && !"".equals(paramValue)
							&& paramValue.contains(",")) {
						String[] values = paramValue.split(",");
						resp.setVersion(values[0]);
						resp.setUri(values[1]);
					}
				}
			}
			result = gson.toJson(resp);

		} else if ("feed".equals(act)) { // 有奖反馈 for 手机
			FeedBean bean = null;
			try {
				bean = (FeedBean) gson.fromJson(msg, FeedBean.class);
			} catch (Exception e) {
				log.error(e.getStackTrace());
			}
			ResBean resp = new ResBean();

			OtherBS otherBS = new OtherBS();
			if (otherBS.addFeed(bean)) {
				resp.setCode("0000");
				resp.setMsg("交易成功");
			} else {
				resp.setCode("0001");
				resp.setMsg("交易失败");
			}

			result = gson.toJson(resp);

		} else if ("shopinfo".equals(act)) { // 管店介绍 for 手机
			ShopInfoRespBean resp = new ShopInfoRespBean();

			ParamBS paramBS = new ParamBS();
			ParamBean paramBean = paramBS.getParamById("shopinfo");

			if (paramBean != null) {
				String paramValue = paramBean.getParam_value();
				if (paramValue != null && !"".equals(paramValue)) {
					resp.setCode("0000");
					resp.setMsg("交易成功");
					if (paramValue.contains(",")) {
						String[] paramList = paramValue.split(",");
						resp.setInfo(paramList[0]);
						resp.setPhone(paramList[1]);
					}
				} else {
					resp.setCode("0001");
					resp.setMsg("交易失败");
				}
			} else {
				resp.setCode("0001");
				resp.setMsg("交易失败");
			}

			result = gson.toJson(resp);
		} else if ("memberinfo".equals(act)) { // 会员介绍 for 手机

			ShopInfoRespBean resp = new ShopInfoRespBean();

			ParamBS paramBS = new ParamBS();
			ParamBean paramBean = paramBS.getParamById("memberinfo");

			if (paramBean != null) {
				String paramValue = paramBean.getParam_value();
				if (paramValue != null && !"".equals(paramValue)) {
					resp.setCode("0000");
					resp.setMsg("交易成功");
					if (paramValue.contains(",")) {
						String[] paramList = paramValue.split(",");
						resp.setInfo(paramList[0]);
						resp.setPhone(paramList[1]);
					}
				} else {
					resp.setCode("0001");
					resp.setMsg("交易失败");
				}
			} else {
				resp.setCode("0001");
				resp.setMsg("交易失败");
			}

			result = gson.toJson(resp);

		} else if ("playimg".equals(act)) { // 动态播放图片 for 手机
			PlayImgReqBean bean = null;
			try {
				bean = (PlayImgReqBean) gson
						.fromJson(msg, PlayImgReqBean.class);
			} catch (Exception e) {
				log.error(e.getStackTrace());
			}

			PlayImgRespBean resp = new PlayImgRespBean();

			ModelImgBS modelimgBS = new ModelImgBS();
			bean.setStart(0);
			bean.setNum(modelimgBS.getModelImgCnt());
			ArrayList<ModelImgBean> imglist = modelimgBS
					.getModelImgListByModel(bean);

			if (imglist != null && imglist.size() > 0) {
				resp.setCode("0000");
				resp.setMsg("交易成功");
				resp.setNum(imglist.size());
				if (imglist != null && imglist.size() > 0) {
					ArrayList<String> imgs = new ArrayList<String>();
					for (int i = 0; i < imglist.size(); i++) {
						ModelImgBean imgbean = imglist.get(i);
						imgs.add(MrbUtil.getImgUrl() + "/" + imgbean.getIuri());
					}
					resp.setImgs(imgs);
				}
			} else {
				resp.setCode("0001");
				resp.setMsg("交易失败");
			}

			result = gson.toJson(resp);

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
