package com.gafis.xj.model;

public class ProductsInfo {
	private String pkId;
	private String productCode;
	private String productName;
	private double productPrice;
	private String productDetail;
	private String productRoles;
	private int isRepeat;
	private String parentCode;
	private String diffCode;
	
	public String getPkId() {
		return pkId;
	}
	public void setPkId(String pkId) {
		this.pkId = pkId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	public String getProductRoles() {
		return productRoles;
	}
	public void setProductRoles(String productRoles) {
		this.productRoles = productRoles;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public int getIsRepeat() {
		return isRepeat;
	}
	public void setIsRepeat(int isRepeat) {
		this.isRepeat = isRepeat;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getDiffCode() {
		return diffCode;
	}
	public void setDiffCode(String diffCode) {
		this.diffCode = diffCode;
	}
	
}
