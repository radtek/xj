package com.gafis.xj.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gafis.xj.controller.MsgController;
import com.gafis.xj.dao.OrderMapper;
import com.gafis.xj.dao.ProductsInfoMapper;
import com.gafis.xj.model.Order;
import com.gafis.xj.model.ProductsInfo;
import com.gafis.xj.service.IProductService;
@Service
public class ProductServiceImpl implements IProductService{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductsInfoMapper productsInfoMapper;
	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public List<ProductsInfo> getList() {
		logger.info("获取列表");
		return productsInfoMapper.getList();
	}

	@Override
	public ProductsInfo getProductDetail(String pkId) {
		logger.info("获取详细");
		return productsInfoMapper.getProductDetail(pkId);
	}

	@Transactional
	@Override
	public String orderProduct(String userCode,String productCode,String month){
		  //8001020 订购失败
			if("8001020".equals(productCode)){
				return "订购失败";
			}
		
		 //判断当前用户是否可以订该产品
		 //判断当前产品是否有父产品及互斥产品
		 ProductsInfo productsInfo= productsInfoMapper.getProductDetailByCode(productCode);
		 if(productsInfo != null ){
			 String parentCode=productsInfo.getParentCode();
			 String diffCode=productsInfo.getDiffCode();
			 
			 if(parentCode != null && !"".equals(parentCode)){
				 //存在父产品，判断当前用户是否已订父产品
				 List<Order> order=orderMapper.getListByUserAndProduct(userCode, productCode, month);
				 if(!(order != null && order.size()>0)){
					 //未订父产品
					 logger.info("需订购父产品");
					 return "需订购父产品"+parentCode;
				 } 
			 }
			 
			 if(diffCode != null && !"".equals(diffCode)){
				 String diff[]=diffCode.split(",");
				 for(String dCode:diff){
					 List<Order> order=orderMapper.getListByUserAndProduct(userCode, dCode, month);
					 if(order != null && order.size()>0){
						 //已订购互斥产品
						 logger.info("已订购互斥产品");
						 return "已订购互斥产品"+dCode;
					 } 
				 }
			 }
			 
			 //是否有重复的订单
			 if(productsInfo.getIsRepeat() == 1){
				 List<Order> order=orderMapper.getListByUserAndProduct(userCode, productCode, month);
				 if(order !=null && order.size()>0){
					 logger.info("订单重复");
					 return "订单重复" ;
				 }
			 }
			 
			 SimpleDateFormat sf=new SimpleDateFormat("YYYY-MM");
			 //开始订购产品
			 Order newOrder=new Order();
			 newOrder.setCreateTime(new Date());
			 try {
				newOrder.setOrderTime(sf.parse(month));
			} catch (ParseException e) {
				return "日期选择有误";
			}
			 newOrder.setProductCode(productCode);
			 newOrder.setUserCode(userCode);
			 orderMapper.insert(newOrder);
			 
			 //计算礼品
			 synchronized (ProductServiceImpl.class) {
				 //获取关联礼品信息，保存用户礼品信息，计算礼品剩余量
			 }
			 return "订单预定成功";
		 }
		return "产品信息有误";
	}

	 
	
	
	

}
