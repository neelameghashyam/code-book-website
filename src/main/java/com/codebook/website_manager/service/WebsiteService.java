package com.codebook.website_manager.service;

import com.codebook.website_manager.dto.WebsiteDto;
import org.springframework.hateoas.PagedModel;
import org.springframework.data.domain.Pageable;

public interface WebsiteService {
    WebsiteDto getWebsite(String id);
    PagedModel<WebsiteDto> getAllWebsites(Pageable pageable);
    WebsiteDto createWebsite(WebsiteDto dto);
    WebsiteDto updateWebsite(WebsiteDto dto);
    void deleteWebsite(String id);
}