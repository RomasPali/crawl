package com.soft.crawl.search.entity;

import java.util.ArrayList;
import java.util.List;

public class SeedEntity extends LinkEntity {

	private List<LinkEntity> linkEntities = new ArrayList<>();

	public SeedEntity() {
	}

	public SeedEntity(List<LinkEntity> linkEntities) {
		this.linkEntities = linkEntities;
	}

	public List<LinkEntity> getLinkEntities() {
		return linkEntities;
	}

	public void setLinkEntities(List<LinkEntity> linkEntities) {
		this.linkEntities = linkEntities;
	}
	
	public void addLinkEntity(LinkEntity linkEntity) {
		linkEntities.add(linkEntity);
	}
}
