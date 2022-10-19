package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.SecurityDAO;
import com.dbconnection.DbConnection;
import com.util.LogicBean;

/**
 * Servlet implementation class OtpCheckServelet
 */
@WebServlet("/OtpCheckServelet")
public class OtpCheckServelet extends HttpServlet {
	Connection con;
	String vmimage = null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bid = Integer.parseInt(request.getParameter("bid"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		String uname = request.getParameter("uname");
		String key = request.getParameter("key");
		String cotp = request.getParameter("cotp");
		if(!key.equals(cotp)) 
		{
			
			response.sendRedirect("OtpCheck.jsp?status=Invalid Otp");
		}
		else 
		{
			
			try {
			con = new DbConnection().getConnection();
			
			
			
			PreparedStatement ps = con.prepareStatement("select vmimage from vmbinary where bid=?");
			ps.setInt(1, bid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) 
			{
				vmimage = rs.getString(1);
			}
			if(!vmimage.isEmpty()) 
			{
				LogicBean lo = new LogicBean();
				lo.setBid(bid);
				lo.setUid(uid);
				lo.setUploadedby(uname);
				lo.setVmimage(vmimage);
				lo.setBinarycode(key);
				
				new SecurityDAO().cloudUserPurchaseApplication(lo);
			}
			response.sendRedirect("ViewApplication_user.jsp?status=Conformation Send to Cloud Provider if Accept it shows in purchased Application");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}

}
