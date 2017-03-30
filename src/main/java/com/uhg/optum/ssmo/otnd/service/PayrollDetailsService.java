package com.uhg.optum.ssmo.otnd.service;

import java.util.List;

import com.uhg.optum.ssmo.otnd.entity.PayrollDetails;

public interface PayrollDetailsService {
	public List<PayrollDetails> getPayrollDetails(PayrollDetails payroll);

	public void deletePayroll(int payrollId);

	public void savePayrollDetail(PayrollDetails payroll);

	public void approvePayrollDetail(int payrollId, boolean b, String approver);

}
