package com.example.jpaboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpaboard.entity.Member;
import com.example.jpaboard.entity.MemberMapping;


public interface MemberRepository extends JpaRepository<Member, Integer>{
	// 아이디 중복 검사
	boolean existsByMemberId(String memberId);
	// 로그인하는 추상메서드 : findby엔티티컬럼필드 ... And 엔티티컬럼필드
	MemberMapping findByMemberIdAndMemberPw(String memberId, String memberPw);
	
	Member findByMemberId(String memberId);
	Page<MemberMapping> findByMemberIdContaining(String memberId, PageRequest pageable);
}
