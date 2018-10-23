package com.gafis.xj.webservice;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService( name="CheckService" ,targetNamespace="http://webservice.xj.gafis.com")
public interface ICheckService {

	@WebMethod
	@WebResult(name = "String", targetNamespace = "")
	public String check(String data);
	
}
