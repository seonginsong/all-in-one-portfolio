package com.example.mbboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.mbboard.dto.Board;
import com.example.mbboard.dto.Page;

@Mapper // ->구현 (component 구현클래스를 자동으로 생성 -> ex)@Component BoardMapper_class -> 객체생성 been 등록 
public interface BoardMapper {
	int insertBoard(Board b);
	int updateBoard(Board b);
	int deleteBoardByKey(Board b);
	List<Board> selectBoardListByPage(Page p);
	Board selectBoardOne(Board b);
	int selectCnt(@Param("searchWord") String searchWord);
}
