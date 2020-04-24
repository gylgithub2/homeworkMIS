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

import model.Notice;
import service.NoticeService;
import service.NoticeServiceImpl;

/**
 * @Decription 公告信息相关业务处理
 * @authorEmail 1076030424@qq.com
 */
@WebServlet(urlPatterns = { "*.ntc" })
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeService noticeService = new NoticeServiceImpl();

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
	 * @Decription 获取公告分页数据源
	 */
	protected void getPagerNotice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer pageChange = Integer.parseInt(request.getParameter("pageChange"));
		// session域里页面当前的页数
		Object noticePage = session.getAttribute("noticePage");
		Integer noticePg = null;
		if (noticePage == null) {
			noticePg = 1;
		} else {
			noticePg = (Integer) noticePage;
		}
		if (pageChange == 1) {
			noticePg += page;
			if (noticePg - 1 < 0) {
				return;
			}
		} else {
			noticePg = page;
			if (noticePg - 1 < 0) {
				return;
			}
		}
		Object noticeNum = session.getAttribute("noticeNum");
		Integer total = null;
		if (noticeNum == null) {
			total = noticeService.queryAll().size();
			session.setAttribute("noticeNum", total);
		} else {
			total = (Integer) noticeNum;
			if ((noticePg - 1) * 7 > total) {
				return;
			}
		}
		List<Notice> notices = noticeService.queryPageNotices(7, noticePg);
		session.setAttribute("noticePage", noticePg);
		Gson gson = new Gson();
		String json = gson.toJson(notices); 
		response.getWriter().print(json);
	}
	/**
	 * 
	 * @Decription 获取总条数
	 */
	protected void getNoticeNum(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response. getWriter().print(noticeService.queryAll().size());
	}
	
	/**
	 * 
	 * @Decription 删除公告
	 */
	protected void deleteNotice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		List<Notice> queryAll = noticeService.queryAll();
		if(id == queryAll.get(queryAll.size()-1).getId()) {
			Integer noticePage = (Integer)session.getAttribute("noticePage");
			if ((queryAll.size()-1) <= ((noticePage-1)*7)) {
				session.setAttribute("noticePage", noticePage -= 1);
			}
		}
		int delete = noticeService.delete(id);
		if (delete > 0) {
			session.setAttribute("noticeNum", (Integer)session.getAttribute("noticeNum")-1);
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");

		}
	}
	/**
	 * 
	 * @Decription 获取公告内容
	 */
	protected void getNoticeContent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Notice queryOne = noticeService.queryOne(id);
		response.getWriter().print(queryOne.getNoticeContent());
	}
	/**
	 * 
	 * @Decription 获取所有公告
	 */
	protected void setNotice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Notice> notices = noticeService.queryAll();
		session.setAttribute("notices", notices);
	}
	/**
	 * 
	 * @Decription 增加公告
	 */
	protected void addNotice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String noticeHeadline = request.getParameter("noticeHeadline");
		String noticeContent = request.getParameter("noticeContent");
		Notice notice = new Notice();
		notice.setHeadline(noticeHeadline);
		notice.setUploadDate(new Date());
		notice.setNoticeContent(noticeContent);
		int add = noticeService.insert(notice);
		if (add > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("noticeNum", (Integer)session.getAttribute("noticeNum")+1);
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");

		}
	}

}
