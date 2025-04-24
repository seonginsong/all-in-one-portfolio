package com.example.fileupload.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fileupload.entity.Boardfile;

public interface BoardfileRepository extends JpaRepository<Boardfile, Integer>{
	List<Boardfile> findByBno(int bno);
	
	// pk 한행삭제
	// void deleteById(int fno) 사용
	
	// fk 여러행 삭제(Board 삭제 시 같이 삭제 : 트랜잭션 처리)
	void deleteByBno(int bno);
	
	// void deleteByBnoAndTitle 등 변수추가 가능
	
	// 파일이 있으면 삭제하지 못하게 하기위한 조건 cnt 구하는 쿼리
	@Query(nativeQuery = true,
			value="select count(*) from boardfile"
					+" where bno = :bno")
	Integer getCountFnoByBno(int bno);
}
