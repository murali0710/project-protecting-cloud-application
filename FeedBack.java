package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.SecurityDAO;
import com.util.Bean;

/**
 * Servlet implementation class FeedBack
 */
@WebServlet("/FeedBack")
public class FeedBack extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				Bean b = new Bean();
				b.setEmail(request.getParameter("email"));
				b.setMobile(request.getParameter("fname"));
				b.setPassword(request.getParameter("lname"));
				b.setUname(request.getParameter("message"));
				try {
				int i = new SecurityDAO().feedback(b);
				if(i!=0) 
				{
					response.sendRedirect("contact.jsp?status=Successfully Submited");
				}else 
				{
					response.sendRedirect("contact.jsp?status=Faild to Submit");
				}
				}catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect("contact.jsp?status=Some Internal Error");
				}
	}

}