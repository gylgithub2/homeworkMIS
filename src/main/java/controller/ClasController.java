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

import model.Clas;
import service.ClasService;
import service.ClasServiceImpl;

/**
 * @Decription 班级信息相关处理
 * @authorEmail 1076030424@qq.com
 */
@WebServlet(urlPatterns = { "*.cls" })
public class ClasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClasService clasService = new ClasServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 
	 * @Decription 业务分发
	 */
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
	 * @Decription 班级分页信息源以及分页控制
	 */
	protected void getPagerClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer pageChange = Integer.parseInt(request.getParameter("pageChange"));
		//session域里页面当前的页数
		Object classPage = session.getAttribute("classPage");
		Integer classPg = null;
		if (classPage == null) {
			classPg = 1;
		}else {			
			classPg = (Integer) classPage;
		}
		if (pageChange == 1) {
			classPg += page;
			if (classPg - 1 < 0) {
				return;
			}
		}else {
			classPg = page;
			if (classPg - 1 < 0) {
				return;
			}
		}
		Object classNum = session.getAttribute("classNum");
		Integer total = null;
		if (classNum == null) {
			total = clasService.queryAll().size();
			session.setAttribute("classNum", total);
		} else {
			total = (Integer) classNum;
			if ((classPg - 1) * 7 > total) {
				return;
			}
		}
		String dimText = request.getParameter("dimText");
		List<Clas> classes = clasService.queryPageClasses(7,classPg, dimText);
		session.setAttribute("classPage", classPg);
		Gson gson = new Gson();
		String json = gson.toJson(classes);
		response.getWriter().print(json);
	}
	protected void getClassNum(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response. getWriter().print(clasService.queryAll().size());
	}
	protected void addClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String className = request.getParameter("className");
		Clas clas = new Clas();
		clas.setClassName(className);
		int insert = clasService.insert(clas);
		if (insert > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("classNum", (Integer)session.getAttribute("classNum")+1);
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");

		}

	}

	protected void updateClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String className = request.getParameter("className");
		Integer id = Integer.parseInt(request.getParameter("id"));
		Clas clas = clasService.queryOne(id);
		clas.setClassName(className);
		int insert = clasService.update(clas);
		if (insert > 0) {

			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");

		}
	}

	protected void deleteClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		List<Clas> queryAll = clasService.queryAll();
		if(id == queryAll.get(queryAll.size()-1).getId()) {
			Integer classPage = (Integer)session.getAttribute("classPage");
			if ((queryAll.size()-1) <= ((classPage-1)*7)) {
				session.setAttribute("classPage", classPage -= 1);
			}
		}
		int delete = clasService.delete(id);
		if (delete > 0) {
			session.setAttribute("classNum", (Integer)session.getAttribute("classNum")-1);
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");

		}
	}

}
