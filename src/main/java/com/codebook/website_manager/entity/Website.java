package com.codebook.website_manager.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

import java.util.List;

@Document(collection = "website")
public record Website(
    @Id ObjectId id,
    ObjectId companyId,
    String domain,
    String apiToken,
    String apiTopic,
    boolean showChatboxOnMobile,
    boolean showChatboxOnDesktop,
    String avatarType,
    String zapierApiKey,
    ObjectId queueMessageId,
    ObjectId businessCategoryTemplateId,
    ObjectId websiteTemplateId,
    ObjectId eyeCatcherId,
    ObjectId chatWidgetId,
    boolean postAgentResponseMessageEnabled,
    boolean useGlobalNotificationSettings,
    boolean chatboxActive,
    boolean emailNotifications,
    boolean pushNotifications,
    ObjectId chatBotId,
    NotificationSettings notificationSettings,
    boolean useTheSameEmail,
    boolean leadGeneratedNotification,
    boolean serviceChatGeneratedNotification,
    List<ObjectId> tags
) {
    public record NotificationSettings(
        String allEmails,
        String leadEmails,
        String serviceChatEmails
    ) {}
}