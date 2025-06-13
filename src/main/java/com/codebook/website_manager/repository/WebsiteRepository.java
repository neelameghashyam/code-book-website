package com.codebook.website_manager.repository;

import com.codebook.website_manager.entity.Website;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface WebsiteRepository extends MongoRepository<Website, ObjectId> {

    @Query("{ 'apiToken' : ?0 }")
    Website findByApiToken(String apiToken);
}
