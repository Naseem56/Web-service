package com.messenger.dao;

public class MessageDaoFactory 
{
	final static String type = "JDBC";
	
	public static MessageDao getInstance()
	{
		if(type.equals("JDBC"))
		{
			return new JDBCImpl();
		}
		else
		if(type.equals("HIBERNATE"))
		{
			return null;
		}
		else
		{
			return null;
		}
	}
}
