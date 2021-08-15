package com.soft.crawl.search.service.nodes;

import java.util.ArrayList;
import java.util.List;

public class SeedNode extends LinkNode {

	private List<LinkNode> linkNodes = new ArrayList<>();

	public SeedNode() {
	}

	public SeedNode(List<LinkNode> linkNodes) {
		this.linkNodes = linkNodes;
	}

	public List<LinkNode> getLinkNodes() {
		return linkNodes;
	}

	public void setLinkNodes(List<LinkNode> linkNodes) {
		this.linkNodes = linkNodes;
	}

	public void addLinkNode(LinkNode linkNode) {
		linkNodes.add(linkNode);
	}
	
}
