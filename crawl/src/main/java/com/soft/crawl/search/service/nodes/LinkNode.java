package com.soft.crawl.search.service.nodes;

import java.util.ArrayList;
import java.util.List;

public class LinkNode {

	private List<TermNode> termNodes = new ArrayList<>();
	private String url;
	private int depth;
	
	public LinkNode() {
	}

	public LinkNode(List<TermNode> termNodes, String url, int depth) {
		this.termNodes = termNodes;
		this.url = url;
		this.depth = depth;
	}

	public List<TermNode> getTermNodes() {
		return termNodes;
	}

	public void setTermNodes(List<TermNode> termNodes) {
		this.termNodes = termNodes;
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
		
		if (!(obj instanceof LinkNode)) {
			return false;
		}
	
		LinkNode other = (LinkNode) obj;
	
		if (!other.getUrl().equalsIgnoreCase(getUrl())) {
			return false;
		}
		
		if (other.getTermNodes().size() != getTermNodes().size()) {
			return false;
		}
		
		for(int i = 0; i < getTermNodes().size(); i++) {
			TermNode otherTermNode = other.getTermNodes().get(i);
			TermNode termNode = getTermNodes().get(i);
			
			if (!otherTermNode.equals(termNode)) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		return "LinkNode [termNodes=" + termNodes + ", url=" + url + ", depth=" + depth + "]";
	}
}
