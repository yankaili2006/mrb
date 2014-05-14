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
import com.mrb.bean.PageBean;
import com.mrb.bean.PwdBean;
import com.mrb.bean.PwdRespBean;
import com.mrb.bean.ResBean;
import com.mrb.bs.PwdBS;
import com.mrb.form.JsonForm;

/**
 * @author Administrator 9:06:26 PM
 * 
 *         密码注册的Action   for phone
 */
public class PwdAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) {

		Logger log = Logger.getLogger(this.getClass());
		String result = "ok";
		String act = ((JsonForm) form).getAct();
		String msg = ((JsonForm) form).getMsg();
		log.debug("act = " + act + ", msg = " + msg);

		PwdBS bs = new PwdBS();
		Gson gson = new Gson();

		if ("chg".equals(act)) { // 添加密码 for 跳转
			PwdBean bean = (PwdBean) gson.fromJson(msg, PwdBean.class);
			PwdRespBean resBean = new PwdRespBean();

			if (bean != null) {
				if (bs.chgPwd(bean)) {
					resBean.setCode("0000");
					resBean.setMsg("交易成功");
					resBean.setUid(bean.getUid());
				} else {
					resBean.setCode("5000");
					resBean.setMsg("交易失败");
				}
			} else {
				resBean.setCode("5001");
				resBean.setMsg("参数非法");
			}

			result = gson.toJson(resBean);

		} else if ("set".equals(act)) { // 更新密码 for 跳转
			PwdBean bean = (PwdBean) gson.fromJson(msg, PwdBean.class);
			PwdRespBean resBean = new PwdRespBean();
			if (bean != null) {
				if (bs.setPwd(bean)) {
					resBean.setCode("0000");
					resBean.setMsg("交易成功");
					resBean.setUid(bean.getUid());
				} else {
					resBean.setCode("5000");
					resBean.setMsg("交易失败");
				}
			} else {
				resBean.setCode("5001");
				resBean.setMsg("参数非法");
			}

			result = gson.toJson(resBean);

		} else {
			ResBean resp = new ResBean();
			resp.setCode("5002");
			resp.setMsg("不支持的操作类型");
			result = gson.toJson(resp);
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
