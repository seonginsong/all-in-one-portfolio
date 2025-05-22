package com.example.snapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class SnRest {
	@Autowired SnMapper snMapper;
	
	@GetMapping("/isSn/{sn}")
	public boolean isSn(@PathVariable String sn) {
		return snMapper.selectSn(sn) == null ? false : true;
	}
}
