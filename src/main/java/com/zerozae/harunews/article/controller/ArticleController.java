package com.zerozae.harunews.article.controller;

import static com.zerozae.harunews.formatter.ArticleFormatter.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
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

	@Value("${slack.url}")
	private String SLACK_URL;

	private final ArticleService articleService;
	private final RestTemplate restTemplate;

	@Scheduled(cron = "0 0 9 * * ?")
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
