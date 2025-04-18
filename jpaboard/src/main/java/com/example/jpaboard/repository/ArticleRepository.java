package com.example.jpaboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpaboard.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{
	// CrudRepository : c(insert), r(select all, select one), u(update), d(delete)
	// JpaRepository(CrudRepository 자식) : c, r, u, d, select limit, select order by...join쿼리나 서브쿼리까지 모두...
}
