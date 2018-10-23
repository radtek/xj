package com.gafis.xj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gafis.xj.model.Order;

public interface OrderMapper {
	
	int insert(Order order);
	
	List<Order> getList();
	
	Order getOrderDetail(String pkId);
	
	List<Order> getListByUserAndProduct(@Param("userCode")String userCode,@Param("productCode")String productCode,@Param("month")String month);
}
