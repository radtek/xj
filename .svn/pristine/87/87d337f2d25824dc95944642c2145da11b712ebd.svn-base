package com.gafis.xj.util;

@SuppressWarnings("serial")
public class Pagination implements java.io.Serializable{
	
	public Pagination(){
		super();
	}
			
	
	public Pagination(Integer page, Integer limit) {
		this.page = page;
		this.limit = limit;
		this.start = limit*(page-1);
	}
	
	private Integer page; //页码
	private Integer start; //开始
	private Integer limit; //每页显示条数
	private Integer total = 0; //总记录数
	@SuppressWarnings("unused")
	private Integer pages; //总页数
	//排序
	private String order;
	private String sort;
	
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPages() {
		if(null != limit && limit != 0 && null != total)
			return total/limit+1;
		return 0;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}

}
