/**
 * Feb 24, 2011 
 * RegisterAction.java 
 */
package com.mrb.paction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.mrb.form.JsonForm;
import com.mrb.pbean.PwdBean;
import com.mrb.pbean.PwdRespBean;
import com.mrb.pbean.ResBean;
import com.mrb.pbs.PwdBS;

/**
 * @author Administrator 9:06:26 PM
 * 
 *         密码注册的Action for phone
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
			PwdBean bean = null;
			try {
				bean = (PwdBean) gson.fromJson(msg, PwdBean.class);
			} catch (Exception e) {
				log.error(e.getStackTrace());
			}

			PwdRespBean resBean = new PwdRespBean();

			if (bean == null) {
				resBean.setCode("5001");
				resBean.setMsg("参数非法");
			} else if (bean.getUid() <= 0) {
				resBean.setCode("5002");
				resBean.setMsg("用户ID非法");
			} else if (bean.getOldpwd() == null || "".equals(bean.getOldpwd())) {
				resBean.setCode("5003");
				resBean.setMsg("原密码不能为空");
			} else if (bean.getNewpwd() == null || "".equals(bean.getNewpwd())) {
				resBean.setCode("5004");
				resBean.setMsg("新密码不能为空");
			} else {

				if (bs.chgPwd(bean) == 0) {
					resBean.setCode("0000");
					resBean.setMsg("交易成功");
					resBean.setUid(bean.getUid());
				} else if (bs.chgPwd(bean) == -100) {
					resBean.setCode("5005");
					resBean.setMsg("原密码校验失败");
				} else if (bs.chgPwd(bean) == -200) {
					resBean.setCode("5006");
					resBean.setMsg("更新密码失败");
				} else {
					resBean.setCode("5007");
					resBean.setMsg("未知错误");
				}
			}

			result = gson.toJson(resBean);

		} else if ("set".equals(act)) { // 重置密码 for 跳转
			PwdBean bean = null;
			try {
				bean = (PwdBean) gson.fromJson(msg, PwdBean.class);
			} catch (Exception e) {
				log.error(e.getStackTrace());
			}

			PwdRespBean resBean = new PwdRespBean();

			if (bean == null) {
				resBean.setCode("6001");
				resBean.setMsg("参数非法");
			} else if (bean.getUid() <= 0) {
				resBean.setCode("6002");
				resBean.setMsg("用户ID非法");
			} else if (bean.getNewpwd() == null || "".equals(bean.getNewpwd())) {
				resBean.setCode("6003");
				resBean.setMsg("新密码不能为空");
			} else {
				// 设置新密码
				if (bs.setPwd(bean) == 0) {
					resBean.setCode("0000");
					resBean.setMsg("交易成功");
					resBean.setUid(bean.getUid());
				} else if (bs.setPwd(bean) == -100) {
					resBean.setCode("6004");
					resBean.setMsg("未找到该用户");
				} else {
					resBean.setCode("6000");
					resBean.setMsg("设置密码失败");
				}
			}
			result = gson.toJson(resBean);
		} else {
			ResBean resp = new ResBean();
			resp.setCode("9999");
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
