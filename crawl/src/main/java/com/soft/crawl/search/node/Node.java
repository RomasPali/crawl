package com.soft.crawl.search.node;

import javassist.expr.Instanceof;

public class Node {
	private String url;
	private int depth;
	
	public Node() {
	}

	public Node(String url, int depth) {
		this.url = url;
		this.depth = depth;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == this) {
			return true;
		}
		
		if (!(obj instanceof Node)) {
			return false;
		}
		
		Node other = (Node) obj;
		return getDepth() == other.getDepth() && getUrl().equals(other.getUrl());
	}
	
	@Override
	public String toString() {
		return "Node [url=" + url + ", depth=" + depth + "]";
	}
	
}
