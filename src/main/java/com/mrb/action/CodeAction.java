/**
 * Feb 24, 2011 
 * RegisterAction.java 
 */
package com.mrb.action;

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
import com.mrb.bean.CodeBean;
import com.mrb.bean.CodeReqBean;
import com.mrb.bean.CodeResBean;
import com.mrb.bean.ResBean;
import com.mrb.bs.CodeBS;
import com.mrb.util.CodeUtil;

/**
 * @author Administrator 9:06:26 PM
 * 
 *         校验码注册的Action
 */
public class CodeAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) {

		Logger log = Logger.getLogger(this.getClass());
		String result = "ok";
		String act = ((JsonForm) form).getAct();
		String msg = ((JsonForm) form).getMsg();
		log.debug("act = " + act + ", msg = " + msg);

		CodeBS bs = new CodeBS();
		CodeUtil util = new CodeUtil();
		Gson gson = new Gson();
		if ("snd".equals(act)) { // 获取校验码列表 for ajax
			CodeReqBean reqBean = (CodeReqBean) gson.fromJson(msg,
					CodeReqBean.class);
			CodeResBean respBean = new CodeResBean();

			CodeBean bean = util.sendCode(reqBean.getPhone());
			bean.setPhone(reqBean.getPhone());

			if (bean != null) {
				if (bs.addCode(bean)) {
					respBean.setCode("0000");
					respBean.setMsg("发送成功");
					respBean.setPhone(bean.getPhone());
					respBean.setChkcode(bean.getCode());
				} else {
					respBean.setCode("3000");
					respBean.setMsg("发送失败");
				}
			} else {
				respBean.setCode("3000");
				respBean.setMsg("发送失败");
			}
			result = gson.toJson(respBean);

		} else if ("chk".equals(act)) { // 添加校验码 for ajax
			CodeReqBean reqBean = (CodeReqBean) gson.fromJson(msg,
					CodeReqBean.class);
			CodeBean bean = new CodeBean();

			bean.setCode(reqBean.getChkcode());
			bean.setPhone(reqBean.getPhone());

			ResBean resBean = new ResBean();
			Integer cnt = bs.chkCode(bean);
			if (cnt > 0) {

				bean.setStatus("1");
				if (bs.updateCode(bean)) {
					resBean.setCode("0000");
					resBean.setMsg("校验通过");
				} else {
					resBean.setCode("4000");
					resBean.setMsg("校验不通过");
				}

			} else {
				resBean.setCode("4000");
				resBean.setMsg("校验不通过");
			}
			result = gson.toJson(resBean);

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
