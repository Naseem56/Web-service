package com.messenger.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.messenger.bean.Message;
import com.messenger.bean.Profile;
import com.mysql.jdbc.Driver;


public class JDBCImpl implements MessageDao {
	
	//Store message in database
	@Override
	public Message sendMessage(Message msg) 
	{
		Connection con 			 = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs 			 = null;
		Statement stmt			 = null;
		
		try {
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);

			String dbUrl = "jdbc:mysql://localhost:3306/messenger?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);

			String query1 = "insert into message (subject,message) values"
					+ "(?,?)";
			pstmt1 = con.prepareStatement(query1);
			pstmt1.setString(1, msg.getSubject());
			pstmt1.setString(2, msg.getContent());
			pstmt1.executeUpdate();
			
			String query = " select max(msgId) from message ";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			int msgId = 0;
			if(rs.next())
			{
				msgId = rs.getInt(1);
				System.out.println("Message Id is ...... "+msgId);
			}
			
			
			List<String> list = msg.getRecipints();
			
			for(int i=0;i<list.size();i++)
			{
				String query2 = "insert into recipients values"
					+ "(?,?,?)";
				pstmt2 = con.prepareStatement(query2);
				pstmt2.setInt(1, msgId);
				pstmt2.setString(2, msg.getFrom());
				pstmt2.setString(3, list.get(i));
				System.out.println("List......."+list.get(i));
				pstmt2.executeUpdate();
			}
						
			
			return msg;
			
		}
		catch (Exception e) {
			
			System.out.println("Error in send message JDBC impl");
			e.printStackTrace();
			return null;
			
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (pstmt1 != null) {
					pstmt1.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if(rs!=null)
				{
					rs.close();
				}
				if(stmt!=null)
				{
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}


	//Create new Profile
	@Override
	public Boolean createProfile(Profile prf) 
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);

			String dbUrl = "jdbc:mysql://localhost:3306/messenger?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);

			String query1 = "insert into profile values"
					+ "(?,?,?,?)";
			pstmt = con.prepareStatement(query1);
			pstmt.setString(1, prf.getMailId());
			pstmt.setString(2, prf.getfName());
			pstmt.setString(3, prf.getlName());
			pstmt.setString(4, prf.getPassword());

			int count = pstmt.executeUpdate();
			
			
			if(count>0)
			{
				return true;
			}
			else
			{
				return false;
			}		
		
		}
		catch (Exception e) {

			System.out.println("Error in signup JDBC impl");
			e.printStackTrace();
			return false;

		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//Retrieve contacts
	public List<String> showContacts(String mailId)
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs   = null;
		
		try {
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);

			String dbUrl = "jdbc:mysql://localhost:3306/messenger?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);

			String query = " select * from profile ";				
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			List<String> list = new ArrayList<String>();
			while(rs.next())
			{
				list.add(rs.getString("mailId"));
			}
			return list;
			
		}
		catch (Exception e) {

			System.out.println("Error in Show contacts JDBC impl");
			e.printStackTrace();
			return null;

		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//Login
	@Override
	public Profile login(String mailId, String password)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs   = null;
		
		try {
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);

			String dbUrl = "jdbc:mysql://localhost:3306/messenger?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);

			String query = " select * from profile where mailId=? and password=? ";				
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mailId);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			Profile prf = null;
			while(rs.next())
			{
				prf = new Profile();
				prf.setfName(rs.getString("FirstName"));
				prf.setlName(rs.getString("LastName"));
				prf.setMailId(rs.getString("mailId"));
				prf.setPassword(rs.getString("password"));
			}
			return prf;
			
		}
		catch (Exception e) {

			System.out.println("Error in login JDBC impl");
			e.printStackTrace();
			return null;

		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//Check MailId already exist or not
	@Override
	public Boolean checkId(String mailId)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs   = null;
		
		try {
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);

			String dbUrl = "jdbc:mysql://localhost:3306/messenger?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);

			String query = " select * from profile where mailId=?";				
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mailId);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}			
		}
		catch (Exception e) {

			System.out.println("Error in checkId JDBC impl");
			e.printStackTrace();
			return false;

		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public List<String> searchContacts(String str) 
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs   = null;
		
		try {
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);

			String dbUrl = "jdbc:mysql://localhost:3306/messenger?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);

			String query = " select * from profile where mailId like ? ";				
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, str+"%");
			rs = pstmt.executeQuery();
			
			List<String> list = new ArrayList<String>();
			while(rs.next())
			{
				list.add(rs.getString("mailId"));
			}
			return list;
			
		}
		catch (Exception e) {

			System.out.println("Error in Show contacts JDBC impl");
			e.printStackTrace();
			return null;

		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Message> showInbox(String str)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs   = null;
		
		try {
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);

			String dbUrl = "jdbc:mysql://localhost:3306/messenger?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);

			String query = " select * from message m,recipients r where m.msgid=r.msgid "
					+ " and m.msgid in (select msgid from recipients where reciever = ?) ";				
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, str);
			rs = pstmt.executeQuery();
			
			List<Message> list = new ArrayList<Message>();
			while(rs.next())
			{
				Message msg = new Message();
				msg.setFrom(rs.getString("sender"));
				msg.setContent(rs.getString("message"));
				msg.setSubject(rs.getString("subject"));
				list.add(msg);
			}
			return list;
			
		}
		catch (Exception e) {

			System.out.println("Error in Show inbox JDBC impl");
			e.printStackTrace();
			return null;

		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public List<Message> showOutbox(String str)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs   = null;
		
		try {
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);

			String dbUrl = "jdbc:mysql://localhost:3306/messenger?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);

			String query = " select * from message m,recipients r where m.msgid=r.msgid "
					+ " and m.msgid in (select distinct msgid from recipients where sender = ?) ";				
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, str);
			rs = pstmt.executeQuery();
			
			List<Message> list = new ArrayList<Message>();
			while(rs.next())
			{
				Message msg = new Message();
				msg.setTo(rs.getString("reciever"));
				msg.setContent(rs.getString("message"));
				msg.setSubject(rs.getString("subject"));
				list.add(msg);
			}
			return list;
			
		}
		catch (Exception e) {

			System.out.println("Error in Show outbox JDBC impl");
			e.printStackTrace();
			return null;

		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
