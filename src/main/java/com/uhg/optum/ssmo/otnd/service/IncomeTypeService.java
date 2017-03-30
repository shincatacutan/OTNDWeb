package com.uhg.optum.ssmo.otnd.service;

import java.util.List;

import com.uhg.optum.ssmo.otnd.entity.IncomeType;

public interface IncomeTypeService {
	public List<IncomeType> getAllIncomeTypes();
	public List<IncomeType> getCodesByTypes(String type);
}
