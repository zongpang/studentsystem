package com.bc.stdsys.webservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bc.stdsys.entitys.ClassWorker;
import com.bc.stdsys.entitys.Deanery;
import com.bc.stdsys.entitys.Master;
import com.bc.stdsys.entitys.Teacher;
import com.bc.stdsys.util.DButil;

public class MyServlet extends HttpServlet {
	HttpSession session;
	Object obj;
	int i = 0;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		i++;
		System.out.println("myServlet:" + i);
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		session = request.getSession();
		obj = session.getAttribute("user");
		String userName = request.getParameter("username");// 姓名
		String passWord = request.getParameter("passWord");// 密码
		String userId = request.getParameter("user");// 用户身份
		if (obj == null) {
			try {
				obj = DButil.login(userId, userName, passWord);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (obj != null && obj instanceof Teacher) {// 登陆成功转发查询处理
			String type = request.getParameter("quit");
			if (type != null && type.equals("q")) {
				session.invalidate();
				response.sendRedirect("login/Login.jsp");
			} else {
				session.setAttribute("user", obj);
				session.setAttribute("loginFirst", true);
				request.getRequestDispatcher("find").forward(request, response);
			}

		} else if (obj != null && obj instanceof ClassWorker) {
			String type = request.getParameter("quit");
			if (type != null && type.equals("q")) {
				session.invalidate();
				response.sendRedirect("login/Login.jsp");
			} else {
				session.setAttribute("user", obj);
				session.setAttribute("loginFirst", true);
				request.getRequestDispatcher("find").forward(request, response);
			}

		} else if (obj != null && obj instanceof Master) {
			String type = request.getParameter("quit");
			if (type != null && type.equals("q")) {
				session.invalidate();
				response.sendRedirect("login/Login.jsp");
			} else {
				session.setAttribute("user", obj);
				session.setAttribute("loginFirst", true);
				request.getRequestDispatcher("find").forward(request, response);
			}

		} else if (obj != null && obj instanceof Deanery) {
			String type = request.getParameter("quit");
			if (type != null && type.equals("q")) {
				session.invalidate();
				response.sendRedirect("login/Login.jsp");
			} else {
				session.setAttribute("user", obj);
				session.setAttribute("loginFirst", true);
				request.getRequestDispatcher("find").forward(request, response);
			}

		} else {// 重定向到登陆界面
			response.sendRedirect("login/Login.jsp");
		}
	}
}