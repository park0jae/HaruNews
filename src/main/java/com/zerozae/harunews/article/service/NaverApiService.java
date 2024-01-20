package com.zerozae.harunews.article.service;

import java.net.URI;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerozae.harunews.article.dto.response.NaverApiResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NaverApiService {

	@Value("${naver.client-id}")
	private String clientId;

	@Value("${naver.client-secret}")
	private String clientSecret;

	private final RestTemplate restTemplate;

	public NaverApiResponse getNaverNews(String keyword) {
		URI uri = createUri(keyword);

		RequestEntity<Void> request = RequestEntity
			.get(uri)
			.header("X-Naver-Client-Id", clientId)
			.header("X-Naver-Client-Secret", clientSecret)
			.build();

		String naverNews = restTemplate.exchange(request, String.class).getBody();
		return mapJsonToNaverApiResponse(naverNews);
	}

	private URI createUri(String keyword) {
		Random random = new Random();
		int randomDisplayCount = random.nextInt(3) + 2;
		return UriComponentsBuilder.fromUriString("https://openapi.naver.com/")
			.path("v1/search/news.json")
			.queryParam("query", keyword)
			.queryParam("display", randomDisplayCount)
			.queryParam("start", randomDisplayCount)
			.queryParam("sort", "sim")
			.encode()
			.build()
			.toUri();
	}

	private NaverApiResponse mapJsonToNaverApiResponse(String json) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(json, NaverApiResponse.class);
		} catch (Exception e) {
			return null;
		}
	}
}
