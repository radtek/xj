package com.gafis.xj.service;

import java.util.List;

import com.gafis.xj.model.ProductsInfo;

public interface IProductService {
	
	/**
	 * 获取列表
	 * @return
	 */
	List<ProductsInfo> getList();
	
	/**
	 * 获取详细信息
	 * @param pkId
	 * @return
	 */
	ProductsInfo getProductDetail(String pkId);
	
	/**
	 * 
	 * @param productCode
	 * @return
	 */
	String orderProduct(String userCode,String productCode,String month);
	
}
