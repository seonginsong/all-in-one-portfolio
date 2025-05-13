package com.example.snapi;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SnMapper {
	@Select("SELECT sn FROM tsn WHERE sn=#{sn}")
	public String selectSn(String sn);
}
