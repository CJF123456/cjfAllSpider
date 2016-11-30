package com.unbank.quartz;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.unbank.tools.SimpleTools;

public class StartBhiQuartzJobBeanQuartzJobBean {
	private static Log logger = LogFactory
			.getLog(StartBhiQuartzJobBeanQuartzJobBean.class);

	public static void main(String[] args) {
		new StartBhiQuartzJobBeanQuartzJobBean().executeInternal();
	}

	public void executeInternal() {
		try {
			logger.info("重新启动定时任务");
			
		} catch (Exception e) {
			logger.info("检测内容节点出错", e);
		}
	}

}
