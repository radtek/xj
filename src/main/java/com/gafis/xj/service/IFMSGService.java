package com.gafis.xj.service;

public interface IFMSGService {
	
	String getMsg(String telNumber);
	
	String sendMsg(String telNumber,String msg);
}
