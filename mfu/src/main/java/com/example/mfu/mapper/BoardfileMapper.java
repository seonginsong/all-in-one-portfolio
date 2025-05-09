package com.example.mfu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.mfu.dto.Boardfile;

@Mapper
public interface BoardfileMapper {
	Integer insertBoardfile(Boardfile boardfile);
	public List<Boardfile> selectFileList(int boardNo);

}
