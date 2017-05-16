package com.messenger.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4686900502955757470L;
	private String fName;
	private String lName;
	private String mailId;
	private String password;
	
	
	public String getfName() {
		return fName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	
	
}
