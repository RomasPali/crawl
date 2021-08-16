package com.soft.crawl.search.node;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NodeTest {

	@Test
	void testEqualsObject() {
		
		int depth0 = 0;
		int depth1 = 1;
		
		String url1 = "www.url1.com";
		String url2 = "www.url2.com";
		
		Node node1 = new Node(url1, depth0);
		Node node2 = new Node(url1, depth0);
		
		assertTrue(node1.equals(node2));
		
		Node node3 = new Node(url2, depth1);
		assertFalse(node1.equals(node3));	
	}

}
