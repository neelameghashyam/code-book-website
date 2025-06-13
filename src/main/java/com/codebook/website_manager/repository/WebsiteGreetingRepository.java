package com.codebook.website_manager.repository;

import com.codebook.website_manager.entity.WebsiteGreeting;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface WebsiteGreetingRepository extends MongoRepository<WebsiteGreeting, ObjectId> {

    @Query("{ 'greetingId' : ?0 }")
    WebsiteGreeting findByGreetingId(ObjectId greetingId);
}