package com.zerozae.harunews.article.controller;

import static com.zerozae.harunews.formatter.ArticleFormatter.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.zerozae.harunews.article.dto.response.ArticleResponse;
import com.zerozae.harunews.article.service.ArticleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ArticleController {

	private static final String SLACK_URL = "https://hooks.slack.com/services/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";

	private final ArticleService articleService;
	private final RestTemplate restTemplate;

	@Scheduled(cron = "0 45 21 * * ?")
	public void getNaverNewsByKeyword() {
		log.info(">>>>>>> get Naver News By Keyword Start !!!!");
		List<ArticleResponse> naverNewsByRandomKeyword = articleService.getNaverNewsByRandomKeyword();
		String messageContent = formatNaverApiResponse(naverNewsByRandomKeyword);
		HttpEntity<Map<String, Object>> slackMessageEntity = createSlackMessage(messageContent);
		restTemplate.exchange(SLACK_URL, HttpMethod.POST, slackMessageEntity, String.class);
	}

	private HttpEntity<Map<String, Object>> createSlackMessage(String articles) {
		Map<String, Object> slackMessageMap = new HashMap<>();
		slackMessageMap.put("username", "오늘의 뉴스");
		slackMessageMap.put("text", articles);
		return new HttpEntity<>(slackMessageMap);
	}
}
