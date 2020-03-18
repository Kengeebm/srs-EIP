package com.eip.domain;

import java.io.Serializable;

public class TimeSheetStatusCount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String approveStatus;

	private int count;

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "TimeSheetStatusCount [approveStatus=" + approveStatus + ", count=" + count + "]";
	}

}
