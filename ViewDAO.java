package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.dbconnection.DbConnection;
import com.util.Bean;
import com.util.LogicBean;

import sun.java2d.pipe.SpanShapeRenderer.Simple;

public class ViewDAO extends DbConnection {
 static Connection con = null;
 public ViewDAO() {
	con=getConnection();
}
 
 public ArrayList<Bean> viewCPnewApplicationDeveloperforAccept() throws Exception
 {
	 ArrayList<Bean> al = new ArrayList();
	 PreparedStatement ps = con.prepareStatement("select uid,username,email,mobile from userdetails where status='inactive' and utype='Application Developer'");
	 ResultSet rs = ps.executeQuery();
	 while(rs.next()) 
	 {
		 Bean b = new Bean();
		 b.setUid(rs.getInt(1));
		 b.setUname(rs.getString(2));
		 b.setEmail(rs.getString(3));
		 b.setMobile(rs.getString(4));
		 al.add(b);
	 }
	 return al;
 }
 
 public ArrayList<Bean> viewCPnewCloudUserforAccept() throws Exception
 {
	 ArrayList<Bean> al = new ArrayList();
	 PreparedStatement ps = con.prepareStatement("select uid,username,email,mobile from userdetails where status='inactive' and utype='Cloud User'");
	 ResultSet rs = ps.executeQuery();
	 while(rs.next()) 
	 {
		 Bean b = new Bean();
		 b.setUid(rs.getInt(1));
		 b.setUname(rs.getString(2));
		 b.setEmail(rs.getString(3));
		 b.setMobile(rs.getString(4));
		 al.add(b);
	 }
	 return al;
 }
 
 public ArrayList<Bean> viewCPApplicationDeveloper() throws Exception
 {
	 ArrayList<Bean> al = new ArrayList();
	 PreparedStatement ps = con.prepareStatement("select uid,username,email,mobile,dob from userdetails where status='active' and utype='Application Developer'");
	 ResultSet rs = ps.executeQuery();
	 while(rs.next()) 
	 {
		 Bean b = new Bean();
		 b.setUid(rs.getInt(1));
		 b.setUname(rs.getString(2));
		 b.setEmail(rs.getString(3));
		 b.setMobile(rs.getString(4));
		 Date d= rs.getDate(5);
		 SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		 String date = sd.format(d);
		 b.setDate(date);
		 al.add(b);
	 }
	 return al;
 }
 
 public ArrayList<Bean> viewCPCloudUser() throws Exception
 {
	 ArrayList<Bean> al = new ArrayList();
	 PreparedStatement ps = con.prepareStatement("select uid,username,email,mobile,dob from userdetails where status='active' and utype='Cloud User'");
	 ResultSet rs = ps.executeQuery();
	 while(rs.next()) 
	 {
		 Bean b = new Bean();
		 b.setUid(rs.getInt(1));
		 b.setUname(rs.getString(2));
		 b.setEmail(rs.getString(3));
		 b.setMobile(rs.getString(4));
		 Date d= rs.getDate(5);
		 SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		 String date = sd.format(d);
		 b.setDate(date);
		 al.add(b);
	 }
	 return al;
 }
 
 public ArrayList<Bean> viewCPVMImage() throws Exception
 {
	 ArrayList<Bean> al = new ArrayList();
	 PreparedStatement ps = con.prepareStatement("select vmid,vmimagename from vmimage order by vmid");
	 ResultSet rs = ps.executeQuery();
	 while(rs.next()) 
	 {
		 Bean b = new Bean();
		 b.setUid(rs.getInt(1));
		 b.setUname(rs.getString(2));
		 al.add(b);
	 }
	 return al;
 }
 
 public ArrayList<Bean> viewADVMImage() throws Exception
 {
	 ArrayList<Bean> al = new ArrayList();
	 PreparedStatement ps = con.prepareStatement("select vmid,vmimagename from vmimage order by vmid");
	 ResultSet rs = ps.executeQuery();
	 while(rs.next()) 
	 {
		 Bean b = new Bean();
		 b.setUid(rs.getInt(1));
		 b.setUname(rs.getString(2));
		 al.add(b);
	 }
	 return al;
 }
 
 public ArrayList<LogicBean> viewADLogic(LogicBean lo) throws Exception
 {
	 ArrayList<LogicBean> al = new ArrayList();
	 PreparedStatement ps = con.prepareStatement("select bid,original_code,status,binary_type from vmbinary where uploadid=? and uploadedby=?");
	 ps.setInt(1, lo.getUid());
	 ps.setString(2, lo.getUploadedby());
	 ResultSet rs = ps.executeQuery();
	 while(rs.next()) 
	 {
		 LogicBean b = new LogicBean();
		 b.setBid(rs.getInt(1));
		 b.setOriginalcode(rs.getString(2));
		 b.setStatus(rs.getString(3));
		 b.setBinarytype(rs.getString(4));
		 al.add(b);
	 }
	 return al;
 }
 
