package com.soft.crawl.dto;

/**
* DTO class
* 
* @author Romas
* 
*/
public class CrawlDTO {
	private String seed;
	private String terms;
	
	public CrawlDTO() {
	}

	public CrawlDTO(String seed, String terms) {
		this.seed = seed;
		this.terms = terms;
	}

	public String getSeed() {
		return seed;
	}

	public void setSeed(String seed) {
		this.seed = seed;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

}
