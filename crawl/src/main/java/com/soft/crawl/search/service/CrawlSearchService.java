package com.soft.crawl.search.service;

import java.util.List;

public interface CrawlSearchService {
	public boolean crawl(String seed, String terms);
	public List<String> getSeeds();
	public byte[] getFullReportBytes(String seed);
	public byte[] getTopReportBytes(String seed, int top);
}
