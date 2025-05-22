package com.example.ajax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ajax.mapper.CityMapper;
import com.example.ajax.mapper.ContinentMapper;
import com.example.ajax.mapper.CountryMapper;

@Controller
public class CntnntController {
	@Autowired ContinentMapper continentMapper;
	@Autowired CountryMapper countryMapper;
	@Autowired CityMapper cityMapper;
	
	@GetMapping("/cntnntList")
	public String continentList(Model model,
								@RequestParam(required = false) Integer continent,
								@RequestParam(required = false) Integer country) {
		model.addAttribute("continentList", continentMapper.selectContinentList());
		if(continent != null) {
			model.addAttribute("countryList", countryMapper.selectCountryList(continent));
		}
		System.out.println(continent);
		if(country != null) {
			model.addAttribute("cityList", cityMapper.selectCityList(country));
		}
		return "cntnntList";
	}
	
}
