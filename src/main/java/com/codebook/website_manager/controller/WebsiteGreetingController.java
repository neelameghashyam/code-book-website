package com.codebook.website_manager.controller;

import com.codebook.website_manager.dto.WebsiteGreetingDto;
import com.codebook.website_manager.service.WebsiteGreetingService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/website-manager/greetings")
public class WebsiteGreetingController {

    private final WebsiteGreetingService service;

    public WebsiteGreetingController(WebsiteGreetingService service) {
        this.service = service;
    }

    @GetMapping("/{greetingId}")
    public ResponseEntity<WebsiteGreetingDto> getWebsiteGreeting(@PathVariable String greetingId) {
        WebsiteGreetingDto dto = service.getWebsiteGreeting(greetingId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PagedModel<WebsiteGreetingDto>> getAllWebsiteGreetings(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        PagedModel<WebsiteGreetingDto> pageModel = service.getAllWebsiteGreetings(pageable);
        return new ResponseEntity<>(pageModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WebsiteGreetingDto> createWebsiteGreeting(@RequestBody WebsiteGreetingDto dto) {
        WebsiteGreetingDto savedDto = service.createWebsiteGreeting(dto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<WebsiteGreetingDto> updateWebsiteGreeting(@RequestBody WebsiteGreetingDto dto) {
        WebsiteGreetingDto updatedDto = service.updateWebsiteGreeting(dto);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @DeleteMapping("/{greetingId}")
    public ResponseEntity<String> deleteWebsiteGreeting(@PathVariable String greetingId) {
        service.deleteWebsiteGreeting(greetingId);
        return new ResponseEntity<>("Website Greeting deleted successfully: " + greetingId, HttpStatus.OK);
    }
}