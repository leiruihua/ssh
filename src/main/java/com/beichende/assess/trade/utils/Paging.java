package com.beichende.assess.trade.utils;

import java.util.List;

public class Paging<T> {
	private Integer  total;	//总数
	private Integer pageSize = 10;	//分页大小 默认值
	private Integer pageNumber = 1;	//分页数 默认值
	private List<T> list; //对象列表
	private T entity;//对象
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public T getEntity() {
		return entity;
	}
	public void setEntity(T entity) {
		this.entity = entity;
	}	
}
