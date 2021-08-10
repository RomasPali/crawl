package com.soft.crawl.search.service.dfs;

public class Node {

	private int number;
	private int depth;

	public Node() {
	}

	public Node(int number, int depth) {
		super();
		this.number = number;
		this.depth = depth;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public String toString() {
		return "Node [number=" + number + ", depth=" + depth + "]";
	}

}
