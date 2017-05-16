package com.messenger.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.messenger.bean.Message;
import com.messenger.bean.Profile;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


@Controller
public class SigninController 
{
	Profile prf = null;
	Message msg = null;
	

	@RequestMapping(value="/Login", method=RequestMethod.POST)
	public ModelAndView login(@FormParam("mailId") String mailId,@FormParam("password") String password) throws JsonParseException, JsonMappingException, IOException
	{
		System.out.println("Inside Login2");
		System.out.println("MailId....."+mailId+"password..."+password);
		Client client = Client.create();
		
		WebResource webResource = client
				   .resource("http://192.168.235.173:8080/MessageService/webapi/profile/login?mailId="+mailId+"&password="+password);

		
				ClientResponse response = webResource.accept("application/json")
		                   .get(ClientResponse.class);
				System.out.println("Status......."+response.getStatus());
				
				if (response.getStatus() == 200) 
				{
					System.out.println("Inside if");
					String json = response.getEntity(String.class);
					prf = new ObjectMapper().readValue(json, Profile.class);
					System.out.println("Email Id ......"+prf.getMailId());
					  return new ModelAndView("Menu","prf",prf); 
				}
				else
				{
					return new ModelAndView("ELogin");
				}

	}
	
	@RequestMapping("/Compose")
	public ModelAndView compose()
	{
		return new ModelAndView("Compose","prf",prf);
	}
	
	@RequestMapping(value="/Compose2",method=RequestMethod.POST)
	public ModelAndView sendMessage(@FormParam("mailId") String mailId,@FormParam("to") String to,
									@FormParam("subject") String subject,@FormParam("message") String message) throws JsonParseException, JsonMappingException, IOException
	{
		List<Message> list = null;
		
		Client client = Client.create();
		String url = "http://192.168.235.173:8080/MessageService/webapi/profile/message/send";
		
		WebResource webResource = client.resource(
				url+"?mailId="+mailId+"&to="+to+"&subject="+subject+"&message="+message);
		
		ClientResponse response = webResource.accept("text/html").get(ClientResponse.class);
		if(response.getStatus()==200)
		{
			String json = response.getEntity(String.class);
			System.out.println("json...."+json);
			list =  new ObjectMapper().readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class,Message.class));
			ModelAndView mv = new ModelAndView();
			mv.addObject("list",list);
			mv.addObject("prf",prf);
			mv.setViewName("Outbox");
			return mv;
		}
		else
		{
			ModelAndView mv = new ModelAndView();
			mv.addObject("prf",prf);
			mv.setViewName("Menu");
			return mv;
		}
		
		
		
	}
	
	@RequestMapping("/Inbox")
	public ModelAndView inbox() throws JsonParseException, JsonMappingException, IOException
	{
		List<Message> list = null;
		
		Client client = Client.create();
		String mailId = prf.getMailId();

		WebResource webResource = client.resource(
				"http://192.168.235.173:8080/MessageService/webapi/profile/inbox?mailId=" + mailId );

		System.out.println("Inside Web res");
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		System.out.println("Status......." + response.getStatus());

		if (response.getStatus() == 200) {
			System.out.println("Inside if");
			String json = response.getEntity(String.class);
			list =  new ObjectMapper().readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class,Message.class));
			System.out.println("Message recieved in inbox ......" + list.get(0).getContent());
			System.out.println("Email Id ......" + prf.getMailId());
			ModelAndView mv = new ModelAndView();
			mv.addObject("list", list);
			mv.addObject("prf",prf);
			mv.setViewName("Inbox");
			return mv;
		}
		else
		{
			return new ModelAndView("Menu");
		}	
	}
	
	@RequestMapping("/Outbox")
	public ModelAndView outbox() throws JsonParseException, JsonMappingException, IOException
	{
		List<Message> list = null;
		
		Client client = Client.create();
		String mailId = prf.getMailId();
		System.out.println("Mail Id in Outbox......"+mailId);

		WebResource webResource = client.resource(
				"http://192.168.235.173:8080/MessageService/webapi/profile/outbox?mailId=" + mailId );

		System.out.println("Inside Web res");
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		System.out.println("Status......." + response.getStatus());

		if (response.getStatus() == 200) {
			System.out.println("Inside if");
			String json = response.getEntity(String.class);
			System.out.println("json...."+json);
			list =  new ObjectMapper().readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class,Message.class));
			System.out.println("Contact ......" + list.get(0).getContent());
			ModelAndView mv = new ModelAndView();
			mv.addObject("list", list);
			mv.addObject("prf",prf);
			mv.setViewName("Outbox");
			return mv;
		}
		else
		{
			return new ModelAndView("Menu");
		}
	}
	
	@RequestMapping("/Contacts")
	public ModelAndView contacts() throws JsonParseException, JsonMappingException, IOException
	{
		List<String> list = null;
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		
		HttpGet request = new HttpGet("http://192.168.235.173:8080/MessageService/webapi/profile/contact");
		HttpResponse response = httpClient.execute(request);

		

		if (response.getStatusLine().getStatusCode() == 200) {
			System.out.println("Inside if");
			BufferedReader rd = new BufferedReader(
	                new InputStreamReader(response.getEntity().getContent()));

		String result = new String();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result=result+line;
		}
		System.out.println("line..."+result);
		
			list =  new ObjectMapper().readValue(result, TypeFactory.defaultInstance().constructCollectionType(List.class,String.class));
			System.out.println("Email Id ......" + prf.getMailId());
			ModelAndView mv = new ModelAndView();
			mv.addObject("list", list);
			mv.addObject("prf",prf);
			mv.setViewName("Contacts");
			return mv;
		}
		else
		{
			return new ModelAndView("Menu");
		}
		
	}
	
	
	@RequestMapping("/Logout")
	public ModelAndView logout()
	{
		msg = null;
		prf = null;
		return new ModelAndView("Login");
	}
}
