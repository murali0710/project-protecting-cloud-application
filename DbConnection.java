package com.dbconnection;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	
	public Connection getConnection(){
		Connection con=null;
        try{
        	Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/MJCC02_2022";
            con=DriverManager.getConnection(url,"root","root");
            System.out.println("------Database Connected----");
        }catch(Exception e)
         {
             e.printStackTrace();
            
         }
        return con;
    }
}