package com.cczu.util.common;

import java.util.List;

public class Page {
	private long totalRecords;//总记录数   从数据库查询
	private long pageSize=10;//每页显示多少条
	private long totalPages;//总页数    计算出来
	private long currentPage;//当前页码
	private List list; //每页存放的数据   从数据库查询
	private long startIndex;//每页开始查询的索引 计算出来

	public Page(long currentPage,long totalRecords){
		this.currentPage = currentPage;
		this.totalRecords = totalRecords;
		this.totalPages = (totalRecords%pageSize==0?  totalRecords/pageSize : totalRecords/pageSize+1);
		this.startIndex = (currentPage-1)*pageSize;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public long getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(long startIndex) {
		this.startIndex = startIndex;
	}
}
