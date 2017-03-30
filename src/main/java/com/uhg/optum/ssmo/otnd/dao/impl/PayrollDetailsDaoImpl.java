package com.uhg.optum.ssmo.otnd.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.uhg.optum.ssmo.otnd.dao.PayrollDetailsDao;
import com.uhg.optum.ssmo.otnd.entity.PayrollDetails;
@Repository
public class PayrollDetailsDaoImpl extends AbstractDao implements
		PayrollDetailsDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<PayrollDetails> getPayrollDetails(PayrollDetails payroll) {
		Criteria criteria = getSession().createCriteria(PayrollDetails.class);
		if(null!=payroll.getEmpId()){
			criteria.add(Restrictions.eq("empId",payroll.getEmpId()));
		}
		if(null!=payroll.getStatus()){
			criteria.add(Restrictions.eq("status",payroll.getStatus()));
		}
		criteria.add(Restrictions.eq("payrollPeriod", payroll.getPayrollPeriod()));
		return (List<PayrollDetails>)criteria.list();
	}

	@Override
	public void deletePayroll(int payrollId) {
		PayrollDetails details = new PayrollDetails();
		details.setId(payrollId);
		getSession().delete(details);
	}

	@Override
	public void savePayrollDetail(PayrollDetails payroll) {
		persist(payroll);
	}

	@Override
	public void approvePayrollDetail(int payrollId, boolean isApproved, String approver) {
		PayrollDetails pd = getPayrollDetail(payrollId);
		pd.setApprover(approver);
		if(isApproved){
			pd.setStatus("approved");
		}else{
			pd.setStatus("rejected");
		}
		
		getSession().update(pd);
	}

	@Override
	public PayrollDetails getPayrollDetail(int payrollId) {
		PayrollDetails detail =  (PayrollDetails) getSession().get(PayrollDetails.class, payrollId);
		return detail;
	}

}
