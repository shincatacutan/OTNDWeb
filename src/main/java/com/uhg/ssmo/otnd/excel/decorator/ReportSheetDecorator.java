package com.uhg.ssmo.otnd.excel.decorator;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.uhg.optum.ssmo.otnd.entity.VariableInputReport;

public class ReportSheetDecorator implements ReportSheet {

	protected ReportSheet sheetTobeDecorated;
	
	public ReportSheetDecorator(ReportSheet sheetTobeDecorated) {
		this.sheetTobeDecorated = sheetTobeDecorated;
	}


	@Override
	public void generate(XSSFWorkbook workbook, List<VariableInputReport> items) {
		// TODO Auto-generated method stub
		sheetTobeDecorated.generate(workbook, items);
	}

}
