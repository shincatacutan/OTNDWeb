package com.uhg.optum.ssmo.otnd.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.uhg.optum.ssmo.otnd.entity.PayrollDetails;
import com.uhg.optum.ssmo.otnd.entity.VariableInputReport;
import com.uhg.optum.ssmo.otnd.excel.helper.ExcelGeneratorHelperImpl;
import com.uhg.ssmo.otnd.excel.decorator.EarningReportSheet;
import com.uhg.ssmo.otnd.excel.decorator.LWOPReportSheet;
import com.uhg.ssmo.otnd.excel.decorator.OTNDReportSheet;
import com.uhg.ssmo.otnd.excel.decorator.ReportSheet;
import com.uhg.ssmo.otnd.excel.decorator.TMAReportSheet;

public class ExcelGenerator {
	public String generate(String otndFolder, List<PayrollDetails> details) {
//		HSSFWorkbook workbook = new HSSFWorkbook();
		XSSFWorkbook workbook = new XSSFWorkbook();
		List<VariableInputReport> otndItems = ExcelGeneratorHelperImpl.consolidate(details, "OT_ND");
		ReportSheet otndSheet = new OTNDReportSheet();
		otndSheet.generate(workbook, otndItems);
		
		List<VariableInputReport> earnItems = ExcelGeneratorHelperImpl.consolidate(details, "EARNINGS");
		ReportSheet earningSheet = new EarningReportSheet();
		earningSheet.generate(workbook, earnItems);

		List<VariableInputReport> tmaItems = ExcelGeneratorHelperImpl.consolidate(details, "TMA");
		ReportSheet tmaSheet = new TMAReportSheet();
		tmaSheet.generate(workbook, tmaItems);
		
		List<VariableInputReport> lwopItems = ExcelGeneratorHelperImpl.consolidate(details, "LWOP");
		ReportSheet lwopSheet = new LWOPReportSheet();
		lwopSheet.generate(workbook, lwopItems);
		
		
		Date date = new Date();
		String timestamp = new Timestamp(date.getTime()).toString()
				.replace(".", "-").replace(":", "-").replace(" ", "_")
				.replace("-", "");
		String fileName = "OTND_" + timestamp+".xlsx";
		String path = otndFolder;
		try {

			File file = new File(path);
			file.mkdirs();
			FileOutputStream out = new FileOutputStream(new File(path + ""
					+ fileName));
			workbook.write(out);
			out.close();
			System.out.println("Excel written successfully..");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
}
