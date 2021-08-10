package com.soft.crawl.search.service;

import org.springframework.stereotype.Service;

@Service
class CrawlSearchServiceImpl implements CrawlSearchService {

	@Override
	public void search(String url, String terms) {
		System.out.println(url+terms);
	}

}
