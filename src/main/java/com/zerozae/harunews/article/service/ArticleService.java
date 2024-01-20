package com.zerozae.harunews.article.service;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.zerozae.harunews.article.dto.response.ArticleResponse;
import com.zerozae.harunews.article.dto.response.NaverApiResponse;
import com.zerozae.harunews.article.entity.Article;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {

	private static final String[] NEWS_CATEGORIES = {"정치", "경제", "사회", "생활/문화", "IT/과학", "세계"};

	private final NaverApiService naverApiService;

	public List<ArticleResponse> getNaverNewsByRandomKeyword() {
		String keyword = getRandomKeyword();
		NaverApiResponse naverNews = naverApiService.getNaverNews(keyword);
		List<Article> articles;

		if (naverNews.getItems() != null) {
			articles = naverNews.getItems();
			return articles.stream()
				.map(article -> ArticleResponse.from(article, keyword))
				.toList();
		} else {
			return Collections.emptyList();
		}
	}

	private String getRandomKeyword() {
		Random random = new Random();
		int randomIndex = random.nextInt(NEWS_CATEGORIES.length);
		return NEWS_CATEGORIES[randomIndex];
	}
}
