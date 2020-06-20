package com.qubit.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qubit.demo.model.Place;
import com.qubit.demo.service.FacebookService;

@RestController
public class FbController {

	@Autowired
	FacebookService facebookService;
	
	private static List<Place> places;
	
	@GetMapping("/facebookData")
	public List<Place> getPlaces() throws IOException {
		return places = facebookService.fetchFacebookData();
	}

	@RequestMapping("/searchByName")
	public List<Place> getitem(@RequestParam("name") String name) throws IOException {
		return places.stream()
				.filter(place -> place.getName() != null && place.getName().toLowerCase().contains(name.toLowerCase()))
				.collect(Collectors.toList());
	}

	@RequestMapping("/searchByCountry")
	public List<Place> getPlacesByCountry(@RequestParam("countryName") String countryName) throws IOException {
		return places.stream()
				.filter(place -> place.getCountry() != null
						&& place.getCountry().toLowerCase().contains(countryName.toLowerCase()))
				.collect(Collectors.toList());
	}

	@RequestMapping("/searchByCity")
	public List<Place> getPlacesByCity(@RequestParam("cityName") String cityName) throws IOException {
		return places.stream().filter(
				place -> place.getCity() != null && place.getCity().toLowerCase().contains(cityName.toLowerCase()))
				.collect(Collectors.toList());
	}
}
