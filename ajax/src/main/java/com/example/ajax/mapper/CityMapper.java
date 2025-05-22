package com.example.ajax.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CityMapper {
	List<Map<String, Object>> selectCityList(@Param("countryNo") int countryNo);

}
