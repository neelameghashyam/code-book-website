package com.codebook.website_manager.controller;

import com.codebook.website_manager.dto.WebsiteTemplateDto;
import com.codebook.website_manager.service.WebsiteTemplateService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/website-manager/templates")
public class WebsiteTemplateController {

    private final WebsiteTemplateService service;

    public WebsiteTemplateController(WebsiteTemplateService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebsiteTemplateDto> getWebsiteTemplate(@PathVariable String id) {
        WebsiteTemplateDto dto = service.getWebsiteTemplate(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PagedModel<WebsiteTemplateDto>> getAllWebsiteTemplates(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        PagedModel<WebsiteTemplateDto> pageModel = service.getAllWebsiteTemplates(pageable);
        return new ResponseEntity<>(pageModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WebsiteTemplateDto> createWebsiteTemplate(@RequestBody WebsiteTemplateDto dto) {
        WebsiteTemplateDto savedDto = service.createWebsiteTemplate(dto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<WebsiteTemplateDto> updateWebsiteTemplate(@RequestBody WebsiteTemplateDto dto) {
        WebsiteTemplateDto updatedDto = service.updateWebsiteTemplate(dto);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWebsiteTemplate(@PathVariable String id) {
        service.deleteWebsiteTemplate(id);
        return new ResponseEntity<>("Website Template deleted successfully: " + id, HttpStatus.OK);
    }
}