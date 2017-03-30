package com.uhg.optum.ssmo.otnd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uhg.optum.ssmo.otnd.dao.PayrollDetailsDao;
import com.uhg.optum.ssmo.otnd.entity.PayrollDetails;
import com.uhg.optum.ssmo.otnd.service.PayrollDetailsService;
@Service
@Transactional
public class PayrollDetailsServiceImpl implements PayrollDetailsService {

	@Autowired
	PayrollDetailsDao payrollDetailsDao; 
	
	@Override
	public List<PayrollDetails> getPayrollDetails(PayrollDetails payroll) {
		return payrollDetailsDao.getPayrollDetails(payroll);
	}

	@Override
	public void deletePayroll(int payrollId) {
		payrollDetailsDao.deletePayroll(payrollId);
	}

	@Override
	public void savePayrollDetail(PayrollDetails payroll) {
		payroll.setStatus("pending");
		payrollDetailsDao.savePayrollDetail(payroll);
	}

	@Override
	public void approvePayrollDetail(int payrollId,boolean isApproved, String approver) {
		payrollDetailsDao.approvePayrollDetail(payrollId, isApproved, approver);
	}

	
}
