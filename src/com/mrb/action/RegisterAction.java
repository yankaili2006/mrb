/**
 * Feb 24, 2011 
 * RegisterAction.java 
 */
package com.mrb.action;

import java.io.BufferedReader;
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
import com.mrb.bean.UserBean;
import com.mrb.bean.RegResBean;
import com.mrb.bs.UserBS;

/**
 * @author Administrator 9:06:26 PM
 * 
 * 用户注册的Action
 */
public class RegisterAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) {

		Logger log = Logger.getLogger(this.getClass());

		UserBean bean = null;
		Gson gson = new Gson();
		try {
			bean = gson.fromJson(req.getReader(), UserBean.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		UserBS bs = new UserBS();
		if (bean != null) {
			bs.addUser(bean);
			log.debug(bean.getUname());
		}
		log.debug("user:" + req.getParameter("user"));

		log.debug("json.getUser()");

		RegResBean resBean = new RegResBean();
		resBean.setCode("0000");
		resBean.setMsg("注册成功");

		PrintWriter pw = null;
		res.setContentType("text/html;charset=UTF-8");
		try {
			pw = res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (pw != null) {
			pw.print(gson.toJson(resBean, RegResBean.class));
			pw.close();
		}

		return null;
	}
}
