package com.zerozae.harunews.article.dto.response;

import java.util.List;

import com.zerozae.harunews.article.entity.Article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NaverApiResponse {
	private String lastBuildDate;
	private int total;
	private int start;
	private int display;
	private List<Article> items;
}
