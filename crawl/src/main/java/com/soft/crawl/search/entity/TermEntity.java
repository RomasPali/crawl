package com.soft.crawl.search.entity;

public class TermEntity {

	private String term;
	private int hit;
	
	public TermEntity() {
	}

	public TermEntity(String term, int hit) {
		this.term = term;
		this.hit = hit;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	@Override
	public String toString() {
		return "TermEntity [term=" + term + ", hit=" + hit + "]";
	}
}
