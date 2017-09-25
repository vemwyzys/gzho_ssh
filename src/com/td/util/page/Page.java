package com.td.util.page;

public class Page {
	private int page;
	private int rows;
	private int total; // 总共记录数

	public Page() {
		super();
	}

	public Page(int page, int rows, int total) {
		super();
		this.page = page;
		this.rows = rows;
		this.total = total;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

}
