package com.zerozae.harunews.article.dto.response;

import com.zerozae.harunews.article.entity.Article;

public record ArticleResponse(
	Long id,
	String channel,
	String title,
	String link,
	String content
) {
	public static ArticleResponse from(Article article) {
		return new ArticleResponse(
			article.getId(),
			article.getChannel(),
			article.getTitle(),
			article.getLink(),
			article.getContent()
		);
	}
}
