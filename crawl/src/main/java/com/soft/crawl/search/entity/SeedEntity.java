package com.soft.crawl.search.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
* Database entity, stores url and term entities and links
* 
* @author Romas
* 
*/
@Entity
@Table(name = "seed")
public class SeedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "url")
	private String url;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinTable(name = "seed_term", joinColumns = @JoinColumn(name = "link_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "seed_id", referencedColumnName = "id"))
	private List<TermEntity> termEntities = new ArrayList<>();
	
	@OneToMany(mappedBy="seedEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
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
		linkEntity.setSeedEntity(this);
		linkEntities.add(linkEntity);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<TermEntity> getTermEntities() {
		return termEntities;
	}

	public void setTermEntities(List<TermEntity> termEntities) {
		this.termEntities = termEntities;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
