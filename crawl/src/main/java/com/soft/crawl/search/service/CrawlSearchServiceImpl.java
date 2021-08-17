package com.soft.crawl.search.service;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import javax.transaction.Transactional;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.soft.crawl.report.Report;
import com.soft.crawl.search.entity.LinkEntity;
import com.soft.crawl.search.entity.SeedEntity;
import com.soft.crawl.search.entity.SeedRepository;
import com.soft.crawl.search.entity.TermEntity;
import com.soft.crawl.search.node.Node;
import com.soft.crawl.url.UrlValidator;

@Service
@Transactional
class CrawlSearchServiceImpl implements CrawlSearchService {

	@Value("${search.depth:8}")
	private int depth;

	@Value("${search.visitedNodes:10000}")
	private int visitedNodesCount;
	

	private final int HTML_RESPONSE_STATUS_OK = 200;

	private final String HTML_ELEMENT_A_HREF = "a[href]";
	private final String HTML_ATTRIBUTE_HREF = "href";

	private final SeedRepository seedRepository;
	private final Report report;
	private final UrlValidator urlValidator;
	
	public CrawlSearchServiceImpl(SeedRepository seedRepository, Report report, UrlValidator urlValidator) {
		this.seedRepository = seedRepository;
		this.report = report;
		this.urlValidator = urlValidator;
	}

	@Override
	public boolean crawl(String seedURL, String terms) {

		try {
			Document seedDoc = getJsoupDocument(seedURL);

			if (seedDoc == null) {
				return false;
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
						processing.addAll(generateNodes(seedDoc, node.getUrl(), node.getDepth() + 1));
					}
				}
				history.add(node);
			}

			seedRepository.save(seedEntity);
		} catch (IOException e) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public List<String> getSeeds() {
		List<String> result = new ArrayList<>();
		
		for(SeedEntity seedEntity : seedRepository.findAll()) {
			result.add(seedEntity.getUrl());
		}
		
		return result;
	}

	@Override
	public byte[] getFullReportBytes(String seed) {
		SeedEntity seedEntity = seedRepository.findByUrl(seed);
		return seedEntity != null ? report.getReportBytes(seedEntity) : null;
	}

	@Override
	public byte[] getTopReportBytes(String seed, int top) {
		SeedEntity seedEntity = seedRepository.findByUrl(seed);
		return seedEntity != null ? report.getReportTopBytes(seedEntity, top) : null;	
	}

	private List<Node> generateNodes(Document doc, String url, int depth) throws IOException {

		List<Node> result = new ArrayList<>();

		if (doc != null) {

			Elements links = doc.select(HTML_ELEMENT_A_HREF);

			for (Element link : links) {

				String linkURL = urlValidator.getLinkTextURL(url, link.attr(HTML_ATTRIBUTE_HREF));

				if (linkURL != null) {
					result.add(new Node(linkURL, depth + 1));
				}
			}
		}
		return result;
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

			if (connection.response().statusCode() == HTML_RESPONSE_STATUS_OK) {
				return doc;
			}
		} catch (Exception e) {
		}

		return null;
	}
}
