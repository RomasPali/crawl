package com.soft.crawl.search.service.nodes;

public class TermNode {

	private String term;
	private int hit;
	
	public TermNode() {
	}

	public TermNode(String term, int hit) {
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
	public boolean equals(Object obj) {
		
		if (obj == this) {
			return true;
		}
		
		if (!(obj instanceof TermNode)) {
			return false;
		}
		
		TermNode other = (TermNode) obj;
		return other.getHit() == getHit() && other.getTerm().equalsIgnoreCase(getTerm());
	}
	
	@Override
	public String toString() {
		return "TermNode [term=" + term + ", hit=" + hit + "]";
	}
}
