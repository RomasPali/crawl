package com.soft.crawl.search.service;

import java.util.List;

/**
* Interface for search services
* 
* @author Romas
* 
*/
public interface CrawlSearchService {
	/**
	 * Crawls web returns true if successfully
	 * @param seed  
	 * @param terms
	 * @return true if success
	 */
	public boolean crawl(String seed, String terms);

	/**
	 * Returns list of seeds
	 * @return List of seeds
	 */
	public List<String> getSeeds();
	
	/**
	 * Returns bytes of report
	 * @param seed  
	 * @return bytes
	 */
	public byte[] getFullReportBytes(String seed);
	
	/**
	 * Returns top link entries bytes of report
	 * @param seed  
	 * @param top 
	 * @return bytes
	 */	
	public byte[] getTopReportBytes(String seed, int top);
}
