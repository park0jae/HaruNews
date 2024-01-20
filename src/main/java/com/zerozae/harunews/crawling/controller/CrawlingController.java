package com.zerozae.harunews.crawling.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zerozae.harunews.crawling.service.CrawlingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CrawlingController {

	private final CrawlingService crawlingService;

	@GetMapping("/api/v1/crawling")
	public ResponseEntity<Void> crawlingNews() {
		crawlingService.crawlingNews();
		return ResponseEntity.noContent().build();
	}
}
