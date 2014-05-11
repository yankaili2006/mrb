/**
 * Feb 24, 2011 
 * RegisterForm.java 
 */
package com.mrb.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Administrator 9:03:50 PM
 */
public class JsonForm extends ActionForm {

	private String act;
	private String msg;

	public JsonForm() {
		act = null;
		msg = null;
	}

	/**
	 * @return the act
	 */
	public String getAct() {
		return act;
	}

	/**
	 * @param act the act to set
	 */
	public void setAct(String act) {
		this.act = act;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		act = null;
		msg = null;
	}

}
