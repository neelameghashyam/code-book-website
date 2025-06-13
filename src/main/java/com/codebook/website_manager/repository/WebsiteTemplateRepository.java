package com.codebook.website_manager.repository;

import com.codebook.website_manager.entity.WebsiteTemplate;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WebsiteTemplateRepository extends MongoRepository<WebsiteTemplate, ObjectId> {}
