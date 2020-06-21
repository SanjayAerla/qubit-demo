package com.qubit.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qubit.demo.service.DemoSchedulerService;

@Component
public class DemoScheduler {

	private static final Logger logger = LoggerFactory.getLogger(DemoScheduler.class);
	
	@Autowired
	DemoSchedulerService service;

	/*
	 * This is Scheduled to save Facebook Posts Data of a User 
	 * This will execute for every 1 minute
	 */
	@Scheduled(fixedDelay = 1 * 60 * 1000) 
	public void saveUserFBPostsData() throws Exception {
		service.savePosts();

	}
	
	/*
	 * This is Scheduled to save Facebook Likes Data of a User 
	 * This will execute for every 2minutes
	 */
	@Scheduled(fixedDelay = 2 * 60 * 1000)
	public void saveUserFBLikesData() throws Exception {
		service.saveFBLikes();

	}
}
