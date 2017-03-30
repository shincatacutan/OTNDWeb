package com.uhg.optum.ssmo.otnd.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.uhg.optum.ssmo.otnd.dao.PayrollPeriodDao;
import com.uhg.optum.ssmo.otnd.entity.PayrollPeriod;

@Repository
public class PayrollPeriodDaoImpl extends AbstractDao implements
		PayrollPeriodDao {

	@Override
	public PayrollPeriod getPayroll(PayrollPeriod pp) {
		Criteria criteria = getSession().createCriteria(PayrollPeriod.class);
		criteria.add(Restrictions.eq("period", pp.getPeriod()));
		return (PayrollPeriod) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PayrollPeriod> getPayrolls(String status) {
		Criteria criteria = getSession().createCriteria(PayrollPeriod.class,"pp");
		criteria.add(Restrictions.eq("status", status)).addOrder(Order.desc("pp.period"));
		return (List<PayrollPeriod>) criteria.list();
	}

	@Override
	public void updatePayrollStatus(PayrollPeriod pp) {
		Criteria criteria = getSession().createCriteria(PayrollPeriod.class);
		criteria.add(Restrictions.eq("period", pp.getPeriod()));
		PayrollPeriod period = (PayrollPeriod) criteria.uniqueResult();
		period.setStatus("Closed");
		getSession().update(period);
	}

	@Override
	public void deletePayrollPeriod(PayrollPeriod pp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPayroll(PayrollPeriod pp) {
		persist(pp);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PayrollPeriod> getAllPeriods() {
		Criteria criteria = getSession().createCriteria(PayrollPeriod.class,"pp");
		return (List<PayrollPeriod>) criteria.addOrder(Order.desc("pp.period")).list();
	}

}
