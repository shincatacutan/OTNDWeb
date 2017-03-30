package com.uhg.optum.ssmo.otnd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uhg.optum.ssmo.otnd.dao.IncomeTypeDao;
import com.uhg.optum.ssmo.otnd.entity.IncomeType;
import com.uhg.optum.ssmo.otnd.service.IncomeTypeService;
@Service
@Transactional
public class IncomeTypeServiceImpl implements IncomeTypeService {

	@Autowired
	private IncomeTypeDao incomeTypeDao;
	
	@Override
	public List<IncomeType> getAllIncomeTypes() {
		List<IncomeType> types = incomeTypeDao.getAllIncomeTypes();
		return types;
	}

	@Override
	public List<IncomeType> getCodesByTypes(String type) {
		return incomeTypeDao.getCodesByTypes(type);
	}

}
