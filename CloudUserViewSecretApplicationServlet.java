package com.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbconnection.DbConnection;
import com.util.AES;

/**
 * Servlet implementation class CloudUserViewSecretApplicationServlet
 */
@WebServlet("/CloudUserViewSecretApplicationServlet")
public class CloudUserViewSecretApplicationServlet extends HttpServlet {
	Connection con;
	String code=null;
	String dec=null;
	String encodedKey=null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i = Integer.parseInt(request.getParameter("bid"));
		con = new DbConnection().getConnection();
		try {
			 Thread.sleep(5000);
			PreparedStatement ps = con.prepareStatement("select encrypted,encrypted_key from vmbinary where bid=? and status='active' and binary_type='SBS'");
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) 
			{
				code =  rs.getString(1);
				encodedKey =  rs.getString(2);
				byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
				SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); 
				new AES();
				dec = AES.decrypt(code, originalKey);
				System.out.println("dec--->"+dec);
			}
			if(dec!=null) 
			{
				request.setAttribute("code", dec);
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
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			response.sendRedirect("ViewPublicApplication_clouduser.jsp?status=Secret Key Error");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("ViewPublicApplication_clouduser.jsp?status=AES Code Error");
		}
	}
}
