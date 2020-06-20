package com.qubit.demo.service;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.qubit.demo.model.Place;
import com.qubit.demo.util.DemoUtils;
import com.qubit.demo.util.Statics;

@Service
public class FacebookService {
	
//	@Autowired
//	UserRepository userRepository;

	/*
	 * This method work for to fetch records from Facebook using facebook graph api
	 */
	public List<Place> fetchFacebookData() throws IOException {
		try {
			
			DemoUtils.getData(Statics.POSTS);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
//		FacebookClient fbClient = new DefaultFacebookClient(MY_ACCESS_TOKEN, Version.LATEST);
//		User user = fbClient.fetchObject("me", User.class);
	}
	
	public List<Place> saveFacebookPostData() throws IOException {
		try {
			
			JSONObject data = DemoUtils.getData(Statics.POSTS);
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
//		FacebookClient fbClient = new DefaultFacebookClient(MY_ACCESS_TOKEN, Version.LATEST);
//		User user = fbClient.fetchObject("me", User.class);
	}

//	public void saveUser(String fbUserId) {
//
//		try {
//
//			JSONObject data = DemoUtils.getData(Statics.POSTS);
//			if(data == null || data.isEmpty() || userRepository.getByIdAndStatus(fbUserId) == null)
//				return;
//			
//			User user = new User();
//			user.setFbUserId(fbUserId);
//			user.setName(data.get("name").toString());
//			user.setNew(true);
//			user.setCreatedDate(new Date());
//			user.setUpdatedDate(new Date());
//			
//			userRepository.save(user);
//			
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}
	
}
