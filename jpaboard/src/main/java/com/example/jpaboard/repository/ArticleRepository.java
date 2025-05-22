package com.example.jpaboard.repository;


import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jpaboard.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{
	// CrudRepository : c(insert), r(select all, select one), u(update), d(delete)
	// JpaRepository(CrudRepository 자식) : c, r, u, d, select limit, select order by...join쿼리나 서브쿼리까지 모두...
	

	
	Page<Article> findByTitleContaining(PageRequest pageable, String word);
	
	@Query(nativeQuery = true, 
			value="select * from article"
					+ " where id=:id"
					+ " and title=:title")
	Article findByIdAndTitle1(long id, String title);
	
	@Query(nativeQuery = true, 
			value="select * from article"
					+ " where content=:#{#article.content}"
					+ " and title=:#{#article.title}")
	Article findByContentAndTitle2(@Param(value="article") Article article);
	
	@Query(nativeQuery = true,
			value="select min(id) minId, max(id) maxId, count(*) cnt"
					+ " from article"
					+ " where title like :word")
	Map<String, Object> getMinMaxCountByTitle(String word);
}
