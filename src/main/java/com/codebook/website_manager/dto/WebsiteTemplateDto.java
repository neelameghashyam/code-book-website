
package com.codebook.website_manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.List;

public record WebsiteTemplateDto(
    String id,
    @NotBlank(message = "createdById is required")
    String createdById,
    @NotBlank(message = "companyId is required")
    String companyId,
    @NotNull(message = "createdTimestamp is required")
    Instant createdTimestamp,
    @NotBlank(message = "businessCategory is required")
    String businessCategory,
    @NotBlank(message = "businessSubcategory is required")
    String businessSubcategory,
    boolean showChatboxOnMobile,
    boolean showChatboxOnDesktop,
    @NotBlank(message = "avatarType is required")
    String avatarType,
    boolean postAgentResponseMessageEnabled,
    boolean useGlobalNotificationSettings,
    @NotBlank(message = "queueMessageId is required")
    String queueMessageId,
    @NotBlank(message = "eyeCatcherId is required")
    String eyeCatcherId,
    @NotBlank(message = "chatWidgetId is required")
    String chatWidgetId,
    @NotBlank(message = "assistantId is required")
    String assistantId,
    boolean emailNotifications,
    boolean pushNotifications,
    @NotNull(message = "notificationSettings is required")
    NotificationSettings notificationSettings,
    boolean useTheSameEmail,
    boolean leadGeneratedNotification,
    boolean serviceChatGeneratedNotification,
    @NotNull(message = "tags is required")
    @Size(min = 1, message = "At least one tag is required")
    List<String> tags,
    @NotNull(message = "smartResponses is required")
    List<String> smartResponses
) {
    public record NotificationSettings(
        @NotBlank(message = "allEmails is required")
        String allEmails,
        @NotBlank(message = "leadEmails is required")
        String leadEmails,
        @NotBlank(message = "serviceChatEmails is required")
        String serviceChatEmails
    ) {}
}
