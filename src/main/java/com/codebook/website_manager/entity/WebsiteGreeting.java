package com.codebook.website_manager.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import org.bson.types.ObjectId;

@Document(collection = "website_greeting")
public record WebsiteGreeting(
    @Id ObjectId greetingId,
    @NotBlank(message = "targetUrl is required") String targetUrl,
    @NotNull(message = "time is required") int time
) {
    public WebsiteGreeting(ObjectId greetingId2, String targetUrl2, Instant time2) {
        this(greetingId2, targetUrl2, (int) time2.getEpochSecond());
    }
}
