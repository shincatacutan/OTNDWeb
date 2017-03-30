package com.uhg.ssmo.otnd.excel.decorator;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.uhg.optum.ssmo.otnd.entity.VariableInputReport;

public class OTNDReportSheet implements ReportSheet {

	@Override
	public void generate(XSSFWorkbook workbook, List<VariableInputReport> items) {
		XSSFSheet otnd = workbook.createSheet("OT_ND");
		int rownum = 0;
		Row header = otnd.createRow(rownum++);
		String headers[] = { "EMPLOYEE ID#", "EMPLOYEE NAME", "SEGMENT",
				"PROCESS", "OT_ND CODE", "BILLABLE", "NON-BILLABLE", "HOURS", "REMARKS",
				"REASON FOR NON-PROD HOURS", "BUSINESS SPOC NAME" };
		int headerCtr = 0;
		headerCtr = ReportSheetUtils.createHeader(workbook,header, headers, headerCtr);
		
		for (VariableInputReport item : items) {
			int cellCtr = 0;
			Row row = otnd.createRow(rownum++);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getEmpId(), cellCtr++, false);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getEmpName(), cellCtr++, false);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getSegment(), cellCtr++, false);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getProcess(), cellCtr++, false);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getOtndCode(), cellCtr++, false);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getBillable(), cellCtr++, false);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getNonBillable(), cellCtr++, false);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getHours(), cellCtr++, false);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getRemarks(), cellCtr++, true);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getNonProdReason(), cellCtr++, false);
			ReportSheetUtils.createRow(workbook,row, rownum, item.getBusSpocName(), cellCtr++, false);
		}
		otnd.setColumnWidth(8, 150);
		ReportSheetUtils.autoSizeWidth(otnd, headers);
	}

	
}
