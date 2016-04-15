package com.ai.paas.ipaas.page.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PagingResult<T> implements Serializable {
	private static final long serialVersionUID = 381081797378780646L;
	// 当前页
	private int currentPage;
	// 总共记录条数
	private int totalSize;

	private int size;

	// 结果集
	private List<T> resultList = new ArrayList<T>();

	@SuppressWarnings("unused")
	private int totalPages;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public int getTotalPages() {
		return (int) Math.ceil(getTotalSize() / getSize());
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
