package com.sp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sp.dao.Tb_managerDao;
import com.sp.entity.Tb_manager;
import com.sp.services.Tb_managerServices;

public class Tb_managerServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String f = request.getParameter("f");
		if(f.equals("login")){  //登录
			login(request,response);
		}
		else if(f.equals("select")){
		//	Selectmanager(request,response);
			
		}
		else if(f.equals("exit")){  //退出
			System.out.println("exit");
			session.removeAttribute("manager"); // 清除session中的manager
			response.sendRedirect("Login.jsp");
			//out.print("<script>window.location.href='Login.jsp';</script>");
		}
		
		out.flush();
		out.close();
	}
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String manName = request.getParameter("txtManName"); //获取登录名
		String pwd = request.getParameter("txtPwd");  //密码
		//调用services层的登录方法
		Tb_managerServices services = new Tb_managerServices();
		Tb_manager manager = services.Login(manName, pwd);
		if(manager!=null){
			//out.print("<script>alert('登录成功');</script>");
			//登录成功，将用户信息放入到session中
			session.setAttribute("manager", manager);
			response.sendRedirect("index1.jsp");
		}
		else{
			out.print("<script>alert('失败');</script>");
		}
	}
	
	
	
//	public void Selectmanager(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		Tb_managerDao dao=new Tb_managerDao();
//		List<Tb_manager> lstNews = dao.getmanager();//调用查询语句，得到集合对象
//		request.setAttribute("lstNews", lstNews); //将集合对象放入到作用域中
//		request.getRequestDispatcher("Manager.jsp").forward(request, response);		
//		
//	
//	
//		
//	}
}
