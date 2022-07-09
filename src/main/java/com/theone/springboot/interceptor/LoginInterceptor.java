package com.theone.springboot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.theone.springboot.entity.Company;
import com.theone.springboot.entity.Member;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();

		if (uri.contains("dashboard")) {
			String loginUser = (String) request.getSession().getAttribute("loginUser");
			if (!"admin".equals(loginUser)) {
				response.sendRedirect(request.getContextPath() + "/login");
				return false;
			}
			return true;
		}

		if (uri.contains("enterprise")) {
			Company loginEnterprise = (Company) request.getSession().getAttribute("loginEnterprise");
			if (loginEnterprise == null) {
				response.sendRedirect(request.getContextPath() + "/login");
				return false;
			}
			return true;
		}
		
		////////////////////////////////////////
//		if (uri.contains("user")) {
//			Member loginMember = (Member) request.getSession().getAttribute("loginUser");
//			if (loginMember == null) {
//				response.sendRedirect(request.getContextPath() + "/login");
//				return false;
//			}
//			return true;
//		}
		return true;
	}

}
