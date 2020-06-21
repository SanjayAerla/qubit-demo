package com.qubit.demo.service;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.qubit.demo.util.DemoUtils;

@Service
public class FacebookService {
	
	/*
	 * This method work for to fetch records from Facebook using facebook graph api
	 */
	public JSONObject fetchFacebookData() throws IOException {
		try {
			
			return DemoUtils.getData("");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
}
