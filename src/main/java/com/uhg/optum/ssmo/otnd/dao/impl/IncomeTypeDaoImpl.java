package com.uhg.optum.ssmo.otnd.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.uhg.optum.ssmo.otnd.dao.IncomeTypeDao;
import com.uhg.optum.ssmo.otnd.entity.IncomeType;

@Repository
public class IncomeTypeDaoImpl extends AbstractDao implements IncomeTypeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<IncomeType> getAllIncomeTypes() {
//		Criteria criteria = getSession().createCriteria(IncomeType.class)
//				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		Query distinctType = getSession().createQuery("SELECT DISTINCT type FROM IncomeType");
		return (List<IncomeType>) distinctType.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IncomeType> getCodesByTypes(String type) {
		Criteria criteria = getSession().createCriteria(IncomeType.class);
		criteria.add(Restrictions.eq("type",type));
		return (List<IncomeType>)criteria.list();
	}

}
