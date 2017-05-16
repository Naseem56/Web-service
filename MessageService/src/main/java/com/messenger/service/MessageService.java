package com.messenger.service;

import java.util.Arrays;
import java.util.List;

import javax.activation.MailcapCommandMap;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.messenger.bean.Message;
import com.messenger.dao.MessageDao;
import com.messenger.dao.MessageDaoFactory;

@Path("profile/message")
public class MessageService 
{
	
	Message msg = new Message();
	MessageDao dao = MessageDaoFactory.getInstance();
	
	
	@GET
	@Path("/send")
	@Produces(MediaType.TEXT_HTML)
	public Response sendMessage(@Context HttpServletRequest req,
							   @QueryParam("mailId")  String from,
							   @QueryParam("message") String message,
							   @QueryParam("subject") String subject,
			                   @QueryParam("to") String to)
	{
		String[] s1 = to.split(",");
		List<String> list = Arrays.asList(s1);
		msg.setFrom(from);
		msg.setContent(message);
		msg.setSubject(subject);
		msg.setRecipints(list);
		
		String ip = req.getRemoteAddr();
		System.out.println("Ip address is....."+ip);
		System.out.println("From..........."+from);
		System.out.println("Message........"+message);
		
		if(dao.sendMessage(msg)!=null)
		{
			List<Message> li = dao.showOutbox(from);
			String json = null;
			if(list!=null)
			{
				json = new Gson().toJson(li);
			}
			return Response.status(200).entity(json).build();
		}
		else
		{
			String output =  "<h2>Failed to send</h2>";
			return Response.status(404).entity(output).build();
			
		}		
	}
}
