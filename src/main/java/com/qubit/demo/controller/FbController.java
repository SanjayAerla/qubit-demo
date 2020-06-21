package com.qubit.demo.controller;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qubit.demo.service.FacebookService;

@RestController
public class FbController {

	@Autowired
	FacebookService facebookService;
	
	@GetMapping("/facebookData")
	public JSONObject getPlaces() throws IOException {
		return facebookService.fetchFacebookData();
	}
}
