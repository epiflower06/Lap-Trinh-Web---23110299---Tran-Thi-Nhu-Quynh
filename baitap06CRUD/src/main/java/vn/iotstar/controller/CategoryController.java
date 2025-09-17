package vn.iotstar.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.CategoryServiceImpl;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet({ "/category", "/category/create", "/category/save", "/category/edit", "/category/update",
		"/category/delete" })
@MultipartConfig(   // upload file
        fileSizeThreshold = 1024 * 1024, 
        maxFileSize = 1024 * 1024 * 10,  
        maxRequestSize = 1024 * 1024 * 50 
)
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CategoryService cateService = new CategoryServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		User u = (User) session.getAttribute("user");

		if (path.equals("/category")) {
			List<Category> list = (u.getRoleId() == 2) ? cateService.findByUser(u.getUserId()) : cateService.findAll();
			req.setAttribute("categories", list);
			req.getRequestDispatcher("/views/category/list.jsp").forward(req, resp);
			return;
		}

		if (path.equals("/category/create")) {
			req.setAttribute("action", "create");
			req.getRequestDispatcher("/views/category/form.jsp").forward(req, resp);
			return;
		}

		if (path.equals("/category/edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Category c = cateService.findById(id);
//			if (c == null || (c.getUser() != null && c.getUser().getUserId() != u.getUserId() && u.getRoleId() != 3)) {
//				resp.sendRedirect(req.getContextPath() + "/category");
//				return;
//			}
			req.setAttribute("category", c);
			req.setAttribute("action", "edit");
			if (u.getRoleId() == 3) {
		        UserService userService = new UserServiceImpl();
		        req.setAttribute("users", userService.findAll());
		    }
			req.getRequestDispatcher("/views/category/form.jsp").forward(req, resp);
			return;
		}

		resp.sendRedirect(req.getContextPath() + "/category");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String path = req.getServletPath();
	    HttpSession session = req.getSession(false);
	    if (session == null || session.getAttribute("user") == null) {
	        resp.sendRedirect(req.getContextPath() + "/login");
	        return;
	    }
	    User u = (User) session.getAttribute("user");

	    // Lấy thư mục upload (webapp/uploads)
	    String uploadPath = getServletContext().getRealPath("/") + "uploads";
	    java.io.File uploadDir = new java.io.File(uploadPath);
	    if (!uploadDir.exists()) uploadDir.mkdir();

	    if (path.equals("/category/save")) {
	        String name = req.getParameter("categoryname");

	        // lấy file upload
	        jakarta.servlet.http.Part filePart = req.getPart("images");
	        String fileName = (filePart != null) ? java.nio.file.Paths.get(filePart.getSubmittedFileName()).getFileName().toString() : null;
	        String filePath = null;
	        if (fileName != null && !fileName.isEmpty()) {
	            filePath = "uploads/" + System.currentTimeMillis() + "_" + fileName;
	            filePart.write(getServletContext().getRealPath("/") + filePath);
	        }

	        Category c = new Category();
	        c.setCategoryname(name);
	        c.setImages(filePath); // lưu path tương đối để hiển thị bằng <img src="">
	        c.setStatus(true);
	        c.setUser(u);
	        cateService.create(c);

	    } else if (path.equals("/category/update")) {
	        String idStr = req.getParameter("id");
	        if (idStr != null && !idStr.isEmpty()) {
	            int id = Integer.parseInt(idStr);
	            Category c = cateService.findById(id);

	            if (c != null && (c.getUser().getUserId() == u.getUserId() || u.getRoleId() == 3)) {
	                c.setCategoryname(req.getParameter("categoryname"));

	                // lấy file upload mới
	                jakarta.servlet.http.Part filePart = req.getPart("images");
	                String fileName = (filePart != null) ? java.nio.file.Paths.get(filePart.getSubmittedFileName()).getFileName().toString() : null;
	                if (fileName != null && !fileName.isEmpty()) {
	                    String filePath = "uploads/" + System.currentTimeMillis() + "_" + fileName;
	                    filePart.write(getServletContext().getRealPath("/") + filePath);
	                    c.setImages(filePath); // ghi đè ảnh mới
	                }
	                // nếu không upload ảnh mới -> giữ ảnh cũ

	                // set status
	                String statusStr = req.getParameter("status");
	                if (statusStr != null) {
	                    c.setStatus(Boolean.parseBoolean(statusStr));
	                }

	                // nếu là admin thì có thể đổi owner
	                if (u.getRoleId() == 3) {
	                    String ownerId = req.getParameter("userId");
	                    if (ownerId != null && !ownerId.isEmpty()) {
	                        UserService userService = new UserServiceImpl();
	                        User newOwner = userService.findById(Integer.parseInt(ownerId));
	                        c.setUser(newOwner);
	                    }
	                }

	                cateService.update(c);
	            }
	        }

	    } else if (path.equals("/category/delete")) {
	        int id = Integer.parseInt(req.getParameter("id"));
	        Category c = cateService.findById(id);
	        if (c != null && (c.getUser() == null || c.getUser().getUserId() == u.getUserId() || u.getRoleId() == 3)) {
	            try {
	                cateService.delete(id);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    resp.sendRedirect(req.getContextPath() + "/" + rolePath(u.getRoleId()) + "/home");
	}


	private String rolePath(int roleid) {
		return (roleid == 1) ? "user" : (roleid == 2) ? "manager" : "admin";
	}
}
