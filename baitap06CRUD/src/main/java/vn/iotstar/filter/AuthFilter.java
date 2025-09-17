package vn.iotstar.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User;

@WebFilter("/*")
public class AuthFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String path = request.getRequestURI().substring(request.getContextPath().length());

		// allow static resources
		if (path.startsWith("/resources/") || path.startsWith("/css") || path.startsWith("/js")
				|| path.startsWith("/images")|| path.startsWith("/uploads/")) {
			chain.doFilter(req, res);
			return;
		}

		// allow login page and login servlet
		if (path.equals("/login") || path.equals("/register") || path.equals("/")) {
			chain.doFilter(req, res);
			return;
		}

		HttpSession session = request.getSession(false);
		User user = (session != null) ? (User) session.getAttribute("user") : null;

		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		// restrict role->path
		if (path.startsWith("/user") && user.getRoleId() != 1) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		if (path.startsWith("/manager") && user.getRoleId() != 2) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		if (path.startsWith("/admin") && user.getRoleId() != 3) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		chain.doFilter(req, res);
	}
}
