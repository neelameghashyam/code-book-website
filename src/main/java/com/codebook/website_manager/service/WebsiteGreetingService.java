package com.codebook.website_manager.service;

import com.codebook.website_manager.dto.WebsiteGreetingDto;
import org.springframework.hateoas.PagedModel;
import org.springframework.data.domain.Pageable;

public interface WebsiteGreetingService {
    WebsiteGreetingDto getWebsiteGreeting(String greetingId);
    PagedModel<WebsiteGreetingDto> getAllWebsiteGreetings(Pageable pageable);
    WebsiteGreetingDto createWebsiteGreeting(WebsiteGreetingDto dto);
    WebsiteGreetingDto updateWebsiteGreeting(WebsiteGreetingDto dto);
    void deleteWebsiteGreeting(String greetingId);
}