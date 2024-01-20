package com.zerozae.harunews.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zerozae.harunews.article.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
