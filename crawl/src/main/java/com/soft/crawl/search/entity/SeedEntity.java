package com.soft.crawl.search.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "seed")
public class SeedEntity extends LinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinTable(name = "seed_link", joinColumns = @JoinColumn(name = "seed_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "link_id", referencedColumnName = "id"))
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
