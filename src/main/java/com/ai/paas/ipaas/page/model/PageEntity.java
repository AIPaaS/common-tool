package com.ai.paas.ipaas.page.model;

import java.io.Serializable;
import java.util.Map;

public class PageEntity implements Serializable {
	private static final long serialVersionUID = 4933771255759680537L;
	private int page; // 目前是第几页
	private int size; // 每页大小
	@SuppressWarnings("rawtypes")
	private Map params; // 传入的参数
	private String orderColumn;
	private String orderTurn = "ASC";

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public String getOrderTurn() {
		return orderTurn;
	}

	public void setOrderTurn(String orderTurn) {
		this.orderTurn = orderTurn;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@SuppressWarnings("rawtypes")
	public Map getParams() {
		return params;
	}

	@SuppressWarnings("rawtypes")
	public void setParams(Map params) {
		this.params = params;
	}
}
