package com.zerozae.harunews.article.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zerozae.harunews.article.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	List<Article> findAllByChannel(String channel);
}
