package com.qubit.demo.util;

import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Service
//@Component
public class DemoUtils {

//	@Value("${access.token}")
	private static String MY_ACCESS_TOKEN = "EAAWIwF25NmUBAP7W1t5UP9mFQtl2H01BemDZB8wLo80mfoNGtPJ6nhtd0bWrWJ0Cp7TGN7i20FFajLlcqJtZBgzREWlVkIw5Sr8fnES1UfeZAA2caASqKJWeR5ZBIZBeBpEwGRQ8z6Hz7zmKFq8qIZCmGCOD9NZCj0UpZAhV7N2QudPdNJHDBlLYhvgxFWaH8e7jmCE9lAlfAQZDZD";
	
//	@Autowired
//    private static Environment env;
	
	public static final String BASE_URL = "https://graph.facebook.com/v7.0/me?";
	public static final String FIELDS = "fields=";
	public static final String FQL_ME_TAGGED_PLACES = "/me/tagged_places";
	public static final String ACCESS_TOKEN = "access_token=";
//	private static String MY_ACCESS_TOKEN = env.getProperty("access.token");
	
	
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
//			System.out.println(data);
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
			System.out.println(data);
			
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
