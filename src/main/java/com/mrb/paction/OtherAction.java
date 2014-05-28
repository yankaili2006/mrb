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
import com.mrb.bean.BImg2ShowBean;
import com.mrb.bean.BImgBean;
import com.mrb.bean.PageBean;
import com.mrb.bs.BImgBS;
import com.mrb.form.JsonForm;
import com.mrb.pbean.AboutRespBean;
import com.mrb.pbean.BImgReqBean;
import com.mrb.pbean.BImgRespBean;
import com.mrb.pbean.FeedBean;
import com.mrb.pbean.PwdBean;
import com.mrb.pbean.ResBean;
import com.mrb.pbean.UpdateRespBean;
import com.mrb.pbs.OtherBS;
import com.mrb.util.PageUtil;

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

			String info = "关于我们";
			AboutRespBean resp = new AboutRespBean();
			resp.setCode("0000");
			resp.setMsg("交易成功");
			resp.setInfo(info);

			result = gson.toJson(resp);

		} else if ("update".equals(act)) { // 版本更新 for 手机

			UpdateRespBean resp = new UpdateRespBean();
			resp.setCode("0000");
			resp.setMsg("交易成功");
			resp.setVersion("1.0.0");
			resp.setUri("mrbv1.0.0.pkg");

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
			}
			else{
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
