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

import model.TeachMaterial;
import service.TeachMaterialService;
import service.TeachMaterialServiceImpl;

/**
 * @Decription 教学材料信息相关处理
 * @authorEmail 1076030424@qq.com
 */
@WebServlet(urlPatterns = { "*.tm" })
public class TeachMaterialController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeachMaterialService teachMaterialService = new TeachMaterialServiceImpl();

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
		mn = mn.substring(0, mn.length() - 3).trim();
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
	protected void getPagerMaterials(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer pageChange = Integer.parseInt(request.getParameter("pageChange"));
		// session域里页面当前的页数
		Object materialPage = session.getAttribute("materialPage");
		Integer materialPg = null;
		if (materialPage == null) {
			materialPg = 1;
		} else {
			materialPg = (Integer) materialPage;
		}
		if (pageChange == 1) {
			materialPg += page;
			if (materialPg - 1 < 0) {
				return;
			}
		} else {
			materialPg = page;
			if (materialPg - 1 < 0) {
				return;
			}
		}
		Object materialNum = session.getAttribute("materialNum");
		Integer total = null;
		if (materialNum == null) {
			total = teachMaterialService.queryAll().size();
			session.setAttribute("materialNum", total);
		} else {
			total = (Integer) materialNum;
			if ((materialPg - 1) * 7 > total) {
				return;
			}
		}
		List<TeachMaterial> teachMaterial = teachMaterialService.queryPageTeachMaterials(7, materialPg);
		session.setAttribute("materialPage", materialPg);
		Gson gson = new Gson();
		String json = gson.toJson(teachMaterial); // 将Pricels转化为Json数组
		response.getWriter().print(json);
	}
	protected void getMaterialsNum(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response. getWriter().print(teachMaterialService.queryAll().size());
	}
	protected void deleteMaterial(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		TeachMaterial queryOne = teachMaterialService.queryOne(id);
		File file = new File(queryOne.getMaterrialPath());
		HttpSession session = request.getSession();
		List<TeachMaterial> queryAll = teachMaterialService.queryAll();
		if(id == queryAll.get(queryAll.size()-1).getId()) {
			Integer materialPage = (Integer)session.getAttribute("materialPage");
			if ((queryAll.size()-1) == ((materialPage-1)*7)) {
				session.setAttribute("materialPage", materialPage -= 1);
			}
		}
		int delete = teachMaterialService.delete(id);
		if (delete > 0) {
			file.delete();
			session.setAttribute("materialNum", (Integer)session.getAttribute("materialNum")-1);
			response.getWriter().print("ok");
		} else {
			response.getWriter().print("fail");

		}
	}
	protected void getMaterialContent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		TeachMaterial queryOne = teachMaterialService.queryOne(id);
		response.getWriter().print(queryOne.getMaterialContent());	
		}
	

	protected void downloadFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		TeachMaterial queryOne = teachMaterialService.queryOne(id);
		File f = new File(queryOne.getMaterrialPath());
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
		}
	}

	protected void addMaterial(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String savePath = "C:\\Users\\HP\\Desktop";

		TeachMaterial material = new TeachMaterial();
		material.setUploadDate(new Date());
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
						if ("materialHeadline".equals(name)) {
							material.setHeadline(obj);
						}
						if ("materialContent".equals(name)) {
							material.setMaterialContent(obj);
						}
					} else {

						File file = new File(savePath + "\\" + fileItem.getName() + new Date().getTime() + ".docx");
						material.setMaterialPath(file.getPath());
						material.setMaterialName(fileItem.getName());
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
				teachMaterialService.insert(material);
				HttpSession session = request.getSession();
				session.setAttribute("materialNum", (Integer)session.getAttribute("materialNum")+1);
				response.getWriter().print("ok");
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
