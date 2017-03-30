package com.uhg.ssmo.otnd.excel.decorator;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.uhg.optum.ssmo.otnd.entity.VariableInputReport;

public class EarningReportSheet implements ReportSheet {

	@Override
	public void generate(XSSFWorkbook workbook, List<VariableInputReport> items) {
		XSSFSheet otnd = workbook.createSheet("EARNINGS");
		int rownum = 0;
		Row header = otnd.createRow(rownum++);
		String headers[] = { "EMPLOYEE ID#", "EMPLOYEE NAME", "SEGMENT",
				"PROCESS", "INCOME CODE", "AMOUNT", "REMARKS",
				"BUSINESS SPOC NAME" };
		int headerCtr = 0;
		
		headerCtr = ReportSheetUtils.createHeader(workbook, header, headers,
				headerCtr);

		for (VariableInputReport item : items) {
			int cellCtr = 0;
			Row row = otnd.createRow(rownum++);
			ReportSheetUtils.createRow(workbook, row, rownum, item.getEmpId(),
					cellCtr++, false);
			ReportSheetUtils.createRow(workbook, row, rownum,
					item.getEmpName(), cellCtr++, false);
			ReportSheetUtils.createRow(workbook, row, rownum,
					item.getSegment(), cellCtr++, false);
			ReportSheetUtils.createRow(workbook, row, rownum,
					item.getProcess(), cellCtr++, false);
			ReportSheetUtils.createRow(workbook, row, rownum,
					item.getOtndCode(), cellCtr++, false);
			ReportSheetUtils.createRow(workbook, row, rownum, item.getAmount(),
					cellCtr++, false);
			ReportSheetUtils.createRow(workbook, row, rownum,
					item.getRemarks(), cellCtr++, true);
			ReportSheetUtils.createRow(workbook, row, rownum,
					item.getBusSpocName(), cellCtr++, false);
		}
		otnd.setColumnWidth(6, 150);
		ReportSheetUtils.autoSizeWidth(otnd, headers);

	}

}
