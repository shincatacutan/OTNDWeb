package com.uhg.optum.ssmo.otnd.vo;

public class PayrollPeriodVo {
	private String period;
	private String status;
	
	public PayrollPeriodVo() {
		super();
	}
	
	public PayrollPeriodVo(String period, String status) {
		super();
		this.period = period;
		this.status = status;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
