package com.messenger.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.messenger.bean.Message;
import com.messenger.bean.Profile;
import com.messenger.dao.MessageDao;
import com.messenger.dao.MessageDaoFactory;


@Path("/profile")
public class ProfileService 
{
	
	Profile prf = new Profile();
	MessageDao dao = MessageDaoFactory.getInstance();
	
	@POST
	@Path("/signup")
	@Produces(MediaType.TEXT_HTML)
	public Response signup(@Context HttpServletRequest req,
						  @FormParam("firstName") String fName,
						  @FormParam("lastName") String lName,
						  @FormParam("mailId") String mailId,
						  @FormParam("password") String password
						  )
	{
		String ip = req.getRemoteAddr();
		
		prf.setfName(fName);
		prf.setlName(lName);
		prf.setMailId(mailId);
		prf.setPassword(password);
		
		if(dao.createProfile(prf)==true)
		{
			String output = "<script type='text/javascript'>"
				    + "function Redirect() {"
                    +"window.location='http://"+ip+":8080/Mail/Login.jsp';"
                    +"}"
                    + "setTimeout('Redirect()', 10);"
           			+ "</script>"
					+ "<h2>Success</h2>";
			return Response.status(200).entity(output).build();
		}
		else
		{
			String output = "<script type='text/javascript'>"
				    + "function Redirect() {"
                    +"window.location='http://"+ip+":8080/Mail/Login.jsp';"
                    +"}"
                    + "setTimeout('Redirect()', 10);"
           			+ "</script>"
					+"<h2>Failed to create</h2>";
			return Response.status(404).entity(output).build();
		}	
	}
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@QueryParam("mailId") String mailId,@QueryParam("password") String password)
	{
		
		Profile prf = dao.login(mailId, password);
		if(prf!=null)
		{
			System.out.println("Login successfull");
			return Response.status(200).entity(prf).build();
		}
		else
		{
			return Response.status(404).entity("Login Failed").build();
		}
		
	}
	
	@GET
	@Path("/contact")
	@Produces(MediaType.APPLICATION_JSON)
	public Response contacts(@PathParam("mailId") String mailId)
	{
		List<String> list = dao.showContacts(mailId);
		if(list!=null)
		{
			String json = new Gson().toJson(list);
		   return Response.status(200).entity(json).build();
		}
		else
		{
			return Response.status(404).entity("Failed").build();
		}
	}
	
	@GET
	@Path("/checkId")
	@Produces(MediaType.TEXT_HTML)
	public Response checkId(@Context HttpServletRequest req,
							@QueryParam("mailId") String mailId)
	{
		
		if(dao.checkId(mailId)==true)
		{
			String output = "<p style='color:red; font-size:15px; margin-left:15px;'>"
					+ "<img src='http://192.168.235.173:8080/MessageService/resources/images/Shield..ico' style='height:40px; width:40px;'>"
					+mailId
					+ " is already in use</p>";
			return Response.status(200).entity(output).build();
		}
		else
		{
			String output = "<p style='color:green; font-size:15px; margin-left:15px;'>"
					+ "<img src='http://192.168.235.173:8080/MessageService/resources/images/Shield.ico' style='height:40px; width:40px;'>"
					+ mailId
					+ " is available</p>";
			return Response.status(200).entity(output).build();
		}
	}
	
	@GET
	@Path("/contacts")
	@Produces(MediaType.TEXT_HTML)
	public Response searchContacts(@QueryParam("mailId") String str)
	{
		List<String> list = dao.searchContacts(str);
		String output = "<table>";
		String content = "";
		if(list!=null)
		{
			for(int i=0;i<list.size();i++)
			{
				content =  "<tr>"
						+ "<td>"+list.get(i)+ "</td>"
						+ "</tr>";
			}
			output = output+content+"</table>";		
		}
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Path("/inbox")
	@Produces(MediaType.APPLICATION_JSON)
	public Response inbox(@QueryParam("mailId") String str)
	{
		List<Message> list = dao.showInbox(str);
		String json = null;
		if(list!=null)
		{
			json = new Gson().toJson(list);
		}
		System.out.println("Message is....."+list.get(0).getContent());
		return Response.status(200).entity(json).build();
	}
	
	@GET
	@Path("/outbox")
	@Produces(MediaType.APPLICATION_JSON)
	public Response outbox(@QueryParam("mailId") String str)
	{
		System.out.println("Mail Id is ....."+str);
		List<Message> list = dao.showOutbox(str);
		String json = null;
		if(list!=null)
		{
			json = new Gson().toJson(list);
		}
		System.out.println("Message is....."+list.get(0).getContent());
		return Response.status(200).entity(json).build();
	}
	
	
}
