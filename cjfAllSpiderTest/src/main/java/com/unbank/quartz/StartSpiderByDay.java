package com.unbank.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.unbank.offeredRate.BankchaijietaiRateSipder;

@Component
public class StartSpiderByDay {
	private static Log logger = LogFactory.getLog(StartSpiderByDay.class);
	@Autowired
	BankchaijietaiRateSipder bankchaijietaiRateSipder;
	

	/**
	 * 定时启动任务
	 */
	public void executeInternal() {
		try {
			logger.info("二期票据启动任务一天启动一次");
			bankchaijietaiRateSipder.spiderBankchaijietai();
			

		} catch (Exception e) {
			logger.error("启动采集定时任务出错", e);
		}
	}

}