 public ArrayList<LogicBean> cpViewADApplicationRequest() throws Exception
 {
	 ArrayList<LogicBean> al = new ArrayList();
	 PreparedStatement ps = con.prepareStatement("select bid,uploadedby,original_code,binary_code,binary_type from vmbinary where status='send to CP'");
	 ResultSet rs = ps.executeQuery();
	 while(rs.next()) 
	 {
		 LogicBean b = new LogicBean();
		 b.setBid(rs.getInt(1));
		 b.setUploadedby(rs.getString(2));
		 b.setOriginalcode(rs.getString(3));
		 b.setBinarycode(rs.getString(4));
		 b.setBinarytype(rs.getString(5));
		 al.add(b);
	 }
	 return al;
 }
 
 public ArrayList<LogicBean> userViewADApplications() throws Exception
 {
	 ArrayList<LogicBean> al = new ArrayList();
	 PreparedStatement ps = con.prepareStatement("select bid,search,vmimage,binary_type,uploadedby from vmbinary where status='active'");
	 ResultSet rs = ps.executeQuery();
	 while(rs.next()) 
	 {
		 LogicBean b = new LogicBean();
		 b.setBid(rs.getInt(1));
		 b.setSearch(rs.getString(2));
		 b.setVmimage(rs.getString(3));
		 b.setBinarytype(rs.getString(4));
		 b.setUploadedby(rs.getString(5));
		 al.add(b);
	 }
	 return al;
 }
 
 public ArrayList<LogicBean> cpViewClodUserPurchaseAccept() throws Exception
 {
	 ArrayList<LogicBean> al = new ArrayList();
	 PreparedStatement ps = con.prepareStatement("select pid,purchased_by,vmImage, binary_id,puchase_key from purchased where status='Send to CP'");
	 ResultSet rs = ps.executeQuery();
	 while(rs.next()) 
	 {
		 LogicBean b = new LogicBean();
		 b.setUid(rs.getInt(1));
		 b.setUploadedby(rs.getString(2));
		 b.setOriginalcode(rs.getString(3));
		 b.setBid(rs.getInt(4));
		 b.setEncrypted(rs.getString(5));
		 al.add(b);
	 }
	 return al;
 }
 
 public ArrayList<LogicBean> cloudUserViewPurchasedApplication(String uname,int uid) throws Exception
 {
	 ArrayList<LogicBean> al = new ArrayList();
	 PreparedStatement ps = con.prepareStatement("select pid,vmImage,binary_id from purchased where status='active' and purchased_by=? and purchased_user_id=?");
	 ps.setString(1, uname);
	 ps.setInt(2, uid);
	 ResultSet rs = ps.executeQuery();
	 while(rs.next()) 
	 {
		 LogicBean b = new LogicBean();
		 b.setUid(rs.getInt(1));
		 b.setOriginalcode(rs.getString(2));
		 b.setBid(rs.getInt(3));
		 al.add(b);
	 }
	 return al;
 }
 
 public ArrayList<LogicBean> cpViewPurchasedApplications() throws Exception
 {
	 ArrayList<LogicBean> al = new ArrayList();
	 PreparedStatement ps = con.prepareStatement("select pid,vmImage,purchased_by, purchased_user_id,puchase_key,binary_id, status from purchased ");
	 ResultSet rs = ps.executeQuery();
	 while(rs.next()) 
	 {
		 LogicBean b = new LogicBean();
		 b.setPid(rs.getInt(1));
		 b.setBinarycode(rs.getString(2));
		 b.setUploadedby(rs.getString(3));
		 b.setUid(rs.getInt(4));
		 b.setEncryptKey(rs.getString(5));
		 b.setBid(rs.getInt(6));
		 b.setStatus(rs.getString(7));
		 al.add(b);
	 }
	 return al;
 }
 
 public ArrayList<LogicBean> cpViewPublicBinaryApplications() throws Exception
 {
	 ArrayList<LogicBean> al = new ArrayList();
	 PreparedStatement ps = con.prepareStatement("select  bid,uploadid,uploadedby,vmimage,status from vmbinary where binary_type='Public Binary'");
	 ResultSet rs = ps.executeQuery();
	 while(rs.next()) 
	 {
		 LogicBean b = new LogicBean();
		 b.setBid(rs.getInt(1));
		 b.setUid(rs.getInt(2));
		 b.setUploadedby(rs.getString(3));
		 b.setOriginalcode(rs.getString(4));
		 b.setStatus(rs.getString(5));
		 al.add(b);
	 }
	 return al;
 }
 
 public ArrayList<LogicBean> cpViewSecretBinaryApplications() throws Exception
 {
	 ArrayList<LogicBean> al = new ArrayList();
	 PreparedStatement ps = con.prepareStatement("select  bid,uploadid,uploadedby,vmimage,status from vmbinary where binary_type='SBS'");
	 ResultSet rs = ps.executeQuery();
	 while(rs.next()) 
	 {
		 LogicBean b = new LogicBean();
		 b.setBid(rs.getInt(1));
		 b.setUid(rs.getInt(2));
		 b.setUploadedby(rs.getString(3));
		 b.setOriginalcode(rs.getString(4));
		 b.setStatus(rs.getString(5));
		 al.add(b);
	 }
	 return al;
 }
 
 public String cloudUserCheckCard(int uid) throws Exception
 {
	 String card = null;
	 PreparedStatement ps = con.prepareStatement("select card from userdetails where uid=?");
	 ps.setInt(1, uid);
	 ResultSet rs = ps.executeQuery();
	 while(rs.next()) 
	 {
		 card = rs.getString(1);
	 }
	 return card;
 }
}