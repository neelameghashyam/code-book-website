package com.codebook.website_manager.controller;

import com.codebook.website_manager.dto.WebsiteDto;
import com.codebook.website_manager.service.WebsiteService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/website-manager/websites")
public class WebsiteController {

    private final WebsiteService service;

    public WebsiteController(WebsiteService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebsiteDto> getWebsite(@PathVariable String id) {
        WebsiteDto dto = service.getWebsite(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PagedModel<WebsiteDto>> getAllWebsites(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        PagedModel<WebsiteDto> pageModel = service.getAllWebsites(pageable);
        return new ResponseEntity<>(pageModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WebsiteDto> createWebsite(@RequestBody WebsiteDto dto) {
        WebsiteDto savedDto = service.createWebsite(dto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<WebsiteDto> updateWebsite(@RequestBody WebsiteDto dto) {
        WebsiteDto updatedDto = service.updateWebsite(dto);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWebsite(@PathVariable String id) {
        service.deleteWebsite(id);
        return new ResponseEntity<>("Website deleted successfully: " + id, HttpStatus.OK);
    }
}