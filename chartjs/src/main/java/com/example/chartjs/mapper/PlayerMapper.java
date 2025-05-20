package com.example.chartjs.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlayerMapper {
	List<Map<String, Object>> selectAvgAgeByCountry();
	
	List<Map<String, Object>> selectCountByGender();

	List<Map<String, Object>> selectCountByYearAndCountry();
	
	List<Map<String, Object>> selectTotalCountByYear();
}
