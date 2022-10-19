package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.SecurityDAO;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * Servlet implementation class CPAddVMImageServlet
 */
@WebServlet("/CPAddVMImageServlet")
public class CPAddVMImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String vmimage = request.getParameter("vmimage");
		try {
		int  i=  new SecurityDAO().cpADDVmImage(vmimage);
		if(i!=0)
		{
			response.sendRedirect("AddVmImageServer_CP.jsp?status=Successfully Added");
		}
		else 
		{
			response.sendRedirect("AddVmImageServer_CP.jsp?status=Not Added");
		}
		}catch (MySQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			response.sendRedirect("AddVmImageServer_CP.jsp?status=VMImage Name Already Entered");
		}catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("AddVmImageServer_CP.jsp?status=Some Internal Error");
		}
	}
}