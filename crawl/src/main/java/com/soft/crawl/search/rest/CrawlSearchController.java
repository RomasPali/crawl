package com.soft.crawl.search.rest;

import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soft.crawl.search.service.CrawlSearchService;

@RestController
public class CrawlSearchController {

	private final CrawlSearchService crawlSearchService;

	public CrawlSearchController(CrawlSearchService crawlSearchService) {
		this.crawlSearchService = crawlSearchService;
	}

	/*
	@GetMapping("{url}/{terms}/search")
	public ResponseEntity<ByteArrayResource> search(@PathVariable String url, @PathVariable String terms) {
		byte[] bytes = crawlSearchService.search("https://en.wikipedia.org/wiki/Elon_Musk", "Tesla");
		
        HttpHeaders header = new HttpHeaders();
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        header.add("content-type", "text/csv");
        header.add("Content-Disposition", "attachment; filename=testCsv.csv");

        return ResponseEntity.ok().headers(header).contentLength(bytes.length).body(new ByteArrayResource(bytes));
	}
	*/

	@PostMapping("{seed}/{terms}/crawl")
	public ResponseEntity<?> crawl(@PathVariable String seed, @PathVariable String terms) {
		if (crawlSearchService.crawl(seed, terms)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.internalServerError().build();
	}

	@GetMapping("/seeds")
	public List<String> getSeeds() {
		return crawlSearchService.getSeeds();
	}

	@GetMapping("{seed}/full-report")
	public ResponseEntity<ByteArrayResource> getFullReport(@PathVariable String seed) {
		byte[] bytes = crawlSearchService.getFullReportBytes(seed);
		
		if (bytes == null) {
			return ResponseEntity.internalServerError().build();
		}
		
        HttpHeaders header = new HttpHeaders();
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        header.add("content-type", "text/csv");
        header.add("Content-Disposition", "attachment; filename=fullReport.csv");

        return ResponseEntity.ok().headers(header).contentLength(bytes.length).body(new ByteArrayResource(bytes));
	}

	@GetMapping("{seed}/{top}/top-report")
	public ResponseEntity<ByteArrayResource> getTopReport(@PathVariable String seed, @PathVariable int top) {
		byte[] bytes = crawlSearchService.getTopReportBytes(seed, top);

		if (bytes == null) {
			return ResponseEntity.internalServerError().build();
		}

        HttpHeaders header = new HttpHeaders();
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        header.add("content-type", "text/csv");
        header.add("Content-Disposition", "attachment; filename=testCsv.csv");

        return ResponseEntity.ok().headers(header).contentLength(bytes.length).body(new ByteArrayResource(bytes));
	}
}
