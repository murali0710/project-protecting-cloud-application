package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dbconnection.DbConnection;
import com.util.EMailer;
import com.util.LogicBean;

/**
 * Servlet implementation class CloudUserSendSBSRequestApplicationServlet
 */
@WebServlet("/CloudUserSendSBSRequestApplicationServlet")
public class CloudUserSendSBSRequestApplicationServlet extends HttpServlet {
	Connection con;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pid = 0;
		int bid = Integer.parseInt(request.getParameter("bid"));
		HttpSession ses = request.getSession();
		int uid =  (Integer)ses.getAttribute("uid");
		String uname = (String)ses.getAttribute("uname");
		con = new DbConnection().getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select purchased_user_id from purchased where purchased_by=? and purchased_user_id=? and binary_id=?");
			ps.setString(1, uname);
			ps.setInt(2, uid);
			ps.setInt(3, bid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) 
			{
				pid =  rs.getInt(1);
			}
			if(pid!=0) 
			{
				response.sendRedirect("ViewApplication_user.jsp?status=Already Purchased");
			}
			else 
			{
				Random rand = new Random();
				Long lo =  (long) rand.nextInt(1000000000);
				String key =  lo.toString();
				/*System.out.println("OtpKey--->"+key);*/
				EMailer.send("studentmail@gmail.com","student1mail","studentmail@gmail.com","This is your security key Dont Share Your Key to any one","Your OTP:\n"+key);
				LogicBean lb = new LogicBean();
				lb.setBid(bid);
				lb.setUid(uid);
				lb.setUploadedby(uname);
				lb.setBinarycode(key);
				
				ArrayList<LogicBean> al = new ArrayList<>();
				al.add(lb);
				HttpSession session = request.getSession();
				session.setAttribute("list", al);
				response.sendRedirect("OtpCheck.jsp?status=Please open your email & check, your recevied OTP successfully");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("OtpCheck.jsp?status=Some Internal Error");
		}
	}
}
