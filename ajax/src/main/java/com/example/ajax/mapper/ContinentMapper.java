package com.example.ajax.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContinentMapper {
	List<Map<String, Object>> selectContinentList();
}
