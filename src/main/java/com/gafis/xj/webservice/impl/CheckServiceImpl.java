package com.gafis.xj.webservice.impl;

import javax.jws.WebService;

import com.gafis.xj.webservice.ICheckService;

@WebService( name="CheckService" ,targetNamespace="http://webservice.xj.gafis.com" ,
			endpointInterface ="com.gafis.xj.webservice.ICheckService"
			)
public class CheckServiceImpl implements ICheckService{

	@Override
	public String check(String data) {
		System.out.println("----"+data);
		return "收到数据："+data;
	}

}
