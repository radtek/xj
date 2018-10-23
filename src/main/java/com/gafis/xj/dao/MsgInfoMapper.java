package com.gafis.xj.dao;

import com.gafis.xj.model.MsgInfo;

public interface MsgInfoMapper {
	
	  int insert(MsgInfo info);
	  
	  int updateByPrimaryKey(MsgInfo info);
}
