package com.uhg.optum.ssmo.otnd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uhg.optum.ssmo.otnd.dao.PayrollPeriodDao;
import com.uhg.optum.ssmo.otnd.entity.PayrollPeriod;
import com.uhg.optum.ssmo.otnd.service.PayrollPeriodService;

@Service
@Transactional
public class PayrollPeriodServiceImpl implements PayrollPeriodService {

	@Autowired
	private PayrollPeriodDao periodDao;

	@Override
	public PayrollPeriod getPayroll(PayrollPeriod pp) {
		// TODO Auto-generated method stub
		return periodDao.getPayroll(pp);
	}

	@Override
	public List<PayrollPeriod> getPayrolls(String status) {
		return periodDao.getPayrolls(status);
	}

	@Override
	public void updatePayrollStatus(PayrollPeriod pp) {
		periodDao.updatePayrollStatus(pp);
	}

	@Override
	public void deletePayrollPeriod(PayrollPeriod pp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPayroll(PayrollPeriod pp) {
		periodDao.addPayroll(pp);
	}

	@Override
	public List<PayrollPeriod> getAllPeriods() {
		return periodDao.getAllPeriods();
	}

}
