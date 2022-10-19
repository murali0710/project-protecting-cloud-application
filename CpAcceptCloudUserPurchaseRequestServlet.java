package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.SecurityDAO;

/**
 * Servlet implementation class CpAcceptCloudUserPurchaseRequestServlet
 */
@WebServlet("/CpAcceptCloudUserPurchaseRequestServlet")
public class CpAcceptCloudUserPurchaseRequestServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pid =Integer.parseInt(request.getParameter("pid"));
			 try {
				int i = new SecurityDAO().cpAcceptCloudUserPurchaseRequest(pid);
				if(i!=0) 
				{
					response.sendRedirect("Purchase ApplicationFromCloudUser_cp.jsp?status=Accepted Successful");
				}
				else 
				{
					response.sendRedirect("Purchase ApplicationFromCloudUser_cp.jsp?status=Not Accepted");
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("Purchase ApplicationFromCloudUser_cp.jsp?status=Some Internal Error");
				
			}
		}
	}