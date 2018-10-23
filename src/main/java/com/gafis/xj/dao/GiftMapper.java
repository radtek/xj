package com.gafis.xj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gafis.xj.model.GiftInfo;
import com.gafis.xj.model.Order;

public interface GiftMapper {
	
	
	
	GiftInfo getGiftInfo(String pkId);
	
}
