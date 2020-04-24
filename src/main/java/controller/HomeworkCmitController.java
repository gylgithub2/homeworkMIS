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

import model.HomeworkCmit;
import service.HomeworkCmitService;
import service.HomeworkCmitServiceImpl;

/**
 * @Decription 学生提交作业信息相关处理
 * @authorEmail 1076030424@qq.com
 */
@WebServlet(urlPatterns = { "*.hwc" })
public class HomeworkCmitController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HomeworkCmitService homeworkCmitService = new HomeworkCmitServiceImpl();

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
	 * @Decription 作业提交分页信息源以及分页控制
	 */
	protected void getPagerStuHomework(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object studentId = session.getAttribute("studentId");
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer pageChange = Integer.parseInt(request.getParameter("pageChange"));
		// session域里页面当前的页数
		Object stuHomeworkPgage = session.getAttribute("stuHomeworkPgPage");
		Integer stuHomeworkPg = null;
		if (stuHomeworkPgage == null) {
			stuHomeworkPg = 1;
		} else {
			stuHomeworkPg = (Integer) stuHomeworkPgage;
		}
		if (pageChange == 1) {
			stuHomeworkPg += page;
			if (stuHomeworkPg - 1 < 0) {
				return;
			}
		} else {
			stuHomeworkPg = page;
			if (stuHomeworkPg - 1 < 0) {
				return;
			}
		}
		Object stuHomeworkNum = session.getAttribute("stuHomeworkNum");
		Integer total = null;
		if (stuHomeworkNum == null) {
			if (studentId != null) {
				total = homeworkCmitService.queryStuSelfAll((Integer)studentId).size();
			} else {
				total = homeworkCmitService.queryAll().size();
			}
		
			session.setAttribute("stuHomeworkNum", total);
		} else {
			total = (Integer) stuHomeworkNum;
			if ((stuHomeworkPg - 1) * 7 > total) {
				return;
			}
		}
		List<HomeworkCmit> homeworkCmits =null;
		if (studentId != null) {
			 homeworkCmits = homeworkCmitService.queryPageStuHomeworkCmits(7, stuHomeworkPg,(Integer)studentId);
		} else {
			 homeworkCmits = homeworkCmitService.queryPageHomeworkCmits(7, stuHomeworkPg);
		}
		session.setAttribute("stuHomeworkPgage", stuHomeworkPg);
		Gson gson = new Gson();
		String json = gson.toJson(homeworkCmits); 
		response.getWriter().print(json);
	}
	/**
	 * 
	 * @Decription 班级信息总数
	 */
	protected void getMyHomeworkNum(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().print(homeworkCmitService.queryAll().size());
	}
	/**
	 * 
	 * @Decription 作业文件下载
	 */
	protected void downloadFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		HomeworkCmit homeworkCmit = homeworkCmitService.queryOne(id);
		File f = new File(homeworkCmit.getFilePath());
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
		} else {
			response.getWriter().print("fail");
		}
	}

	/**
	 * 
	 * @Decription 删除作业
	 */
	protected void deleteHomeworkCmit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		HttpSession session = request.getSession();
		List<HomeworkCmit> queryAll = homeworkCmitService.queryAll();
		if(id == queryAll.get(queryAll.size()-1).getId()) {
			Integer stuHomeworkPgPage = (Integer)session.getAttribute("stuHomeworkPgPage");
			if ((queryAll.size()-1) <= ((stuHomeworkPgPage-1)*7)) {
				session.setAttribute("stuHomeworkPgPage", stuHomeworkPgPage -= 1);
			}
		}
		int delete = homeworkCmitService.delete(id);
		if (delete > 0) {
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");

		}
	}

	/**
	 * 
	 * @Decription 新增批阅信息
	 */
	protected void addHomeworkRead(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("stuHomeworkId"));
		HttpSession session = request.getSession();
		Integer teacherId = (Integer) session.getAttribute("teacherId");
		String readMSG = request.getParameter("readMSG");

		HomeworkCmit queryOne = homeworkCmitService.queryOne(id);
		queryOne.setReadDate(new Date());
		queryOne.setReadAdvice(readMSG);
		queryOne.setTeacher(teacherId);
		int insert = homeworkCmitService.update(queryOne);
		if (insert > 0) {
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");

		}
	}

	/**
	 * 
	 * @Decription 上传作业
	 */
	protected void uploadHomework(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String savePath = "C:\\Users\\HP\\Desktop";
		HttpSession session = request.getSession();
		HomeworkCmit homeworkCmit = new HomeworkCmit();
		homeworkCmit.setUploadDate(new Date());
		homeworkCmit.setStudent((Integer) session.getAttribute("studentId"));
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
						if ("homeworkHeadline".equals(name)) {
							homeworkCmit.setHomeworkName(obj);
						}
					} else {

						File file = new File(savePath + "\\" + +new Date().getTime() + fileItem.getName());
						homeworkCmit.setFilePath(file.getPath());
						homeworkCmit.setFileName(fileItem.getName());
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
				int insert = homeworkCmitService.insert(homeworkCmit);
				if (insert > 0) {
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
