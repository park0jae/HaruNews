package com.zerozae.harunews.article.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "channel")
	private String channel;

	@Column(name = "title")
	private String title;

	@Column(name = "link")
	private String link;

	@Lob
	@Column(name = "content")
	private String content;

	public Article(String channel, String title, String link, String content) {
		this.channel = channel;
		this.title = title;
		this.link = link;
		this.content = content;
	}
}
