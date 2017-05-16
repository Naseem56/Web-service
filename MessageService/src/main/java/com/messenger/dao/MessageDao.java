package com.messenger.dao;

import java.util.List;

import com.messenger.bean.Message;
import com.messenger.bean.Profile;

public interface MessageDao 
{
	public Message sendMessage(Message msg);
	public Boolean createProfile(Profile prf);
	public List<String> showContacts(String mailId);
	public Profile login(String mailId,String password);
	public Boolean checkId(String mailId);
	public List<String> searchContacts(String str);
	public List<Message> showInbox(String str);
	public List<Message> showOutbox(String str);
	
}
