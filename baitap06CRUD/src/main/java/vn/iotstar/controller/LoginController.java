package vn.iotstar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.entity.User;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private UserDao userDao = new UserDaoImpl();
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	    }

	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        String username = req.getParameter("username");
	        String password = req.getParameter("password");

	        User u = userDao.login(username, password);
	        if (u != null) {
	            HttpSession session = req.getSession();
	            session.setAttribute("user", u);
	            // redirect theo role
	            int role = u.getRoleId();
	            switch (role) {
	                case 1 -> resp.sendRedirect(req.getContextPath() + "/user/home");
	                case 2 -> resp.sendRedirect(req.getContextPath() + "/manager/home");
	                case 3 -> resp.sendRedirect(req.getContextPath() + "/admin/home");
	                default -> resp.sendRedirect(req.getContextPath() + "/login");
	            }
	        } else {
	            req.setAttribute("error", "Sai tài khoản hoặc mật khẩu");
	            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	        }
	    }
	}

