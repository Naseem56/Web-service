package com.messenger.service;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter
{
  @Override
  public void filter(ContainerRequestContext creq, ContainerResponseContext cres) throws IOException 
  {
	  cres.getHeaders().add("Access-Control-Allow-Origin", "*");
  }
}
