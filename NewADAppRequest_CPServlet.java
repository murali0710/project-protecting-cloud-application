package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.SecurityDAO;


@WebServlet("/NewADAppRequest_CPServlet")
public class NewADAppRequest_CPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i = Integer.parseInt(request.getParameter("uid"));
		try {
		 int bid = new SecurityDAO().cpAcceptNewApplicationfromAD(i);
		 if(bid!=0) 
		 {
			 response.sendRedirect("NewApplicationAccept.jsp?status=Accepted Successful");
		 }
		 else 
		 {
			 response.sendRedirect("NewApplicationAccept.jsp?status=Not Accepted");
		 }
		}catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("NewApplicationAccept.jsp?status=Some Internal Error");
		}
	}
}