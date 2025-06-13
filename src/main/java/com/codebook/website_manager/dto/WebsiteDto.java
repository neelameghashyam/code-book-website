
package com.codebook.website_manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record WebsiteDto(
    String id,
    @NotBlank(message = "companyId is required")
    String companyId,
    @NotBlank(message = "domain is required")
    String domain,
    @NotBlank(message = "apiToken is required")
    String apiToken,
    @NotBlank(message = "apiTopic is required")
    String apiTopic,
    boolean showChatboxOnMobile,
    boolean showChatboxOnDesktop,
    @NotBlank(message = "avatarType is required")
    String avatarType,
    String zapierApiKey,
    @NotBlank(message = "queueMessageId is required")
    String queueMessageId,
    @NotBlank(message = "businessCategoryTemplateId is required")
    String businessCategoryTemplateId,
    @NotBlank(message = "websiteTemplateId is required")
    String websiteTemplateId,
    @NotBlank(message = "eyeCatcherId is required")
    String eyeCatcherId,
    @NotBlank(message = "chatWidgetId is required")
    String chatWidgetId,
    boolean postAgentResponseMessageEnabled,
    boolean useGlobalNotificationSettings,
    boolean chatboxActive,
    boolean emailNotifications,
    boolean pushNotifications,
    @NotBlank(message = "chatBotId is required")
    String chatBotId,
    @NotNull(message = "notificationSettings is required")
    NotificationSettings notificationSettings,
    boolean useTheSameEmail,
    boolean leadGeneratedNotification,
    boolean serviceChatGeneratedNotification,
    @NotNull(message = "tags is required")
    List<String> tags
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
