package com.messenger.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3537395275998642401L;
	private String from;
	private String to;
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	private String content;
	private String subject;
	private List<String> recipints;
	
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getFrom() 
	{
		return from;
	}
	public void setFrom(String from) 
	{
		this.from = from;
	}
	public String getContent() 
	{
		return content;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}
	public List<String> getRecipints() 
	{
		return recipints;
	}
	public void setRecipints(List<String> recipints) 
	{
		this.recipints = recipints;
	}
	
	
}
