package vn.iotstar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession s = req.getSession(false);
		if (s != null)
			s.invalidate();
		resp.sendRedirect(req.getContextPath() + "/login");
	}
}
