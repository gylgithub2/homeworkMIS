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

import model.Student;

import service.ClasService;
import service.ClasServiceImpl;
import service.StudentService;
import service.StudentServiceImpl;

/**
 * @Decription 学生信息及用户相关处理
 * @authorEmail 1076030424@qq.com
 */
@WebServlet(urlPatterns = { "*.stu" })
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentService studentService = new StudentServiceImpl();
	private ClasService clasService = new ClasServiceImpl();

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
	protected void getPagerStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer pageChange = Integer.parseInt(request.getParameter("pageChange"));
		// session域里页面当前的页数
		Object studentPage = session.getAttribute("studentPage");
		Integer studentPg = null;
		if (studentPage == null) {
			studentPg = 1;
		} else {
			studentPg = (Integer) studentPage;
		}
		if (pageChange == 1) {
			studentPg += page;
			if (studentPg - 1 < 0) {
				return;
			}
		} else {
			studentPg = page;
			if (studentPg - 1 < 0) {
				return;
			}
		}
		Object studentNum = session.getAttribute("studentNum");
		Integer total = null;
		if (studentNum == null) {
			total = studentService.queryAll().size();
			session.setAttribute("studentNum", total);
		} else {
			total = (Integer) studentNum;
			if ((studentPg - 1) * 7 > total) {
				return;
			}
		}

		List<Student> students = studentService.queryPageStudents(7, studentPg, null);
		session.setAttribute("studentPage", studentPg);
		Gson gson = new Gson();
		String json = gson.toJson(students); 
		response.getWriter().print(json);
	}
	protected void getStudentNum(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response. getWriter().print(studentService.queryAll().size());
	}
	protected void addStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String studentName = request.getParameter("studentName");
		String studentPassword = request.getParameter("studentPassword");

		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer sex = Integer.parseInt(request.getParameter("sex"));
		if (sex != 1 && sex != 2) {
			response.getWriter().print("fail");
		}
		Integer age = Integer.parseInt(request.getParameter("age"));
		if (age < 0 || age > 120) {
			response.getWriter().print("fail");
		}
		Integer studentClass = Integer.parseInt(request.getParameter("studentClass"));
		if (clasService.queryOne(studentClass) == null) {
			response.getWriter().print("fail");
		}
		Student student = new Student();
		student.setStudentName(studentName);
		student.setId(id);
		student.setStudentPassword(studentPassword);
		student.setSex(sex);
		student.setAge(age);
		student.setStudentClass(studentClass);
		int insert = studentService.insert(student);
		if (insert > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("studentNum", (Integer) session.getAttribute("studentNum") + 1);
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");

		}
	}

	protected void updateStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String studentName = request.getParameter("studentName");
		String studentPassword = request.getParameter("studentPassword");

		Integer sex = Integer.parseInt(request.getParameter("sex"));
		if (sex != 1 && sex != 2) {
			response.getWriter().print("fail");
		}
		Integer age = Integer.parseInt(request.getParameter("age"));
		if (age < 0 || age > 120) {
			response.getWriter().print("fail");
		}
		Integer studentClass = Integer.parseInt(request.getParameter("studentClass"));
		if (clasService.queryOne(studentClass) == null) {
			response.getWriter().print("fail");
		}
		Integer id = Integer.parseInt(request.getParameter("id"));
		Student student = studentService.queryOne(id);
		student.setStudentName(studentName);
		student.setStudentPassword(studentPassword);
		student.setSex(sex);
		student.setAge(age);
		student.setStudentClass(studentClass);
		int insert = studentService.update(student);
		if (insert > 0) {
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");

		}
	}

	protected void deleteStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		List<Student> queryAll = studentService.queryAll();
		if(id == queryAll.get(queryAll.size()-1).getId()) {
			Integer studentPage = (Integer)session.getAttribute("studentPage");
			if ((queryAll.size()-1) == ((studentPage-1)*7)) {
				session.setAttribute("studentPage", studentPage -= 1);
			}
		}
	
		int delete = studentService.delete(id);
		if (delete > 0) {

			session.setAttribute("studentNum", (Integer) session.getAttribute("studentNum") - 1);
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");

		}
	}

	protected void getStudentInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Student student = studentService.queryOne(id);

		response.getWriter().print(student.getStudentName() + "(" + id + ")");

	}

}
