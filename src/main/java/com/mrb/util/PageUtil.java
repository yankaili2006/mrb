package com.mrb.util;

import com.mrb.bean.PageBean;

public class PageUtil {

	public String pagination(PageBean bean) {
		StringBuilder html = new StringBuilder();
		if (bean.getTotal() > 1 && bean.getP() > 0 && bean.getMaxpage() > 0) {
			html.append("<div class=\"pagination\"><ul>");

			// 第一页
			html.append("<li>");
			html.append("<a href=\"#\"");
			html.append(" onclick =\"load(1)\">");
			html.append("第一页");
			html.append("</a>");
			html.append("</li>");

			// 总页数 <= 5
			if (bean.getTotal() <= bean.getMaxpage()) {
				bean.setStart(1);
				bean.setStop(bean.getTotal());

			} else {
				if (bean.getP() - bean.getMaxpage() / 2 <= 0) {
					bean.setStart(1);
					bean.setStop(bean.getMaxpage());
				} else if (bean.getP()
						+ (bean.getMaxpage() - bean.getMaxpage() / 2) - 1 > bean
							.getTotal()) {
					bean.setStart(bean.getTotal() - bean.getMaxpage() + 1);
					bean.setStop(bean.getTotal());
				} else {

					bean.setStart(bean.getP() - bean.getMaxpage() / 2);
					bean.setStop(bean.getP()
							+ (bean.getMaxpage() - bean.getMaxpage() / 2) - 1);
				}
			}

			// 上一页
			if (bean.getP() == bean.getStart()) {
				bean.setPre(bean.getP());
			} else {
				bean.setPre(bean.getP() - 1);
			}
			html.append("<li>");
			html.append("<a href=\"#\"");
			html.append(" onclick =\"load(" + bean.getPre() + ")\">");
			html.append("上一页");
			html.append("</a>");
			html.append("</li>");

			// 主体页
			for (int i = bean.getStart(); i <= bean.getStop(); i++) {
				if (bean.getP() != i) {
					html.append("<li>");
				} else {
					html.append("<li class=\"active\">");
				}
				html.append("<a href=\"#\"");
				html.append(" onclick =\"load(" + i + ")\">");
				html.append(i);
				html.append("</a>");
				html.append("</li>");
			}

			// 下一页
			if (bean.getP() == bean.getStop()) {
				bean.setNext(bean.getP());
			} else {
				bean.setNext(bean.getP() + 1);
			}
			html.append("<li>");
			html.append("<a href=\"#\"");
			html.append(" onclick =\"load(" + bean.getNext() + ")\">");
			html.append("下一页");
			html.append("</a>");
			html.append("</li>");

			// 最后一页
			html.append("<li>");
			html.append("<a href=\"#\"");
			html.append(" onclick =\"load(" + bean.getTotal() + ")\">");
			html.append("最后一页");
			html.append("</a>");
			html.append("</li>");

			html.append("</ul></div>");
		}

		return html.toString();
	}

	public static void main(String[] args) {
		PageUtil util = new PageUtil();
		PageBean bean = new PageBean();
		bean.setTotal(1);
		bean.setMaxpage(5);
		bean.setP(1);

		System.out.println(util.pagination(bean));
	}
}
