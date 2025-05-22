package com.example.mbboard.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.mbboard.dto.ConnectCount;

@Mapper
public interface ConnectCountMapper {
	// 접속자수 
	Map<String, Integer> selectConnectCountAll();
	Map<String, Integer> selectConnectCountToday();
	
	//오늘 날짜의 카운팅이 있는지 없는지
	String selectConnectDateByKey(ConnectCount cc); // cc.getMemberRole()만 필요
	
	// selectConnectDateByKey의 반환값이 없다면
	int insertConnectCount(ConnectCount cc);
	
	// 반환값이 있다면
	int updateConnectCount(ConnectCount cc);
}
