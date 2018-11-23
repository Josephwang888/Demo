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
		if(f.equals("login")){  //��¼
			login(request,response);
		}
		else if(f.equals("select")){
		//	Selectmanager(request,response);
			
		}
		else if(f.equals("exit")){  //�˳�
			System.out.println("exit");
			session.removeAttribute("manager"); // ���session�е�manager
			response.sendRedirect("Login.jsp");
			//out.print("<script>window.location.href='Login.jsp';</script>");
		}
		
		out.flush();
		out.close();
	}
	/**
	 * ��¼
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String manName = request.getParameter("txtManName"); //��ȡ��¼��
		String pwd = request.getParameter("txtPwd");  //����
		//����services��ĵ�¼����
		Tb_managerServices services = new Tb_managerServices();
		Tb_manager manager = services.Login(manName, pwd);
		if(manager!=null){
			//out.print("<script>alert('��¼�ɹ�');</script>");
			//��¼�ɹ������û���Ϣ���뵽session��
			session.setAttribute("manager", manager);
			response.sendRedirect("index1.jsp");
		}
		else{
			out.print("<script>alert('ʧ��');</script>");
		}
	}
	
	
	
//	public void Selectmanager(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		Tb_managerDao dao=new Tb_managerDao();
//		List<Tb_manager> lstNews = dao.getmanager();//���ò�ѯ��䣬�õ����϶���
//		request.setAttribute("lstNews", lstNews); //�����϶�����뵽��������
//		request.getRequestDispatcher("Manager.jsp").forward(request, response);		
//		
//	
//	
//		
//	}
}
