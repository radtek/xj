package com.gafis.xj.dao;

import com.gafis.xj.model.FUserMsg;

public interface FUserMsgMapper {
	
	 /**
	  * 获取用户验证信息 
	 * @param pkId
	 * @return
	 */
	FUserMsg selectByUserId(String userId);
	
	/**
	 * @param record
	 * @return
	 */
	int insert(FUserMsg record);
	 
	/**
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(FUserMsg record);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	int deleteByPrimaryKey(String userId);
}
