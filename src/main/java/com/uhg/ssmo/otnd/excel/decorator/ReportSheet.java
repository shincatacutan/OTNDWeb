package com.uhg.ssmo.otnd.excel.decorator;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.uhg.optum.ssmo.otnd.entity.VariableInputReport;

public interface ReportSheet {
	public void generate(XSSFWorkbook workbook, List<VariableInputReport> items	);
}
