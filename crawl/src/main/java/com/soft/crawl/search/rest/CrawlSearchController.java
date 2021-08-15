package com.soft.crawl.search.rest;

import javax.annotation.Resource;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.soft.crawl.search.service.CrawlSearchService;

@RestController
public class CrawlSearchController {

	private final CrawlSearchService crawlSearchService;

	public CrawlSearchController(CrawlSearchService crawlSearchService) {
		this.crawlSearchService = crawlSearchService;
	}

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

}
