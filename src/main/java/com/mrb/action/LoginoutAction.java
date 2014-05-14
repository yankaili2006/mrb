/**
 * Feb 24, 2011 
 * RegisterAction.java 
 */
package com.mrb.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mrb.form.JsonForm;

/**
 * @author Administrator 9:06:26 PM
 * 
 *         用户注册的Action
 */
public class LoginoutAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) {

		Logger log = Logger.getLogger(this.getClass());
		String act = ((JsonForm) form).getAct();
		String msg = ((JsonForm) form).getMsg();
		log.debug("act = " + act + ", msg = " + msg);

		req.getSession().setAttribute("uid", null);
		
		res.setCharacterEncoding("UTF-8");

		return mapping.findForward("admin");
	}

}
