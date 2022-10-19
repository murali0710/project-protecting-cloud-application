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
 * Servlet implementation class NewCloudUserRequest_CPServlet
 */
@WebServlet("/NewCloudUserRequest_CPServlet")
public class NewCloudUserRequest_CPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Bean b = new Bean();
		int uid = Integer.parseInt(request.getParameter("uid"));
		try {
			int i = new SecurityDAO().cpAcceptNewCloudUser(uid);
			if(i!=0) 
			{
				response.sendRedirect("NewCloudProviderRequest_CP.jsp?status=Accepted Successful");
			}
			else 
			{
				response.sendRedirect("NewCloudProviderRequest_CP.jsp?status=Not Accepted");
			}
			}catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("NewCloudProviderRequest_CP.jsp?status=Some Internal Error");
			}
	}
}
