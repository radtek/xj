package com.gafis.xj.dao;

import java.util.List;


import com.gafis.xj.model.ProductsInfo;

public interface ProductsInfoMapper {
	
	List<ProductsInfo> getList();
	
	ProductsInfo getProductDetail(String pkId);
	
	ProductsInfo getProductDetailByCode(String code);
	
}
