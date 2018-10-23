package com.gafis.xj.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gafis.xj.model.ProductsInfo;
import com.gafis.xj.service.IFMSGService;
import com.gafis.xj.service.IProductService;

@Controller
public class MsgController {
	
	@Autowired
	private IFMSGService fMSGService;
	
	@Autowired
	private IProductService productService;
	 
	private static final Logger logger = LoggerFactory.getLogger(MsgController.class);
	
	 @ResponseBody
	 @RequestMapping("/getMsg")
	 public String getUserMsg(String telNumber){
		 String result=fMSGService.getMsg(telNumber);
		 logger.info(telNumber+"-------获取验证码------结果："+result);
		 return result;
	 }
	 
	 @ResponseBody
	 @RequestMapping("/sendMsg")
	 public String sendMsg(String telNumber,String msg){
		 String result=fMSGService.sendMsg(telNumber,msg);
		 logger.info( telNumber+"-------验证------结果："+result);
		 return result;
	 }
	 
	 @ResponseBody
	 @RequestMapping("/getProductList")
	 public List<ProductsInfo> getProductsList(){
		 List<ProductsInfo> list=productService.getList();
		 return list;
	 }
	 
	 
	 @ResponseBody
	 @RequestMapping("/getProductsDetail")
	 public ProductsInfo  getProductsDetail(String pkId){
		 ProductsInfo detail=productService.getProductDetail(pkId);
		 return detail;
	 }
	 
	 @ResponseBody
	 @RequestMapping("/orderProduct")
	 public String  orderProducts(String userCode,String productCode,String month){
		 return productService.orderProduct(userCode, productCode, month);
	 }
	 
}
