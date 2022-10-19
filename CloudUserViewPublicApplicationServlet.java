package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbconnection.DbConnection;

/**
 * Servlet implementation class CloudUserViewPublicApplicationServlet
 */
@WebServlet("/CloudUserViewPublicApplicationServlet")
public class CloudUserViewPublicApplicationServlet extends HttpServlet {
	Connection con;
	String code = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bid =  Integer.parseInt(request.getParameter("bid"));
		 
		con = new DbConnection().getConnection();
		try {
			 Thread.sleep(5000);
			PreparedStatement ps = con.prepareStatement("select  original_code from vmbinary where bid=? and status='active' and binary_type='Public Binary'");
			ps.setInt(1, bid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) 
			{
				code =  rs.getString(1);
			}
			if(!code.isEmpty()) 
			{
				request.setAttribute("code", code);
				RequestDispatcher rd =request.getRequestDispatcher("ViewPublicApplication_clouduser.jsp"); 
				rd.include(request, response);
			}
			else 
			{
				response.sendRedirect("ViewPublicApplication_clouduser.jsp?status=Code Not Available");
			}
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
			response.sendRedirect("ViewPublicApplication_clouduser.jsp?status=Some Internal Error");
		}
	}
}