package webutils;

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
 * @Decription 过滤器,用于权限控制
 * @authorEmail 1076030424@qq.com
 */

public class PageListenner implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		Object userRole = session.getAttribute("userRole");
		String path = request.getServletPath();
		if (userRole == null) {
			response.sendRedirect("index.jsp");
		} else if (Integer.parseInt(userRole+"") == 2 && !path.contains("/student.jsp") && !path.contains("myhomework-manager.jsp")) {
			response.sendRedirect("index.jsp");
		} else if (Integer.parseInt(userRole+"") == 1 && !path.contains("/teacher.jsp") && !path.contains("stuHomework-manager.jsp")
				&& !path.contains("materials-manager.jsp")) {
			response.sendRedirect("index.jsp");
		} else {

			filterChain.doFilter(servletRequest, servletResponse);

		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
