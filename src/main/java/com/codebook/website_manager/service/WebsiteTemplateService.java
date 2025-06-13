package com.codebook.website_manager.service;

import com.codebook.website_manager.dto.WebsiteTemplateDto;
import org.springframework.hateoas.PagedModel;
import org.springframework.data.domain.Pageable;

public interface WebsiteTemplateService {
    WebsiteTemplateDto getWebsiteTemplate(String id);
    PagedModel<WebsiteTemplateDto> getAllWebsiteTemplates(Pageable pageable);
    WebsiteTemplateDto createWebsiteTemplate(WebsiteTemplateDto dto);
    WebsiteTemplateDto updateWebsiteTemplate(WebsiteTemplateDto dto);
    void deleteWebsiteTemplate(String id);
}
