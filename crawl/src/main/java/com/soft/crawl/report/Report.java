package com.soft.crawl.report;

import com.soft.crawl.search.entity.SeedEntity;

public interface Report {
	public byte[] getReportBytes(SeedEntity seedEntity);
	public byte[] getReportTopBytes(SeedEntity seedEntity, int top);
}
