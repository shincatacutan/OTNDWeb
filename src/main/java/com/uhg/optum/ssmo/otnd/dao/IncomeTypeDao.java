package com.uhg.optum.ssmo.otnd.dao;

import java.util.List;

import com.uhg.optum.ssmo.otnd.entity.IncomeType;

public interface IncomeTypeDao {
	public List<IncomeType> getAllIncomeTypes();
	public List<IncomeType> getCodesByTypes(String type);
}
