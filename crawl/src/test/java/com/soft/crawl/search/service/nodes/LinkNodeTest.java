package com.soft.crawl.search.service.nodes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class LinkNodeTest {

	@Test
	void testEqualsObject() {
		
		List<TermNode> termNodes1 = Arrays.asList(new TermNode("termA", 20), new TermNode("termB", 21));
		List<TermNode> termNodes2 = Arrays.asList(new TermNode("termC", 22), new TermNode("termD", 22));
		
		
		String url1 = "https://www.url1.com";
		String url2 = "https://www.url2.com";
		
		LinkNode node1 = new LinkNode(termNodes1, url1, 0);
		LinkNode node2 = new LinkNode(termNodes1, url1, 0);
		
		assertTrue(node1.equals(node2));
	
		LinkNode node3 = new LinkNode(termNodes1, url2, 0);
		assertFalse(node1.equals(node3));

		LinkNode node4 = new LinkNode(termNodes2, url2, 0);
		assertFalse(node1.equals(node4));		
	}
	
	@Test
	void testListContains() {
		
		List<TermNode> termNodes1 = Arrays.asList(new TermNode("termA", 20), new TermNode("termB", 21));
		List<TermNode> termNodes2 = Arrays.asList(new TermNode("termC", 22), new TermNode("termD", 22));
		
		
		String url1 = "https://www.url1.com";
		String url2 = "https://www.url2.com";
		
		LinkNode node1 = new LinkNode(termNodes1, url1, 0);

		List<LinkNode> linkNodes = new ArrayList<>();
				
		linkNodes.add(new LinkNode(termNodes1, url2, 0));
		assertFalse(linkNodes.contains(node1));
		
		linkNodes.add(new LinkNode(termNodes2, url1, 0));
		assertFalse(linkNodes.contains(node1));
		
		linkNodes.add(node1);
		assertTrue(linkNodes.contains(node1));
	}

}
