package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.dbconnection.DbConnection;
import com.util.Bean;
import com.util.LogicBean;


public class SecurityDAO extends DbConnection {
static Connection con=null;
	
	public SecurityDAO() {
		con=getConnection();
	}
	public int reg(Bean b) throws Exception
	{
		int i = 0;
			PreparedStatement ps = con.prepareStatement("insert into userdetails(username,password,email,mobile,dob,status,utype,card) values(?,?,?,?,?,'inactive',?,?)");
			ps.setString(1, b.getUname());
			ps.setString(2, b.getPassword());
			ps.setString(3, b.getEmail());
			ps.setString(4, b.getMobile());
			String d = b.getDate();
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			Date da = new Date(sd.parse(d).getTime());
			ps.setDate(5, da);
			ps.setString(6, b.getUtype());
			ps.setString(7, b.getCard());
			i= ps.executeUpdate();
			con.close();
		return i;	
	}
	
	public int feedback(Bean b) throws Exception
	{
		int i = 0;
			PreparedStatement ps = con.prepareStatement("insert into feedback(fname,lname,email,message) values(?,?,?,?)");
			ps.setString(1, b.getMobile());
			ps.setString(2, b.getPassword());
			ps.setString(3, b.getEmail());
			ps.setString(4, b.getUname());
			i= ps.executeUpdate();
			con.close();
		return i;	
	}
	
	public ArrayList<Bean> login(Bean b) throws Exception
	{
		
		ArrayList<Bean> al = new ArrayList();
		
			PreparedStatement ps = con.prepareStatement("select uid,username,email,utype from userdetails where email=? and password=? and status='active'");
			ps.setString(1, b.getEmail());
			ps.setString(2, b.getPassword());
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Bean value = new Bean();
				value.setUid(rs.getInt(1));
				value.setUname(rs.getString(2));
				value.setEmail(rs.getString(3));
				value.setUtype(rs.getString(4));
				al.add(value);
			}
			con.close();
		return al;
	}
	
	public int cpAcceptNewApplictionDeveloper(int uid) throws Exception
	{
		int i = 0;
			PreparedStatement ps = con.prepareStatement("update userdetails set status='active' where uid=? and utype='Application Developer'");
			ps.setInt(1, uid);
			i= ps.executeUpdate();
			con.close();
		return i;	
	}
	
	public int cpAcceptNewCloudUser(int uid) throws Exception
	{
		int i = 0;
			PreparedStatement ps = con.prepareStatement("update userdetails set status='active' where uid=? and utype='Cloud User'");
			ps.setInt(1, uid);
			i= ps.executeUpdate();
			con.close();
		return i;	
	}
	
	public int cpADDVmImage(String vmimage) throws Exception
	{
		int i = 0;
			PreparedStatement ps = con.prepareStatement("insert into vmimage(vmimagename) values(?)");
			ps.setString(1, vmimage);
			i= ps.executeUpdate();
			con.close();
		return i;	
	}
	
	public int ADUploadPublicBinary(LogicBean lo) throws Exception
	{
		int i = 0;
			PreparedStatement ps = con.prepareStatement("insert into vmbinary(uploadedby,vmimage,original_code,binary_code,encrypted,binary_type,status,uploadid,search) values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, lo.getUploadedby());
			ps.setString(2, lo.getVmimage());
			ps.setString(3, lo.getOriginalcode());
			ps.setString(4, lo.getBinarycode());
			ps.setString(5, "Not Encrypted");
			ps.setString(6, lo.getBinarytype());
			ps.setString(7, "send to CP");
			ps.setInt(8, lo.getUid());
			ps.setString(9, lo.getSearch());
			i= ps.executeUpdate();
			con.close();
		return i;	
	}
	
	public int ADUploadSBSBinary(LogicBean lo) throws Exception
	{
		int i = 0;
			PreparedStatement ps = con.prepareStatement("insert into vmbinary(uploadedby,vmimage,original_code,binary_code,encrypted,binary_type,status,uploadid,search,encrypted_key) values(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, lo.getUploadedby());
			ps.setString(2, lo.getVmimage());
			ps.setString(3, lo.getOriginalcode());
			ps.setString(4, lo.getBinarycode());
			ps.setString(5, lo.getEncrypted());
			ps.setString(6, lo.getBinarytype());
			ps.setString(7, "send to CP");
			ps.setInt(8, lo.getUid());
			ps.setString(9, lo.getSearch());
			ps.setString(10, lo.getEncryptKey());
			i= ps.executeUpdate();
			con.close();
		return i;	
	}
	
	public int cpAcceptNewApplicationfromAD(int bid) throws Exception
	{
		int i = 0;
			PreparedStatement ps = con.prepareStatement("update vmbinary set status='active' where bid=?");
			ps.setInt(1, bid);
			i= ps.executeUpdate();
			con.close();
		return i;	
	}
	
	public int cloudUserPurchaseApplication(LogicBean lo) throws Exception
	{
		int i = 0;
			PreparedStatement ps = con.prepareStatement("insert into purchased(vmImage,purchased_by,purchased_user_id,puchase_key,binary_id,status)value(?,?,?,?,?,?)");
			ps.setString(1, lo.getVmimage());
			ps.setString(2, lo.getUploadedby());
			ps.setInt(3, lo.getUid());
			ps.setString(4, lo.getBinarycode());
			ps.setInt(5, lo.getBid());
			ps.setString(6, "Send to CP");
			i= ps.executeUpdate();
			con.close();
		return i;	
	}
	
	public int cpAcceptCloudUserPurchaseRequest(int pid) throws Exception
	{
		int i = 0;
			PreparedStatement ps = con.prepareStatement("update purchased set  status='active' where pid=?");
			ps.setInt(1, pid);
			i= ps.executeUpdate();
			con.close();
		return i;	
	}
}