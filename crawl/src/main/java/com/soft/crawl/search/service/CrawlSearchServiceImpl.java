package com.soft.crawl.search.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.springframework.stereotype.Service;

import com.soft.crawl.search.service.dfs.Node;

@Service
class CrawlSearchServiceImpl implements CrawlSearchService {

	@Override
	public void search(String url, String terms) {
		System.out.println(url + terms);

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

}
