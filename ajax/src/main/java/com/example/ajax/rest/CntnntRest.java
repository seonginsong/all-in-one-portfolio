package com.example.ajax.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.ajax.mapper.CityMapper;
import com.example.ajax.mapper.CountryMapper;

@RestController
public class CntnntRest {
	@Autowired CountryMapper countryMapper;
	@Autowired CityMapper cityMapper;
	
	@GetMapping("/cntList/{continentNo}")
	public List<Map<String, Object>> cntList(@PathVariable(value = "continentNo") int continentNo) {
		return countryMapper.selectCountryList(continentNo);
	}
	
	@GetMapping("/ctyList/{countryNo}")
	public List<Map<String, Object>> ctyList(@PathVariable(value = "countryNo") int countryNo) {
		return cityMapper.selectCityList(countryNo);
	}
}
