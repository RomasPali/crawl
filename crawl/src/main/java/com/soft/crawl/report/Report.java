package com.soft.crawl.report;

import com.soft.crawl.search.entity.SeedEntity;

/**
* Report interface for generating report bytes
* 
* @author Romas
* 
*/
public interface Report {
	/**
	 * Returns bytes of report
	 * @param seedEntity 
	 * @return the bytes of report
	 */
	public byte[] getReportBytes(SeedEntity seedEntity);
	
	
	/**
	 * Returns top entries bytes of report
	 * @param seedEntity 
	 * @param top
	 * @return the bytes of report
	 */
	public byte[] getReportTopBytes(SeedEntity seedEntity, int top);
}
