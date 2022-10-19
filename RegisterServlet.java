package com.servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.SecurityDAO;
import com.util.Bean;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Bean b = new Bean();
		b.setUname(request.getParameter("name"));
		b.setPassword(request.getParameter("password"));
		b.setEmail(request.getParameter("email"));
		
		String mobile = request.getParameter("mobile");
		b.setMobile(mobile);
		b.setDate(request.getParameter("dob"));
		b.setUtype(request.getParameter("utype"));
		b.setCard(request.getParameter("card"));
		try {
		int i = new SecurityDAO().reg(b);
		if(i!=0) 
		{
			response.sendRedirect("register.jsp?status=Registred Successfully");
		}
		else 
		{
			response.sendRedirect("register.jsp?status=Registred Faild");
		}
		}catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("register.jsp?status=Some Internal Error");
		}
	}
}