package com.zerozae.harunews.article.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zerozae.harunews.article.dto.request.ArticleRequest;
import com.zerozae.harunews.article.dto.response.ArticleResponse;
import com.zerozae.harunews.article.entity.Article;
import com.zerozae.harunews.article.repository.ArticleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

	private final ArticleRepository articleRepository;

	public List<ArticleResponse> getArticlesByChannel(ArticleRequest request) {
		List<Article> articles = articleRepository.findAllByChannel(request.channel());

		Collections.shuffle(articles);
		int numberOfArticles = Math.min(articles.size(), 3);
		List<Article> randomArticles = articles.subList(0, numberOfArticles);

		return randomArticles.stream().map(ArticleResponse::from).toList();
	}
}
