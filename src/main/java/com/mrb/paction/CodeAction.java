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
import com.mrb.bean.CodeBean;
import com.mrb.bean.CodeReqBean;
import com.mrb.bean.CodeResBean;
import com.mrb.bean.UserBean;
import com.mrb.bs.UserBS;
import com.mrb.form.JsonForm;
import com.mrb.pbean.ResBean;
import com.mrb.pbs.CodeBS;
import com.mrb.util.CodeUtil;

/**
 * @author Administrator 9:06:26 PM
 * 
 *         校验码注册的Action
 */
public class CodeAction extends Action {

	private Logger log = Logger.getLogger(this.getClass());

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

			CodeReqBean reqBean = null;
			try {
				reqBean = (CodeReqBean) gson.fromJson(msg, CodeReqBean.class);
			} catch (Exception e) {
				log.error(e.getStackTrace());
			}
			// 判断参数
			CodeResBean respBean = new CodeResBean();
			if (reqBean == null) {
				respBean.setCode("3001");
				respBean.setMsg("参数解析失败");
			} else if (reqBean.getPhone() == null
					|| "".equals(reqBean.getPhone())) {
				respBean.setCode("3002");
				respBean.setMsg("手机号码不能为空");
			} else {
				// 发送校验码
				CodeBean bean = util.sendCode(reqBean.getPhone());

				if (bean != null) {
					// 添加校验码到数据库
					bean.setPhone(reqBean.getPhone());
					if (bs.addCode(bean)) {
						respBean.setCode("0000");
						respBean.setMsg("发送成功");
						respBean.setPhone(reqBean.getPhone());
						respBean.setChkcode(bean.getCode());
						log.error("发送成功, reqBean.getPhone() = "
								+ reqBean.getPhone());
					} else {
						respBean.setCode("3003");
						respBean.setMsg("写入数据库失败, addCode");
						log.error("写入数据库失败, reqBean.getPhone() = "
								+ reqBean.getPhone());
					}
				} else {
					respBean.setCode("3000");
					respBean.setMsg("发送失败");
					log.error("发送失败, reqBean.getPhone() = "
							+ reqBean.getPhone());

				}
			}
			result = gson.toJson(respBean);

		} else if ("chk".equals(act)) { // 添加校验码 for ajax

			CodeReqBean reqBean = null;
			try {
				reqBean = (CodeReqBean) gson.fromJson(msg, CodeReqBean.class);
			} catch (Exception e) {
				log.error(e.getStackTrace());
			}

			CodeBean bean = new CodeBean();
			ResBean resBean = new ResBean();
			if (reqBean == null) {
				resBean.setCode("4001");
				resBean.setMsg("参数错误");
			} else if (reqBean.getChkcode() == null
					|| "".equals(reqBean.getChkcode())) {
				resBean.setCode("4002");
				resBean.setMsg("校验码不能为空");
			} else if (reqBean.getPhone() == null
					|| "".equals(reqBean.getPhone())) {
				resBean.setCode("4003");
				resBean.setMsg("手机号不能为空");
			} else {

				// 校验校验码
				bean.setCode(reqBean.getChkcode());
				bean.setPhone(reqBean.getPhone());
				Integer cnt = bs.chkCode(bean);

				if (cnt > 0) {
					// 更新数据库，校验通过
					bean.setStatus("1");
					if (bs.updateCode(bean)) {
						UserBS userBS = new UserBS();
						UserBean userBean = userBS.getUserByPhone(reqBean
								.getPhone());
						if (userBean != null) {
							resBean.setUid(userBean.getUid());
						}
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
