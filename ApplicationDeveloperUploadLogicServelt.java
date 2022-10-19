package com.servlet;

import java.io.IOException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.SecurityDAO;
import com.util.AES;
import com.util.Bean;
import com.util.BinaryConversion;
import com.util.LogicBean;

/**
 * Servlet implementation class ApplicationDeveloperUploadLogicServelt
 */
@WebServlet("/ApplicationDeveloperUploadLogicServelt")
public class ApplicationDeveloperUploadLogicServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			HttpSession ses = request.getSession();
			String uname = (String)ses.getAttribute("uname");
			int uid = (Integer)ses.getAttribute("uid");
			
			LogicBean lo = new LogicBean();
			String logic = request.getParameter("logic");
			String binary = request.getParameter("binary");
			String vmimage =  request.getParameter("vmimage");
			String search =  request.getParameter("lname");
						
			try {
				Thread.sleep(5000);
			if(binary.equals("Public Binary")) 
			{
				String stringtobinary = new BinaryConversion().toBinary(logic);
				lo.setUploadedby(uname);
				lo.setBinarytype(binary);
				lo.setVmimage(vmimage);
				lo.setOriginalcode(logic);
				lo.setBinarycode(stringtobinary);
				lo.setUid(uid);
				lo.setSearch(search);
				
				int i = new SecurityDAO().ADUploadPublicBinary(lo);
				if(i!=0) {
					response.sendRedirect("UploadApplication_AD.jsp?status=You Public Binary Uploaded Successful");	
				}
				else 
				{
					response.sendRedirect("UploadApplication_AD.jsp?status=Not Uploaded");
				}
			}
			
			if(binary.equals("SBS"))
			{
				String stringtobinary = new BinaryConversion().toBinary(logic);
				
				KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
				keyGenerator.init(128);
				SecretKey secretKey = keyGenerator.generateKey();
				
				String encrypt = new AES().encrypt(logic, secretKey);
				
				lo.setUploadedby(uname);
				lo.setBinarytype(binary);
				lo.setVmimage(vmimage);
				lo.setOriginalcode(logic);
				lo.setBinarycode(stringtobinary);
				lo.setEncrypted(encrypt);
				lo.setUid(uid);
				lo.setSearch(search);
				String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
				lo.setEncryptKey(encodedKey);
				int i = new SecurityDAO().ADUploadSBSBinary(lo);
				if(i!=0) {
					response.sendRedirect("UploadApplication_AD.jsp?status=SBS Uploaded Successful");
				}
				else 
				{
					response.sendRedirect("UploadApplication_AD.jsp?status=Not Successful");
				}
			}
			}catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("UploadApplication_AD.jsp?status=Some Internal Error");
			}
	}
}