package controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.LeaveMSG;
import service.LeaveMSGService;
import service.LeaveMSGServiceImpl;

/**
 * @Decription 留言信息相关处理
 * @authorEmail 1076030424@qq.com
 */
@WebServlet(urlPatterns = { "*.lmsg" })
public class LeaveMSGController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LeaveMSGService leaveMSGService = new LeaveMSGServiceImpl();

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
		mn = mn.substring(0, mn.length() - 5).trim();
		try {
			Method declaredMethod = this.getClass().getDeclaredMethod(mn, HttpServletRequest.class,
					HttpServletResponse.class);
			declaredMethod.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void getPagerLeaveMSG(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer pageChange = Integer.parseInt(request.getParameter("pageChange"));
		// session域里页面当前的页数
		Object leaveMSGPage = session.getAttribute("leaveMSGPage");
		Integer leaveMSGPg = null;
		if (leaveMSGPage == null) {
			leaveMSGPg = 1;
		} else {
			leaveMSGPg = (Integer) leaveMSGPage;
		}
		if (pageChange == 1) {
			leaveMSGPg += page;
			if (leaveMSGPg - 1 < 0) {
				return;
			}
		} else {
			leaveMSGPg = page;
			if (leaveMSGPg - 1 < 0) {
				return;
			}
		}
		Object leaveMSGNum = session.getAttribute("leaveMSGNum");
		Integer total = null;
		if (leaveMSGNum == null) {
			total = leaveMSGService.queryAll().size();
			session.setAttribute("leaveMSGNum", total);
		} else {
			total = (Integer) leaveMSGNum;
			if ((leaveMSGPg - 1) * 3 > total) {
				return;
			}
		}
		List<LeaveMSG> leaveMSGs = leaveMSGService.queryPageLeaveMSGs(3, leaveMSGPg);
		session.setAttribute("leaveMSGPage", leaveMSGPg);
		Gson gson = new Gson();
		String json = gson.toJson(leaveMSGs); 
		response.getWriter().print(json);
	}
	protected void getLeaveMSGNum(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response. getWriter().print(leaveMSGService.queryAll().size());
	}
	protected void deleteLeaveMSG(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		List<LeaveMSG> queryAll = leaveMSGService.queryAll();
		if(id == queryAll.get(queryAll.size()-1).getId()) {
			Integer leaveMSGPage = (Integer)session.getAttribute("leaveMSGPage");
			if ((queryAll.size()-1) <= ((leaveMSGPage-1)*3)) {
				session.setAttribute("leaveMSGPage", leaveMSGPage -= 1);
			}
		}
		int delete = leaveMSGService.delete(id);
		if (delete > 0) {
			session.setAttribute("leaveMSGNum", (Integer) session.getAttribute("leaveMSGNum") - 1);
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");

		}
	}

	protected void addLeaveMSG(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 留言人 todo...
		 */
		LeaveMSG msg = new LeaveMSG();
		HttpSession session = request.getSession();
		Object teacherName = session.getAttribute("teacherName");
		Object studentName = session.getAttribute("studentName");
		if (studentName != null) {
			msg.setUser((String) studentName);
		} else {
			msg.setUser((String) teacherName);
		}
		String headline = request.getParameter("lmsgHeadline");
		String content = request.getParameter("lmsgContent");
		msg.setUploadDate(new Date());
		msg.setHeadline(headline);
		msg.setMsgContent(content);
		int insert = leaveMSGService.insert(msg);
		if (insert > 0) {
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");

		}
	}

}
