package vn.iotstar.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;

@WebServlet({"/user/home", "/manager/home", "/admin/home"})
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService cateService = new CategoryServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) { resp.sendRedirect(req.getContextPath() + "/login"); return; }
        User u = (User) session.getAttribute("user");
        if (u == null) { resp.sendRedirect(req.getContextPath() + "/login"); return; }

        String view;
        switch (u.getRoleId()) {
            case 1 -> { // user
                req.setAttribute("categories", cateService.findAll());
                view = "/views/user/home.jsp";
            }
            case 2 -> { // manager: group theo user
                List<Category> all = cateService.findAll();
                Map<User, List<Category>> grouped = all.stream()
                        .collect(Collectors.groupingBy(Category::getUser));
                req.setAttribute("groupedCategories", grouped);
                view = "/views/manager/home.jsp";
            }
            case 3 -> { // admin
                req.setAttribute("categories", cateService.findAll());
                view = "/views/admin/home.jsp";
            }
            default -> view = "/views/user/home.jsp";
        }
        req.getRequestDispatcher(view).forward(req, resp);
    }
}
