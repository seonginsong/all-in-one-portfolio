package com.example.mbboard.service;

import java.util.Map;

import com.example.mbboard.dto.ConnectCount;

public interface IRootService {
	Map<String, Integer> getConnectCountAll();
	Map<String, Integer> getConnectCountToday();
	
	// 데이터가 있는지 없는지
	String getConnectDateByKey(ConnectCount cc);
	// 없으면
	int addConnectCount(ConnectCount cc);
	// 있으면
	int modifyConnectCount(ConnectCount cc);
}
