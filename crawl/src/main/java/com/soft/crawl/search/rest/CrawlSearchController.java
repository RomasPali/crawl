package com.soft.crawl.search.rest;

import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.soft.crawl.dto.CrawlDTO;
import com.soft.crawl.dto.TopDTO;
import com.soft.crawl.search.service.CrawlSearchService;

/**
* Controller, where receives crawl, seeds, generate-full-report, generate-top-report requests
* 
* @author Romas
* 
*/
@RestController
public class CrawlSearchController {

	private final CrawlSearchService crawlSearchService;

	public CrawlSearchController(CrawlSearchService crawlSearchService) {
		this.crawlSearchService = crawlSearchService;
	}
	
	@PostMapping("/crawl")
	public ResponseEntity<?> crawl(@RequestBody CrawlDTO dto) {
		if (crawlSearchService.crawl(dto.getSeed(), dto.getTerms())) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.internalServerError().build();
	}

	@GetMapping("/seeds")
	public List<String> getSeeds() {
		return crawlSearchService.getSeeds();
	}

	@PostMapping("/generate-full-report")
	public ResponseEntity<ByteArrayResource> generateFullReport(@RequestBody String seed) {
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

	@PostMapping("/generate-top-report")
	public ResponseEntity<ByteArrayResource> getTopReport(@RequestBody TopDTO dto) {
		byte[] bytes = crawlSearchService.getTopReportBytes(dto.getSeed(), dto.getTop());

		if (bytes == null) {
			return ResponseEntity.internalServerError().build();
		}

        HttpHeaders header = new HttpHeaders();
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        header.add("content-type", "text/csv");
        header.add("Content-Disposition", "attachment; filename=topReport.csv");

        return ResponseEntity.ok().headers(header).contentLength(bytes.length).body(new ByteArrayResource(bytes));
	}
}
