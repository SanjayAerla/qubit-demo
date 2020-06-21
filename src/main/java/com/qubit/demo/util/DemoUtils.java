package com.qubit.demo.util;

import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
//@Component
public class DemoUtils {

	private static final Logger logger = LoggerFactory.getLogger(DemoUtils.class);
	
	public static String MY_ACCESS_TOKEN;
	
	public static String MY_USER_ID;
	
	@Value("${access.token}")
	public void setAccessToken(String accessToken) {
		DemoUtils.MY_ACCESS_TOKEN = accessToken;
	}
	
	@Value("${fb.user.id}")
	public void setMyUserId(String myUserId) {
		DemoUtils.MY_USER_ID = myUserId;
	}
		
	public static final String BASE_URL = "https://graph.facebook.com/v7.0/me?";
	public static final String FIELDS = "fields=";
	public static final String FQL_ME_TAGGED_PLACES = "/me/tagged_places";
	public static final String ACCESS_TOKEN = "access_token=";
	
	
	public static JSONObject getData(String field) throws Exception {
		JSONObject data = null;
		String fbURL = "";
		JSONParser parser = new JSONParser();
		try {
			
			RestTemplate restTemplate = new RestTemplate();
			if(field == null || field.isEmpty())
				fbURL = BASE_URL + ACCESS_TOKEN + MY_ACCESS_TOKEN;
			else
				fbURL = BASE_URL + FIELDS + field + "&" + ACCESS_TOKEN + MY_ACCESS_TOKEN;
			ResponseEntity<String> response = restTemplate.getForEntity(fbURL, String.class);
			if(response.getStatusCodeValue() != 200)
				throw new Exception("No Data Found");
			
			data = (JSONObject) parser.parse(response.getBody());
			logger.info("Data from Server : " + data);
		}catch(Exception ex) {
			ex.printStackTrace();
//			throw new Exception(ex.getLocalizedMessage());
		}
		return data;
	}
	
	public static JSONObject getPageData(String url) throws Exception {
		JSONObject data = null;
		JSONParser parser = new JSONParser();
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			if(response.getStatusCodeValue() != 200)
				throw new Exception("No Data Found");
			
			data = (JSONObject) parser.parse(response.getBody());
			logger.info("Data from Server : " + data);
			
		}catch(Exception ex) {
			ex.printStackTrace();
//			throw new Exception(ex.getLocalizedMessage());
		}
		return data;
	}
	
	
	public static Map<String, Object> convertJsonStringToMap(String fields) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, Object> fieldData = mapper.readValue(fields, new TypeReference<Map<String, Object>>() {
			});
			return fieldData;
		}
		
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Could not parse the Json String :" + e.getMessage());
		}
		
	}
}
