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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
* Database entity, stores url and term entities
* 
* @author Romas
* 
*/
@Entity
@Table(name = "link")
public class LinkEntity implements Comparable< LinkEntity > {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinTable(name = "link_term", joinColumns = @JoinColumn(name = "link_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "term_id", referencedColumnName = "id"))
	private List<TermEntity> termEntities = new ArrayList<>();
	
	@Column(name = "url")
	private String url;
	
    @ManyToOne
    @JoinColumn(name="seed_id", nullable=false)
    private SeedEntity seedEntity;
    
	public LinkEntity() {
	}

	public LinkEntity(List<TermEntity> termEntities, String url) {
		this.termEntities = termEntities;
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public SeedEntity getSeedEntity() {
		return seedEntity;
	}

	public void setSeedEntity(SeedEntity seedEntity) {
		this.seedEntity = seedEntity;
	}

	public int getTotalHits() {
		int total = 0;
		for(TermEntity entity : termEntities) {
			total += entity.getHit();
		}
		return total;
	}

	@Override
	public String toString() {
		return "LinkEntity [id=" + id + ", url=" + url + "]";
	}

	@Override
	public int compareTo(LinkEntity o) {
		if(getTotalHits() == o.getTotalHits()) {
			return 0;
		} else if(getTotalHits() > o.getTotalHits()) {
			return -1;
		} else {
			return 1;
		}
	}
}
