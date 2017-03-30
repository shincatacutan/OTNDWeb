package com.uhg.optum.ssmo.otnd.dao;

import java.util.List;

import com.uhg.optum.ssmo.otnd.entity.PayrollDetails;

public interface PayrollDetailsDao {
	public List<PayrollDetails> getPayrollDetails(PayrollDetails payroll);
	public void deletePayroll(int payrollId);
	public void savePayrollDetail(PayrollDetails payroll);
	public void approvePayrollDetail(int payrollId, boolean isApproved, String approver);
	public PayrollDetails getPayrollDetail(int payrollId);

}
