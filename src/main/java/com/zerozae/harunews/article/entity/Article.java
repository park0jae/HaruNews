package com.zerozae.harunews.article.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "original_link")
	private String originallink;

  @Column(name = "link")
	private String link;

	@Lob
	@Column(name = "description")
	private String description;

	@Column(name = "pub_date")
	private String pubDate;
}
