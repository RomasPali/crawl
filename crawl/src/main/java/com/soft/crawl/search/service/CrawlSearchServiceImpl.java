package com.soft.crawl.search.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.soft.crawl.search.service.nodes.LinkNode;
import com.soft.crawl.search.service.nodes.SeedNode;


@Service
class CrawlSearchServiceImpl implements CrawlSearchService, InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		search("https://en.wikipedia.org/wiki/Elon_Musk", "Tesla,Musk,Gigafactory,Elon Mask");
	}

	
	@Override
	public void search(String url, String terms) {
		
		try {
			List<String> termList = Arrays.asList(terms.split(","));
			
			SeedNode seedNode = new SeedNode();
			seedNode.setUrl(url);
		
			generateLinkNodes(url);
			
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	private List<LinkNode> generateLinkNodes(String url) throws IOException {
		
		List<LinkNode> result = new ArrayList<>();
		
		Document seedDoc = Jsoup.connect(url).get();
		Elements links = seedDoc.select("a[href]");
		
		return result; 
	}
	
	/*
	@Override
	public void search(String url, String terms) {

		List<String> termList = Arrays.asList(terms.split(","));
		
		Node startNode = new Node(1, 0);
		Stack<Node> stack = new Stack<Node>();
		stack.push(startNode);

		for (int i = 0; i < 100 && !stack.isEmpty(); i++) {

			System.out.println("stack : " + stack.toString().replaceAll("\\[", "").replaceAll("]", ""));
			Node node = stack.pop();
			System.out.println(node);

			if (node.getDepth() < 5) {
				List<Node> newNodes = generatedNodes(node);

				for (Node newNode : newNodes) {
					stack.push(newNode);
				}
			}

		}

	}

	private List<Node> generatedNodes(Node node) {

		List<Node> generatedNodes = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			Node newNode = new Node(node.getNumber() + i, node.getDepth() + 1);
			generatedNodes.add(newNode);
		}

		return generatedNodes;
	}
	*/
}


