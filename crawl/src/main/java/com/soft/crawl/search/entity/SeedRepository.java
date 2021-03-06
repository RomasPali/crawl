package com.soft.crawl.search.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeedRepository extends JpaRepository<SeedEntity, Long> {
	public SeedEntity findByUrl(String url);
}
