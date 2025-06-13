package com.codebook.website_manager.mapper;

import java.time.Instant;

import org.bson.types.ObjectId;

import com.codebook.website_manager.dto.WebsiteGreetingDto;
import com.codebook.website_manager.entity.WebsiteGreeting;

public class WebsiteGreetingMapper {
    public static WebsiteGreetingDto toDto(WebsiteGreeting entity) {
        return new WebsiteGreetingDto(
            entity.greetingId().toHexString(),
            entity.targetUrl(),
            Instant.ofEpochSecond(entity.time())
        );
    }

    public static WebsiteGreeting toEntity(WebsiteGreetingDto dto) {
        return new WebsiteGreeting(
            dto.greetingId() != null ? new ObjectId(dto.greetingId()) : null,
            dto.targetUrl(),
            (int) dto.time().getEpochSecond()
        );
    }
}