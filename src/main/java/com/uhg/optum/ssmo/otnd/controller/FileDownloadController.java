package com.uhg.optum.ssmo.otnd.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uhg.optum.ssmo.otnd.entity.PayrollDetails;
import com.uhg.optum.ssmo.otnd.entity.PayrollPeriod;
import com.uhg.optum.ssmo.otnd.io.ExcelGenerator;
import com.uhg.optum.ssmo.otnd.service.PayrollDetailsService;
import com.uhg.optum.ssmo.otnd.service.PayrollPeriodService;

@Controller
@RequestMapping("/download.do")
public class FileDownloadController {
	@Autowired
	private ServletContext context;

	@Value("${otnd.folder.path}")
	private String defaultPath;

	@Autowired
	private PayrollDetailsService detailsService;

	@Autowired
	private PayrollPeriodService periodService;

	private final static Logger logger = LoggerFactory
			.getLogger(FileDownloadController.class);

	private static final int BUFFER_SIZE = 4096;

	@RequestMapping(method = RequestMethod.GET)
	public void doDownload(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String payPeriod)
			throws IOException {
		logger.debug("[generateExcel] payPeriod: " + payPeriod);

		String[] localDate = payPeriod.split("-");
		PayrollPeriod pp = periodService
				.getPayroll(new PayrollPeriod(new LocalDate(Integer
						.parseInt(localDate[0]),
						Integer.parseInt(localDate[1]), Integer
								.parseInt(localDate[2]))));
		PayrollDetails detail = new PayrollDetails();
		detail.setPayrollPeriod(pp);
		detail.setEmpId(null);
		detail.setStatus("approved");

		List<PayrollDetails> details = detailsService.getPayrollDetails(detail);

		String fileName = new ExcelGenerator().generate(defaultPath, details);
		logger.debug("[generateExcel] generated fileName: " + fileName);
		String fullPath = defaultPath + fileName;

		File downloadFile = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(downloadFile);

		// get MIME type of the file
		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type: " + mimeType);

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				downloadFile.getName());
		response.setHeader(headerKey, headerValue);

		// get output stream of the response
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();

	}
}