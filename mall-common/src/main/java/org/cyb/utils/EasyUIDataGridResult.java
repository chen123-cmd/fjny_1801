package org.cyb.utils;

import java.util.List;

/**
 * 
 * @author cyb
 *easyUIGrid 要求的格式
 */

public class EasyUIDataGridResult {
	private long total;
	private List<?> rows;
	
	public EasyUIDataGridResult() {
		
	}
	
	public EasyUIDataGridResult(long total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
}
