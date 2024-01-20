package com.zerozae.harunews.article.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.zerozae.harunews.article.dto.response.ArticleResponse;
import com.zerozae.harunews.article.entity.Article;
import com.zerozae.harunews.article.service.ArticleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/article")
public class ArticleController {

	private static final String SLACK_URL = "https://hooks.slack.com/services/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";

	private final ArticleService articleService;
	private final RestTemplate restTemplate;

	@GetMapping
	public void getNaverNewsByKeyword() {
		List<ArticleResponse> naverNewsByRandomKeyword = articleService.getNaverNewsByRandomKeyword();
		String messageContent = formatNaverApiResponse(naverNewsByRandomKeyword);
		HttpEntity<Map<String, Object>> slackMessageEntity = createSlackMessage(messageContent);
		restTemplate.exchange(SLACK_URL, HttpMethod.POST, slackMessageEntity, String.class);
	}

	private HttpEntity<Map<String, Object>> createSlackMessage(String articles) {
		Map<String, Object> slackMessageMap = new HashMap<>();
		slackMessageMap.put("username", "오늘의 뉴스");

		if (!articles.isEmpty()) {
			slackMessageMap.put("text", articles);
		} else {
			slackMessageMap.put("text", "오늘 뉴스 없음");
		}
		return new HttpEntity<>(slackMessageMap);
	}

	private String formatNaverApiResponse(List<ArticleResponse> articles) {
		if (articles != null && !articles.isEmpty()) {
			StringBuilder result = new StringBuilder();
			result.append("\n오늘의 뉴스 주제 : ")
				.append(articles.get(0).keyword())
				.append("\n");

			for (ArticleResponse articleResponse : articles) {
				result.append(articleResponse);
			}
			return result.toString();
		} else {
			return "뉴스가 없습니다.";
		}
	}
}
