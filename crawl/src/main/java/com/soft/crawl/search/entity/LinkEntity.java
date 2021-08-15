package com.soft.crawl.search.entity;

import java.util.ArrayList;
import java.util.List;

public class LinkEntity {

	private List<TermEntity> termEntities = new ArrayList<>();	
	private String url;
	
	public LinkEntity() {
	}

	public LinkEntity(List<TermEntity> termEntities, String url) {
		this.termEntities = termEntities;
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<TermEntity> getTermEntities() {
		return termEntities;
	}

	public void setTermEntities(List<TermEntity> termEntities) {
		this.termEntities = termEntities;
	}

	@Override
	public String toString() {
		return "LinkEntity [termEntities=" + termEntities + ", url=" + url + "]";
	}
}
