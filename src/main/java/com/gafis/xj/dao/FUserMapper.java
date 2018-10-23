package com.gafis.xj.dao;

import com.gafis.xj.model.FUser;

public interface FUserMapper {
	
	 /**
	  * 获取用户信息 
	 * @param pkId
	 * @return
	 */
	FUser selectByTelNumber(String pkId);
}
