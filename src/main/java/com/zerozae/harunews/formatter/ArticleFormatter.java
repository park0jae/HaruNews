package com.zerozae.harunews.formatter;

import java.util.List;

import com.zerozae.harunews.article.dto.response.ArticleResponse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleFormatter {

	public static String formatNaverApiResponse(List<ArticleResponse> articles) {
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
