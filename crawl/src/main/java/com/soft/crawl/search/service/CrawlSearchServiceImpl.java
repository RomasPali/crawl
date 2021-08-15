package com.soft.crawl.search.service;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.LinkedNode;
import com.opencsv.CSVWriter;
import com.soft.crawl.search.entity.LinkEntity;
import com.soft.crawl.search.entity.SeedEntity;
import com.soft.crawl.search.entity.TermEntity;
import com.soft.crawl.search.node.Node;

@Service
class CrawlSearchServiceImpl implements CrawlSearchService, InitializingBean {

	private final int depth = 2;
	private final int visitedNodesCount = 100;
	private final String HTML_ELEMENT_A_HREF = "a[href]";
	private final String HTML_ATTRIBUTE_HREF = "href";

	@Override
	public void afterPropertiesSet() throws Exception {
		// search("https://en.wikipedia.org/wiki/Elon_Musk",
		// "Tesla,Musk,Gigafactory,Elon Mask");
		//search("https://en.wikipedia.org/wiki/Elon_Musk", "Tesla");
	}

	@Override
	public byte[] search(String seedURL, String terms) {

		try {
			Document seedDoc = getJsoupDocument(seedURL);

			if (seedDoc == null) {
				return null;
			}

			List<String> termList = Arrays.asList(terms.split(","));

			SeedEntity seedEntity = new SeedEntity();
			seedEntity.setUrl(seedURL);
			seedEntity.setTermEntities(generateTermEntities(seedDoc, termList));

			Deque<Node> processing = new ArrayDeque<>();
			List<Node> history = new ArrayList<>();

			processing.addAll(generateNodes(seedDoc, seedURL, 0));

			for (int i = 0; i < visitedNodesCount && !processing.isEmpty(); i++) {
				Node node = processing.pop();

				System.out.println("pop: i:" + i + " " + node);

				if (node.getDepth() < depth && !history.contains(node)) {

					Document doc = getJsoupDocument(node.getUrl());

					if (doc != null) {
						seedEntity.addLinkEntity(createLinkEntity(node, doc, termList));
					}
					processing.addAll(generateNodes(seedDoc, seedURL, 0));
				}
				history.add(node);
			}

			System.out.println("Finish");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return getBytes();
	}

	public byte[] getBytes() {
		try {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			OutputStreamWriter streamWriter = new OutputStreamWriter(stream);

			CSVWriter writer = new CSVWriter(streamWriter);

			List<String[]> data = new ArrayList<String[]>();
			data.add(new String[] { "Name", "Class", "Marks" });
			data.add(new String[] { "Aman", "10", "620" });
			data.add(new String[] { "Suraj", "10", "630" });
			writer.writeAll(data);
			streamWriter.flush();
			writer.close();
			
			return stream.toByteArray();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private List<Node> generateNodes(Document doc, String url, int depth) throws IOException {

		List<Node> result = new ArrayList<>();

		if (doc != null) {

			Elements links = doc.select(HTML_ELEMENT_A_HREF);

			for (Element link : links) {

				String linkURL = getLinkTextURL(url, link.attr(HTML_ATTRIBUTE_HREF));

				if (linkURL != null) {
					result.add(new Node(linkURL, depth + 1));
				}
			}
		}
		return result;
	}

	private String getLinkTextURL(String url, String linkText) throws IOException {

		if (isValidURL(linkText)) {
			return linkText;
		}

		if (linkText.startsWith("#")) {
			return null;
		}

		URL seedURL = new URL(url);
		return String.format("%s://%s%s", seedURL.getProtocol(), seedURL.getAuthority(), linkText);
	}

	private boolean isValidURL(String url) {

		try {
			new URL(url).toURI();
		} catch (MalformedURLException | URISyntaxException e) {
			return false;
		}

		return true;
	}

	private LinkEntity createLinkEntity(Node node, Document doc, List<String> termList) throws IOException {

		LinkEntity linkEntity = new LinkEntity();

		linkEntity.setUrl(node.getUrl());
		linkEntity.setTermEntities(generateTermEntities(doc, termList));

		return linkEntity;
	}

	private List<TermEntity> generateTermEntities(Document doc, List<String> termList) throws IOException {

		List<TermEntity> result = new ArrayList<>();

		for (String term : termList) {
			TermEntity termEntity = new TermEntity();
			termEntity.setTerm(term);
			termEntity.setHit(doc.getElementsContainingText(term).size());
			result.add(termEntity);
		}

		return result;
	}

	private Document getJsoupDocument(String url) {

		try {
			Connection connection = Jsoup.connect(url);
			Document doc = connection.get();

			if (connection.response().statusCode() == 200) {
				return doc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
