/**
 * Feb 25, 2011 
 * AuthorityFilter.java 
 */
package com.mrb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Administrator 5:02:38 PM
 */
public class AuthorityFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String uri = request.getRequestURI();

		HttpSession session = request.getSession();
		if (session.getAttribute("uid") == null) {
			response.sendRedirect(request.getContextPath() + "/admin.jsp");
			return;
		}

		if (uri.endsWith("/") || uri.endsWith("/index.jsp")) {
			response.sendRedirect(request.getContextPath() + "/admin.jsp");
			return;
		}

		chain.doFilter(req, res);
		return;
	}

	// @see javax.servlet.Filterinit(javax.servlet.FilterConfig)
	public void init(FilterConfig paramConfig) throws ServletException {
	}

}
