package com.soft.crawl.search.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* LinkEntity repository
* 
* @author Romas
* 
*/
@Repository
public interface LinkRepository extends JpaRepository<LinkEntity, Long>  {

}
