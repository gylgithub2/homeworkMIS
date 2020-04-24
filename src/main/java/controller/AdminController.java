package controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Admin;
import service.AdminService;
import service.AdminServiceImpl;

/**
 * @Decription 管理员信息相关处理
 * @authorEmail 1076030424@qq.com
 */
@WebServlet(urlPatterns = { "*.admin" })
public class AdminController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * 
	 * @Decription 业务分发
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String mn = req.getServletPath();
		mn = mn.substring(1);
		mn = mn.substring(0, mn.length() - 6).trim();
		try {
			Method declaredMethod = this.getClass().getDeclaredMethod(mn, HttpServletRequest.class,
					HttpServletResponse.class);
			declaredMethod.invoke(this, req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Decription 获取用户分页数据源以及分页控制
	 */
	protected void getPagerAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer pageChange = Integer.parseInt(request.getParameter("pageChange"));
		//session域里页面当前的页数
		Object adminPage = session.getAttribute("adminPage");
		Integer adminPg = null;
		if (adminPage == null) {
			adminPg = 1;
		}else {			
			adminPg = (Integer) adminPage;
		}
		if (pageChange == 1) {
			adminPg += page;
			if (adminPg - 1 < 0) {
				return;
			}
		}else {
			adminPg = page;
			if (adminPg- 1 < 0) {
				return;
			}
		}
		Object adminNum = session.getAttribute("adminNum");
		Integer total = null;
		if (adminNum == null) {
			total = adminService.queryAll().size();
			session.setAttribute("adminNum", total);
		} else {
			total = (Integer) adminNum;
			if ((adminPg - 1) * 7 > total) {
				return;
			}
		}
		List<Admin> admins = adminService.queryPageAdmins(7,adminPg, null);
		session.setAttribute("adminPage", adminPg);
		Gson gson = new Gson();
		String json = gson.toJson(admins); 
		response.getWriter().print(json);
	}
	/**
	 * 
	 * @Decription 获取用户总数
	 */
	protected void getAdminNum(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().print(adminService.queryAll().size());
	}
	
	/**
	 * 
	 * @Decription 更新用户密码
	 */
	protected void updatePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oldPassword = request.getParameter("oldPassword").trim();
		String newPassword = request.getParameter("newPassword").trim();
		HttpSession session = request.getSession();
		String adminName = (String) session.getAttribute("adminName");
		Admin admin = adminService.login(adminName, oldPassword);
		if (admin != null) {
			admin.setAdminPassword(newPassword);
			adminService.update(admin);
			response.getWriter().print("ok");
			
		} else {
			response.getWriter().print("fail");

		}

	}
	/**
	 * 
	 * @Decription 新增用户
	 */
	protected void addAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminName = request.getParameter("name");
		String adminPassword = request.getParameter("psword");
		Admin admin = new Admin();
		admin.setAdminName(adminName);
		admin.setAdminPassword(adminPassword);
		int insert = adminService.insert(admin);
		if (insert > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("adminNum", (Integer)session.getAttribute("adminNum")+1);
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");

		}
	}

	/**
	 * 
	 * @Decription 更新用户
	 */
	protected void updateAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminName = request.getParameter("adminName");
		String adminPassword = request.getParameter("adminPassword");
		Integer id = Integer.parseInt(request.getParameter("id"));
		Admin admin = adminService.queryOne(id);
		admin.setAdminName(adminName);
		admin.setAdminPassword(adminPassword);
		int insert = adminService.update(admin);
		if (insert > 0) {
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");

		}
	}

	/**
	 * 
	 * @Decription 用户删除,以及用户界面控制
	 */
	protected void deleteAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		 List<Admin> queryAll = adminService.queryAll();
		if(id ==queryAll.get(queryAll.size()-1).getId()) {
			Integer adminPage = (Integer)session.getAttribute("adminPage");
			if ((queryAll.size()-1) <= ((adminPage-1)*7)) {
				session.setAttribute("adminPage", adminPage -= 1);
			}
		}
		int delete = adminService.delete(id);
		if (delete > 0) {		
			session.setAttribute("adminNum", (Integer)session.getAttribute("adminNum")-1);
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");

		}
	}

}
