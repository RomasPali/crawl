package com.soft.crawl.dto;

/**
* DTO class
* 
* @author Romas
* 
*/
public class TopDTO {

	private String seed;
	private int top;
	
	public TopDTO() {
	}

	public TopDTO(String seed, int top) {
		this.seed = seed;
		this.top = top;
	}

	public String getSeed() {
		return seed;
	}

	public void setSeed(String seed) {
		this.seed = seed;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}
}
