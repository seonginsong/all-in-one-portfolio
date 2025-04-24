package com.example.fileupload.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fileupload.entity.Board;
import com.example.fileupload.entity.BoardMapping;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	List<BoardMapping> findAllBy();
	BoardMapping findByBno(int bno);
	
	@Query(nativeQuery = true
			, value = "select * from board")
	Page<BoardMapping> findAllBoardList(PageRequest pageable);
}
