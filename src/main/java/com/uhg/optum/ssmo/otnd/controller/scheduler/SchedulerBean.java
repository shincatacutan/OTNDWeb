package com.uhg.optum.ssmo.otnd.controller.scheduler;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("myBean")
public class SchedulerBean {

	@Value("${otnd.folder.path}")
	private String defaultPath;

	private final static Logger logger = LoggerFactory
			.getLogger(SchedulerBean.class);

	public void deleteFiles() throws IOException {
		logger.debug("<< scheduler task invoked >>");
		File path = new File(defaultPath);
		if(path.exists()){
			FileUtils.cleanDirectory(path);
		}
		
	}

}
