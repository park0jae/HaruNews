package com.zerozae.harunews.crawling.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.zerozae.harunews.article.entity.Article;
import com.zerozae.harunews.article.repository.ArticleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CrawlingService {

	private final ArticleRepository articleRepository;

	public void crawlingNews() {
		String url = "https://news.naver.com";
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements channelCards = doc.select("div.cjs_channel_card");
			for (Element channelCard : channelCards) {
				String channelWithDate = channelCard.select("div.cjs_ctw h4.channel").text();
				String channel = channelWithDate.replaceFirst("\\d{2}월 \\d{2}일.*", "").trim();

				Elements newsElements = channelCards.select("div.cjs_journal_wrap._item_contents");

				for (Element newsElement : newsElements) {
					String title = newsElement.select("div.cjs_t").text();
					String link = newsElement.select("a.cjs_news_a").attr("href");
					String content = newsElement.select("p.cjs_d").text() + "...";
					Article article = new Article(channel, title, link, content);
					articleRepository.save(article);
				}
			}
		} catch (IOException e) {
			log.warn("crawling ");
			throw new RuntimeException(e);
		}
		log.info("=====================================================================");
		log.info("doc = {}", doc);
		log.info("=====================================================================");
	}
}
