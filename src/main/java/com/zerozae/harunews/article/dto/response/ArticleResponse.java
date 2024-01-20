package com.zerozae.harunews.article.dto.response;

import com.zerozae.harunews.article.entity.Article;

public record ArticleResponse(
	String title,
	String link,
	String content,
	String pubDate,
	String keyword
) {
	public static ArticleResponse from(Article article, String keyword) {
		return new ArticleResponse(
			removeHtmlTags(article.getTitle()),
			article.getLink(),
			removeHtmlTags(article.getDescription()),
			article.getPubDate(),
			keyword
		);
	}

	public static String removeHtmlTags(String input) {
		String result = input.replaceAll("<b>.*?</b>", "");
		return result.replaceAll("&quot;", "\"");
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("\nTitle: ").append(title)
			.append("\nLink: ").append(link);

		if (!content.isEmpty()) {
			result.append("\nContent: ").append(content);
		}

		result.append("\npubDate: ").append(pubDate)
			.append("\n=================================\n");

		return result.toString();
	}
}
