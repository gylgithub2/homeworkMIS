package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;

import model.HomeworkSet;
import service.HomeworkSetService;
import service.HomeworkSetServiceImpl;

/**
 * @Decription 作业设置及作业信息相关处理
 * @authorEmail 1076030424@qq.com
 */
@WebServlet(urlPatterns = { "*.hws" })
public class HomeworkSetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HomeworkSetService homeworkSetService = new HomeworkSetServiceImpl();

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

	protected void getPagerHomework(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//请求参数页数或页数变化量
		Integer page = Integer.parseInt(request.getParameter("page"));
		//判断page实际表示
		Integer pageChange = Integer.parseInt(request.getParameter("pageChange"));
		// session域里,当前显示页面的页数
		Object homeworkPage = session.getAttribute("homeworkPage");
		Integer homeworkPg = null;
		if (homeworkPage == null) {
			homeworkPg = 1;
		} else {
			homeworkPg = (Integer) homeworkPage;
		}
		if (pageChange == 1) {
			homeworkPg += page;
			if (homeworkPg - 1 < 0) {
				return;
			}
		} else {
			homeworkPg = page;
			if (homeworkPg - 1 < 0) {
				return;
			}
		}
		//记录总数
		Object homeworkNum = session.getAttribute("homeworkNum");
		Integer total = null;
		if (homeworkNum == null) {
			total = homeworkSetService.queryAll().size();
			session.setAttribute("homeworkNum", total);
		} else {
			total = (Integer) homeworkNum;
			if ((homeworkPg - 1) * 7 > total) {
				return;
			}
		}
		//分页查询
		List<HomeworkSet> homeworkSets = homeworkSetService.queryPageHomeworkSets(7, homeworkPg);
		//页数更新
		session.setAttribute("homeworkPage", homeworkPg);
		Gson gson = new Gson();
		String json = gson.toJson(homeworkSets); 
		response.getWriter().print(json);
	}
	/**
	 * 
	 * @Decription 获取记录总数
	 */
	protected void getHomeworkNum(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().print(homeworkSetService.queryAll().size());
	}
	/**
	 * 
	 * @Decription 文件下载
	 */
	protected void downloadFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		HomeworkSet homeworkSet = homeworkSetService.queryOne(id);
		File f = new File(homeworkSet.getHomeworkPath());
		if (f.exists()) {
			FileInputStream fis = new FileInputStream(f);
			String filename = URLEncoder.encode(f.getName(), "utf-8"); // 解决中文文件名下载后乱码的问题
			byte[] b = new byte[fis.available()];
			fis.read(b);
			response.setCharacterEncoding("utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + filename + "");
			// 获取响应报文输出流对象
			ServletOutputStream out = response.getOutputStream();
			// 输出
			out.write(b);
			out.flush();
			out.close();
			fis.close();
		}else{
			response.getWriter().print("fail");
		}
	}

	/**
	 * 
	 * @Decription 作业删除
	 */
	protected void deleteHomework(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		HomeworkSet queryOne = homeworkSetService.queryOne(id);
		File file = new File(queryOne.getHomeworkPath());
		HttpSession session = request.getSession();
		List<HomeworkSet> queryAll = homeworkSetService.queryAll();
		if(id == queryAll.get(queryAll.size()-1).getId()) {
			Integer homeworkPage = (Integer)session.getAttribute("homeworkPage");
			if ((queryAll.size()-1) <= ((homeworkPage-1)*7)) {
				session.setAttribute("homeworkPage", homeworkPage -= 1);
			}
		}
		int delete = homeworkSetService.delete(id);
		if (delete > 0) {
			file.delete();
			session.setAttribute("homeworkNum", (Integer) session.getAttribute("homeworkNum") - 1);
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");

		}
	}

	/**
	 * 
	 * @Decription 作业名字获取,用于作业提交页面显示
	 */
	protected void getHomeworkName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		HomeworkSet queryOne = homeworkSetService.queryOne(id);
		if (queryOne != null) {
			response.getWriter().print(queryOne.getHeadline());
		} else {
			response.getWriter().print("fail");
		}
	}

	/**
	 * 
	 * @Decription 作业新增
	 */
	protected void addHomework(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String savePath = "C:\\Users\\HP\\Desktop";
		HttpSession session = request.getSession();
		HomeworkSet homeworkSet = new HomeworkSet();
		homeworkSet.setSetDate(new Date());
		homeworkSet.setTeacher((Integer) session.getAttribute("teacherId"));
		if (!ServletFileUpload.isMultipartContent(request)) {
			return;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			List<FileItem> fileList = servletFileUpload.parseRequest(request);
			if (fileList != null && fileList.size() > 0) {
				for (FileItem fileItem : fileList) {
					if (fileItem.isFormField()) {
						String name = fileItem.getFieldName();
						String obj = fileItem.getString("UTF-8");
						if ("headline".equals(name)) {
							homeworkSet.setHeadline(obj);
						}
					} else {

						File file = new File(savePath + "\\" + +new Date().getTime() + fileItem.getName());
						homeworkSet.setHomeworkPath(file.getPath());
						homeworkSet.setHomeworkName(fileItem.getName());
						if (!file.exists()) {
							file.createNewFile();
						}
						outputStream = new FileOutputStream(file);
						inputStream = fileItem.getInputStream();
						byte[] b = new byte[1024];
						int len = 0;
						while ((len = inputStream.read(b)) > 0) {
							outputStream.write(b, 0, len);
						}

					}
				}
				int insert = homeworkSetService.insert(homeworkSet);
				if (insert > 0) {
					session.setAttribute("homeworkNum", (Integer) session.getAttribute("homeworkNum") + 1);
					response.getWriter().print("ok");
				} else {
					response.getWriter().print("fail");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

}
