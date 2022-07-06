package com.theone.springboot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();

		if (uri.contains("dashboard")) {
			String loginUser = (String) request.getSession().getAttribute("loginUser");
			if (null == loginUser) {
				response.sendRedirect(request.getContextPath() + "/login");
				return false;
			}
			return true;
		}
		return true;
	}

}