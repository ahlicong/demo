package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
	private static final Logger logger=LoggerFactory.getLogger(Scheduler.class);
	
	@Autowired
	private Dao dao;

	/**
	 * trigger at 0:00:00 everyday
	 */
	@Scheduled(cron="0 0 0 * * ?")
	public void refreshRanks() {
		logger.info("refresh ranks start.");
		dao.refreshRanks();
		logger.info("refresh ranks complete.");
	}

}
