package com.mrb.util;

import com.mrb.bean.CodeBean;

public class CodeUtil {
	public CodeBean sendCode(String phone) {
		CodeBean bean = new CodeBean();

		bean.setCode("111111111");
		// 发送code
		return bean;
	}
}
