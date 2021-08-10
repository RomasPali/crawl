package com.soft.crawl.search.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.soft.crawl.search.service.CrawlSearchService;

@RestController
public class CrawlSearchController {

	private final CrawlSearchService crawlSearchService;

	public CrawlSearchController(CrawlSearchService crawlSearchService) {
		this.crawlSearchService = crawlSearchService;
	}

	@GetMapping("{url}/{terms}/search")
	public void search(@PathVariable String url, @PathVariable String terms) {
		crawlSearchService.search(url, terms);
	}

}
