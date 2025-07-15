package com.example.supa;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NowMapper {
	String selectNow();
}
