package com.mrb.util;

import java.util.Random;

import org.apache.log4j.Logger;

import com.mrb.bean.CodeBean;
import com.mrb.bean.ParamBean;
import com.mrb.bs.ParamBS;

public class CodeUtil {

	private Logger log = Logger.getLogger(this.getClass());

	public CodeBean sendCode(String phone) {
		CodeBean bean = new CodeBean();

		if (phone == null || "".equals(phone)) {
			log.debug("发送手机校验码: 输入的手机号为空");
			return null;
		}

		// 生成code
		StringBuilder code = new StringBuilder();
		Integer[] rand = new Integer[6];
		for (int i = 0; i < rand.length; i++)
			rand[i] = (Integer) new Random().nextInt(9);

		for (Integer i : rand)
			code.append(i);
		if (code == null || code.length() <= 0) {
			log.debug("发送手机校验码: 生成手机验证码失败");
			return null;
		}

		ParamBS paramBS = new ParamBS();
		ParamBean paramBean = paramBS.getParamById("sms");

		String sms = "【美人邦】美人邦动态验证码：" + code
				+ "，5分钟内有效。如非本人操作，请忽略本短信。切勿泄漏给他人，以防您的帐号被盗风险";
		if (paramBean != null) {
			String paramValue = paramBean.getParam_value();
			if (paramValue != null && !"".equals(paramValue)
					&& paramValue.contains("#code#")) {
				sms = paramValue.replace("#code#", code);
			}
		}
		// 发送code给手机
		xmlEntity xmlentity = new xmlEntity();
		String xml = null;
		HttpSMS t = new HttpSMS();

		xml = t.SendMessage("mrb", "xd000032", "xd000032", phone, sms, "")
				.toString();
		log.debug("xml.toString():" + xml.toString());
		xmlentity.setReturnstatus("returnstatus");
		xmlentity.setMessage("message");
		xmlentity.setRemainpoint("remainpoint");
		xmlentity.setTaskID("taskID");
		xmlentity.setSuccessCounts("successCounts");
		xmlentity = t.readStringXmlCommen(xmlentity, xml);
		System.out.println("状态" + xmlentity.getReturnstatus() + "返回信息"
				+ xmlentity.getMessage() + "成功条数"
				+ xmlentity.getSuccessCounts());

		bean.setCode(code.toString());
		// 发送code
		return bean;
	}
}
