package vn.iotstar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.User;
import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String confirmPassword = req.getParameter("confirmPassword");

        // validate confirm
        if (!password.equals(confirmPassword)) {
            req.setAttribute("error", "Mật khẩu xác nhận không khớp!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        // validate username đã tồn tại
        if (userService.findByUsername(username) != null) {
            req.setAttribute("error", "Username đã tồn tại!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        User u = new User();
        u.setFullname(fullname);
        u.setUsername(username);
        u.setPassword(password);
        u.setRoleId(1); // mặc định role user

        try {
            userService.create(u);
            // đăng ký xong → quay lại login
            resp.sendRedirect(req.getContextPath() + "/login");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Đăng ký thất bại, vui lòng thử lại!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }

}
