package com.messenger.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.messenger.bean.Profile;

@Controller
public class SignupController 
{
	Profile prf = null;
	
	@RequestMapping("/Signup")
	public ModelAndView showForm()
	{
		return new ModelAndView("Login");
	}
	@RequestMapping("/Signup2")
	public ModelAndView signup(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException
	{
		RequestDispatcher disp = req.getRequestDispatcher("http://192.168.235.173:8080/MessageService/webapi/profile/signup");
		disp.forward(req, resp);
		return null;
	}
}
