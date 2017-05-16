package com.messenger.dao;

import java.util.List;

import com.messenger.bean.Message;

public class Test {

	public static void main(String[] args) 
	{
		MessageDao dao = MessageDaoFactory.getInstance();
		List<Message> list=dao.showInbox("xyz@gmail.com");
		System.out.println(list.get(2).getContent());

	}

}
