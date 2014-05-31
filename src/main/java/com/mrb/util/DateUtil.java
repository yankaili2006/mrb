package com.mrb.util;

public class DateUtil {
	/*
	 * 20140529003739 2014-05-29 00:37:39
	 */
	public static String format(Long d) {
		StringBuilder sb = new StringBuilder();
		long tmp = 0;
		tmp = (long) Math.pow(10, 10);
		sb.append(String.format("%04d", d / tmp));
		d = d % tmp;

		tmp = (long) Math.pow(10, 8);
		sb.append("-" + String.format("%02d", d / tmp));
		d = d % tmp;

		tmp = (long) Math.pow(10, 6);
		sb.append("-" + String.format("%02d", d / tmp));
		d = d % tmp;

		tmp = (long) Math.pow(10, 4);
		sb.append(" " + String.format("%02d", d / tmp));
		d = d % tmp;

		tmp = (long) Math.pow(10, 2);
		sb.append(":" + String.format("%02d", d / tmp));
		d = d % tmp;

		sb.append(":" + String.format("%02d", d));

		return sb.toString();
	}
}
