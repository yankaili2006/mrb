package com.mrb.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdUtil {
	public static long generateID() {
		SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
		String now = dfm.format(new Date());

		return Long.valueOf(now);
	}
}
