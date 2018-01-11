package com.ai.paas.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 分页参数对象
 * 
 * @author douxiaofeng
 *
 * @param <T>
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Page implements Serializable {
	private static final long serialVersionUID = 4933771255759680537L;
	/**
	 * 要取得第几页，从1开始
	 */
	private int pageNo = 1;
	/*
	 * 每页大小
	 */
	private int pageSize = 10;
	/**
	 * 总条数
	 */
	private long totleCnt = 0;
	/**
	 * 总页数
	 */
	private long pageCnt;

	/**
	 * 开始行数
	 */
	private long startRow;
	/*
	 * 结束行数
	 */
	private long endRow;

	public Page() {

	}

	public Page(Page page) {
		this.pageNo = page.getPageNo();
		this.pageSize = page.getPageNo();
		this.startRow = (this.pageNo - 1) * this.pageSize;
		this.endRow = this.pageNo * this.pageSize;
	}

	public Page(int pageNo, int pageSize) {
		if (pageNo > 0)
			this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.startRow = (this.pageNo - 1) * this.pageSize;
		this.endRow = this.pageNo * this.pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotleCnt() {
		return totleCnt;
	}

	public void setTotleCnt(long totleCnt) {
		this.totleCnt = totleCnt;
	}

	public long getPageCnt() {
		return pageCnt;
	}

	public void setPageCnt(long pageCnt) {
		this.pageCnt = pageCnt;
	}

	public long getStartRow() {
		return startRow;
	}

	public void setStartRow(long startRow) {
		this.startRow = startRow;
	}

	public long getEndRow() {
		return endRow;
	}

	public void setEndRow(long endRow) {
		this.endRow = endRow;
	}

}
