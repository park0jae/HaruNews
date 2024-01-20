package com.zerozae.harunews.article.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zerozae.harunews.article.dto.request.ArticleRequest;
import com.zerozae.harunews.article.dto.response.ArticleResponse;
import com.zerozae.harunews.article.service.ArticleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/article")
public class ArticleController {

	private final ArticleService articleService;

	@GetMapping
	public ResponseEntity<List<ArticleResponse>> getArticles(@ModelAttribute ArticleRequest request) {
		List<ArticleResponse> response = articleService.getArticlesByChannel(request);
		return ResponseEntity.ok(response);
	}
}
