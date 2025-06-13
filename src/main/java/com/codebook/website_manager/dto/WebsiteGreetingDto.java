package com.codebook.website_manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record WebsiteGreetingDto(
    String greetingId,
    @NotBlank(message = "targetUrl is required")
    String targetUrl,
    @NotNull(message = "time is required")
    Instant time
) {}