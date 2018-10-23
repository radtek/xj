package com.gafis.xj.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gafis.xj.controller.MsgController;
import com.gafis.xj.dao.FUserMapper;
import com.gafis.xj.dao.FUserMsgMapper;
import com.gafis.xj.model.FUser;
import com.gafis.xj.model.FUserMsg;
import com.gafis.xj.service.IFMSGService;

@Service
public class FMSGServiceImpl implements IFMSGService{

	@Autowired
	private FUserMapper fUserMapper;
	@Autowired
	private FUserMsgMapper fUserMsgMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(FMSGServiceImpl.class);
	
	@Override
	@Transactional
	public String getMsg(String telNumber) {
		FUser fUser=fUserMapper.selectByTelNumber(telNumber);
		if(fUser != null){
			//用户存在，发送短信
			String msg=fUser.getTelNumber();
			if(msg!=null){
				// 用户验证信息
				msg=msg.substring(msg.length()-6,msg.length());
				FUserMsg fUserMsg=fUserMsgMapper.selectByUserId(fUser.getUserId()+"");
				if(fUserMsg == null){
					fUserMsg=new FUserMsg();
					fUserMsg.setCreateTime(new Date());
					fUserMsg.setMassage(msg);
					fUserMsg.setTelNumber(fUser.getTelNumber());
					fUserMsg.setUserId(fUser.getUserId());
					fUserMsgMapper.insert(fUserMsg);
				}else{
					fUserMsg.setCreateTime(new Date());
					fUserMsg.setMassage(msg);
					fUserMsgMapper.updateByPrimaryKey(fUserMsg);
				}
				
				//调用发送短信接口
				
				return "发送的短信信息："+msg;
			}
			
			return "用户手机号不存在" ;
		}else{
			//用户不存在，返回发送失败
			return "用户不存在，请输入正确手机号码";
		}
		
	}

	@Override
	public String sendMsg(String telNumber, String msg) {
		FUser fUser=fUserMapper.selectByTelNumber(telNumber);
		if(fUser != null){
			FUserMsg fUserMsg=fUserMsgMapper.selectByUserId(fUser.getUserId()+"");
			if(fUserMsg!=null&&fUserMsg.getMassage().equals(msg)){
				logger.info("验证成功");
				return "验证成功";
			}else{
				//重新发送验证码
				if(fUserMsg == null){
					fUserMsg=new FUserMsg();
					fUserMsg.setCreateTime(new Date());
					fUserMsg.setMassage(msg);
					fUserMsg.setTelNumber(fUser.getTelNumber());
					fUserMsg.setUserId(fUser.getUserId());
					fUserMsgMapper.insert(fUserMsg);
				}else{
					fUserMsg.setCreateTime(new Date());
					fUserMsg.setMassage("短信验证码为:"+msg);
					fUserMsgMapper.updateByPrimaryKey(fUserMsg);
				}
				
				//调用发送短信接口
				logger.info("验证失败");
				return "验证失败" ;
			}
				
		}
		return "认证失败";
	}
	
	
}
