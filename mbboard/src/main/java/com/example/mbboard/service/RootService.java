package com.example.mbboard.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mbboard.dto.ConnectCount;
import com.example.mbboard.mapper.ConnectCountMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class RootService implements IRootService{
	@Autowired ConnectCountMapper connectCountMapper;
	@Override
	public Map<String, Integer> getConnectCountAll() {
		return connectCountMapper.selectConnectCountAll();
	}
	@Override 
	public Map<String, Integer> getConnectCountToday() {
		return connectCountMapper.selectConnectCountToday();
	}
	@Override
	public String getConnectDateByKey(ConnectCount cc) {
		return connectCountMapper.selectConnectDateByKey(cc);
	}
	
	@Override
	public int addConnectCount(ConnectCount cc) {
		return connectCountMapper.insertConnectCount(cc);
	}
	
	@Override
	public int modifyConnectCount(ConnectCount cc) {
		return connectCountMapper.updateConnectCount(cc);
	}
}
