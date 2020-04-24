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

import model.Teacher;
import service.TeacherService;
import service.TeacherServiceImpl;

/**
 * @Decription 教师信息及用户相关处理
 * @authorEmail 1076030424@qq.com
 */
@WebServlet(urlPatterns = { "*.tea" })
public class TeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherService teacherService = new TeacherServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	// 业务分发
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String mn = request.getServletPath();
		mn = mn.substring(1);
		mn = mn.substring(0, mn.length() - 4).trim();
		try {
			Method declaredMethod = this.getClass().getDeclaredMethod(mn, HttpServletRequest.class,
					HttpServletResponse.class);
			declaredMethod.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @Decription 分页信息源以及分页控制
	 */
	protected void getPagerTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer pageChange = Integer.parseInt(request.getParameter("pageChange"));
		// session域里页面当前的页数
		Object teacherPage = session.getAttribute("teacherPage");
		Integer teacherPg = null;
		if (teacherPage == null) {
			teacherPg = 1;
		} else {
			teacherPg = (Integer) teacherPage;
		}
		if (pageChange == 1) {
			teacherPg += page;
			if (teacherPg - 1 < 0) {
				return;
			}
		} else {
			teacherPg = page;
			if (teacherPg - 1 < 0) {
				return;
			}
		}
		Object teacherNum = session.getAttribute("teacherNum");
		Integer total = null;
		if (teacherNum == null) {
			total = teacherService.queryAll().size();
			session.setAttribute("teacherNum", total);
		} else {
			total = (Integer) teacherNum;
			if ((teacherPg - 1) * 7 > total) {
				return;
			}
		}
		List<Teacher> teachers = teacherService.queryPageTeachers(7, teacherPg, null);
		session.setAttribute("teacherPage", teacherPg);
		Gson gson = new Gson();
		String json = gson.toJson(teachers); // 将Pricels转化为Json数组
		response.getWriter().print(json);
	}

	protected void getTeacherNum(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response. getWriter().print(teacherService.queryAll().size());
	}

	protected void addTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String TeacherName = request.getParameter("name");
		String TeacherPassword = request.getParameter("psword");
		Integer id = Integer.parseInt(request.getParameter("id"));

		Integer sex = Integer.parseInt(request.getParameter("sex"));
		if (sex != 1 && sex != 2) {
			response.getWriter().print("fail");
		}
		Integer age = Integer.parseInt(request.getParameter("age"));
		if (age < 0 || age > 120) {
			response.getWriter().print("fail");
		}
		Teacher teacher = new Teacher();
		teacher.setId(id);
		teacher.setTeacherName(TeacherName);
		teacher.setTeacherPassword(TeacherPassword);
		teacher.setSex(sex);
		teacher.setAge(age);
		int insert = teacherService.insert(teacher);
		if (insert > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("teacherNum", (Integer) session.getAttribute("teacherNum") + 1);
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");

		}
	}

	protected void updateTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String TeacherName = request.getParameter("teacherName");
		String TeacherPassword = request.getParameter("teacherPassword");

		Integer sex = Integer.parseInt(request.getParameter("sex"));
		if (sex != 1 && sex != 2) {
			response.getWriter().print("fail");
		}
		Integer age = Integer.parseInt(request.getParameter("age"));
		if (age < 0 || age > 120) {
			response.getWriter().print("fail");
		}
		Integer id = Integer.parseInt(request.getParameter("id"));
		Teacher Teacher = teacherService.queryOne(id);
		Teacher.setTeacherName(TeacherName);
		Teacher.setTeacherPassword(TeacherPassword);
		Teacher.setSex(sex);
		Teacher.setAge(age);
		int insert = teacherService.update(Teacher);
		if (insert > 0) {
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");

		}
	}

	protected void deleteTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		List<Teacher> queryAll = teacherService.queryAll();
		if(id == queryAll.get(queryAll.size()-1).getId()) {
			Integer teacherPage = (Integer)session.getAttribute("teacherPage");
			if ((queryAll.size()-1) == ((teacherPage-1)*7)) {
				session.setAttribute("teacherPage", teacherPage -= 1);
			}
		}
		int delete = teacherService.delete(id);
		if (delete > 0) {
			session.setAttribute("teacherNum", (Integer) session.getAttribute("teacherNum") - 1);
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");

		}
	}

	protected void getTeacherName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Teacher teacher = teacherService.queryOne(id);
		response.getWriter().print(teacher.getTeacherName());

	}

}
