package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Admin;
import model.Notice;
import model.Student;
import model.Teacher;
import service.AdminService;
import service.AdminServiceImpl;
import service.NoticeService;
import service.NoticeServiceImpl;
import service.StudentService;
import service.StudentServiceImpl;
import service.TeacherService;
import service.TeacherServiceImpl;

/**
 * @Decription 处理登录业务
 * @authorEmail 1076030424@qq.com
 */
@WebServlet(urlPatterns = { "*.lg" })
public class LoginController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminServiceImpl();
	private StudentService studentdService = new StudentServiceImpl();
	private TeacherService teacherService = new TeacherServiceImpl();
	private NoticeService noticeService = new NoticeServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * 
	 * @Decription 按登录请求参数,做出判断、session内变量设置,以及消息返回
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		List<Notice> notices = noticeService.queryAll();
		String parameter = request.getParameter("userRole");
		Integer userRole = Integer.parseInt(parameter);
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		HttpSession session = request.getSession();
		if (userRole != null && userName != null && userPassword != null) {
			if (userRole == 3) {
				Admin admin = adminService.login(userName, userPassword);
				if (admin != null) {
					session.setAttribute("adminName", userName);
					session.setAttribute("userRole", "3");
					response.getWriter().print("ok");
					return;
				} else {
					response.getWriter().print("fail");
				}
			} else if (userRole == 1) {
				Teacher teacher = teacherService.login(userName, userPassword);
				if (teacher != null) {
					session.setAttribute("teacherName", userName);
					session.setAttribute("teacherId", teacher.getId());
					session.setAttribute("userRole", "1");
					session.setAttribute("notices", notices);
					response.getWriter().print("ok");
				} else {
					response.getWriter().print("fail");
				}
			} else if (userRole == 2) {
				Student student = studentdService.login(userName, userPassword);
				if (student != null) {
					session.setAttribute("studentName", userName);
					session.setAttribute("studentId", student.getId());
					session.setAttribute("userRole", "2");
					session.setAttribute("notices", notices);
					response.getWriter().print("ok");
				} else {
					response.getWriter().print("fail");
				}
			}
		} else {
			response.getWriter().print("fail");
		}
	}
}
