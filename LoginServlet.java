package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.SecurityDAO;
import com.util.Bean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int  uid = 0;
		String uname = null;
		String mail = null;
		String utype = null;
		
		Bean b = new Bean();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if(email.equalsIgnoreCase("cloudprovider@gmail.com")&&password.equalsIgnoreCase("cloudprovider")) 
		{
				RequestDispatcher rd = request.getRequestDispatcher("cphome.jsp?status=Welcome Cloud Provider");
				rd.include(request, response);
		}
		else {
		b.setEmail(email);
		b.setPassword(password);
		try {
		ArrayList<Bean> al = new SecurityDAO().login(b);
		for(Bean value:al) 
		{
			uid =  value.getUid();
			mail = value.getEmail();
			uname = value.getUname();
			utype = value.getUtype();
		}
		if(!al.isEmpty()) 
		{
			if(utype.equals("Application Developer")) {
				HttpSession ses = request.getSession();
				ses.setAttribute("uid", uid);
				ses.setAttribute("mail", mail);
				ses.setAttribute("uname", uname);
			RequestDispatcher rd = request.getRequestDispatcher("applicationdeveloperhome.jsp?status=Welcome Developer "+uname);
			rd.include(request, response);
			}
			else if(utype.equals("Cloud User")) 
			{
				HttpSession ses = request.getSession();
				ses.setAttribute("uid", uid);
				ses.setAttribute("mail", mail);
				ses.setAttribute("uname", uname);
				RequestDispatcher rd = request.getRequestDispatcher("clouduserhome.jsp?status=Welcome Cloud User "+uname);
				rd.include(request, response);
			}
		}
		else 
		{
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp?status=Email and Password Not Matched");
			rd.include(request, response);
		}
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp?status=Some Internal Error");
			rd.include(request, response);
		}
		}
	}
}
