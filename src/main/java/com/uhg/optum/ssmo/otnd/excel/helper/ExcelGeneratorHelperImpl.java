package com.uhg.optum.ssmo.otnd.excel.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.uhg.optum.ssmo.otnd.entity.PayrollDetails;
import com.uhg.optum.ssmo.otnd.entity.VariableInputReport;

public class ExcelGeneratorHelperImpl {

	public static List<VariableInputReport> consolidate(
			List<PayrollDetails> details, String code) {
		List<VariableInputReport> list = new ArrayList<VariableInputReport>();
		Map<String, VariableInputReport> map = new HashMap<String, VariableInputReport>();
		
		for (PayrollDetails payrollDetail : details) {
			if (code.equals(payrollDetail.getIncomeType().getType())) {
				VariableInputReport inputReport = new VariableInputReport();
				inputReport.setEmpId(payrollDetail.getEmpId().getEmpID());
				inputReport.setEmpName(payrollDetail.getEmpId().getFullName());
				inputReport.setSegment("OPTUM");
				inputReport.setProcess("TECHNOLOGY");
				inputReport.setOtndCode(payrollDetail.getIncomeType().getId());
				inputReport.setHours(payrollDetail.getProdHrsAmt());
				inputReport.setAmount(payrollDetail.getProdHrsAmt());
				inputReport.setRemarks(payrollDetail.getRemarks());
				inputReport.setBusSpocName("Bolivar, Sara Jane");
				String key = payrollDetail.getIncomeType().getId() + payrollDetail.getEmpId().getEmpID();
				VariableInputReport value = map.get(key);
				if (value == null ) {
					map.put(key, inputReport);
				} else {
					BigDecimal origHours = new BigDecimal(value.getHours());
					BigDecimal hoursToAdd = new BigDecimal(payrollDetail.getProdHrsAmt());
					value.setHours(origHours.add(hoursToAdd).toString());
					
					BigDecimal origAmt = new BigDecimal(value.getAmount());
					BigDecimal amtToAdd = new BigDecimal(payrollDetail.getProdHrsAmt());
					value.setAmount(origAmt.add(amtToAdd).toString());
					value.setRemarks(value.getRemarks() +", "+payrollDetail.getRemarks());
				}
				
			}

		}
		
		for(Entry<String, VariableInputReport> entry: map.entrySet()){
			list.add(entry.getValue());
		}
		return list;
	}

}
