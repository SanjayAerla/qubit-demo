package com.qubit.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qubit.demo.service.DemoSchedulerService;

@Component
public class DemoScheduler {

	@Autowired
	DemoSchedulerService service;

//	@Scheduled(fixedDelay = 10000)
//	public void saveUserFBPostsData() throws Exception {
//		service.savePosts();
//
//	}
	
	@Scheduled(fixedDelay = 10000)
	public void saveUserFBLikesData() throws Exception {
		service.saveFBLikes();

	}
}
