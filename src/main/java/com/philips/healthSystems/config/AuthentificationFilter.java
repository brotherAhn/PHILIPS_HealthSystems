package com.philips.healthSystems.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.philips.healthSystems.admin.domain.Login;
import com.philips.healthSystems.admin.service.PhilipsAdminService;
import com.philips.healthSystems.client.domain.confirmParam;
import com.philips.healthSystems.client.service.PhilipsClientService;

@Component
public class AuthentificationFilter implements Filter {
	@Autowired
	private PhilipsAdminService philipsAdminService;
	
	@Autowired
	private PhilipsClientService philipsClientService;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		ServletContext context = request.getServletContext();
		String uri = req.getRequestURI();
		/*
		 * if(uri.contains("/admin")) {//로그인 페이지에 접근시 기존 로그인 정보가 있으면 /admin_home 페이지로 이동
		 * Login auth = (Login) session.getAttribute("auth"); String authcode =
		 * philipsService.auth(auth); if(authcode== null) { chain.doFilter(req, res);
		 * }else if(authcode.equals(req.getSession().getId())) {
		 * res.sendRedirect("/admin_home"); }else { chain.doFilter(req, res);
		 * 
		 * } }
		 */
		if(uri.contains("/admin_sleepcoach") || uri.contains("/sign") ||  uri.contains("/admin_home") || uri.contains("/admin_prescription")  || uri.contains("/admin_private") || uri.contains("/admin_agree") || uri.contains("/admin_filter")  || uri.contains("/admin_install") || uri.contains("/upload") || uri.contains("/admin_return") || uri.contains("/admin_qna") || uri.contains("/admin_sleepcoach") || uri.contains("/admin_usergroup") || uri.contains("/admin_targetRegister") || uri.contains("/admin_groupInfo") || uri.contains("/admin_send") || uri.contains("/admin/user_result_view")) {
			Login auth = (Login) session.getAttribute("auth");
			if(auth == null) {
				// 로그인 페이지로 Redirect!!
				res.sendRedirect("/login");
				//context.getRequestDispatcher("/login").forward(request, response);
			} else {
				// 인증되면 진행
				String authcode = philipsAdminService.auth(auth);
				if(authcode== null) {
					res.sendRedirect("/login");
				}else if(authcode.equals(req.getSession().getId())) {
					chain.doFilter(req, res);
				}else {
					res.sendRedirect("/login");
				}
			}
			
		}else if(uri.contains("/co_agreeUser")) {
			confirmParam auth = (confirmParam) session.getAttribute("userAuth");
			if(auth == null) {
				// 로그인 페이지로 Redirect!!
				res.sendRedirect("/userAuth");
				//context.getRequestDispatcher("/login").forward(request, response);
			} else {
				// 인증되면 진행
				String authcode = philipsClientService.auth(auth);
				
				if(authcode == null) {
					res.sendRedirect("/userAuth");
				}else if(authcode.equals(req.getSession().getId()+"userAuth")) {
					chain.doFilter(req, res);
				}else {
					res.sendRedirect("/userAuth");
				}
			}
			
		} else {
			// 인증되면 진행
			chain.doFilter(req, res);
		}
		
		
	}
	
	@Override
	public void destroy() {
		
	}

	
}
