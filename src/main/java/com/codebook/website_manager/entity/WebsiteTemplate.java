package com.codebook.website_manager.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

import java.time.Instant;
import java.util.List;

@Document(collection = "website_template")
public record WebsiteTemplate(
    @Id ObjectId id,
    ObjectId createdById,
    ObjectId companyId,
    Instant createdTimestamp,
    String businessCategory,
    String businessSubcategory,
    boolean showChatboxOnMobile,
    boolean showChatboxOnDesktop,
    String avatarType,
    boolean postAgentResponseMessageEnabled,
    boolean useGlobalNotificationSettings,
    ObjectId queueMessageId,
    ObjectId eyeCatcherId,
    ObjectId chatWidgetId,
    ObjectId assistantId,
    boolean emailNotifications,
    boolean pushNotifications,
    NotificationSettings notificationSettings,
    boolean useTheSameEmail,
    boolean leadGeneratedNotification,
    boolean serviceChatGeneratedNotification,
    List<ObjectId> tags,
    List<ObjectId> smartResponses
) {
    public record NotificationSettings(
        String allEmails,
        String leadEmails,
        String serviceChatEmails
    ) {}
}