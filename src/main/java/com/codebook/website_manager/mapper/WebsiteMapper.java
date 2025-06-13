package com.codebook.website_manager.mapper;

import com.codebook.website_manager.dto.WebsiteDto;
import com.codebook.website_manager.entity.Website;
import org.bson.types.ObjectId;

import java.util.stream.Collectors;

public class WebsiteMapper {

    public static WebsiteDto toDto(Website entity) {
        return new WebsiteDto(
            entity.id().toString(),
            entity.companyId().toString(),
            entity.domain(),
            entity.apiToken(),
            entity.apiTopic(),
            entity.showChatboxOnMobile(),
            entity.showChatboxOnDesktop(),
            entity.avatarType(),
            entity.zapierApiKey(),
            entity.queueMessageId().toString(),
            entity.businessCategoryTemplateId().toString(),
            entity.websiteTemplateId().toString(),
            entity.eyeCatcherId().toString(),
            entity.chatWidgetId().toString(),
            entity.postAgentResponseMessageEnabled(),
            entity.useGlobalNotificationSettings(),
            entity.chatboxActive(),
            entity.emailNotifications(),
            entity.pushNotifications(),
            entity.chatBotId().toString(),
            new WebsiteDto.NotificationSettings(
                entity.notificationSettings().allEmails(),
                entity.notificationSettings().leadEmails(),
                entity.notificationSettings().serviceChatEmails()
            ),
            entity.useTheSameEmail(),
            entity.leadGeneratedNotification(),
            entity.serviceChatGeneratedNotification(),
            entity.tags().stream().map(ObjectId::toString).collect(Collectors.toList())
        );
    }

    public static Website toEntity(WebsiteDto dto) {
        return new Website(
            dto.id() != null ? new ObjectId(dto.id()) : null,
            new ObjectId(dto.companyId()),
            dto.domain(),
            dto.apiToken(),
            dto.apiTopic(),
            dto.showChatboxOnMobile(),
            dto.showChatboxOnDesktop(),
            dto.avatarType(),
            dto.zapierApiKey(),
            new ObjectId(dto.queueMessageId()),
            new ObjectId(dto.businessCategoryTemplateId()),
            new ObjectId(dto.websiteTemplateId()),
            new ObjectId(dto.eyeCatcherId()),
            new ObjectId(dto.chatWidgetId()),
            dto.postAgentResponseMessageEnabled(),
            dto.useGlobalNotificationSettings(),
            dto.chatboxActive(),
            dto.emailNotifications(),
            dto.pushNotifications(),
            new ObjectId(dto.chatBotId()),
            new Website.NotificationSettings(
                dto.notificationSettings().allEmails(),
                dto.notificationSettings().leadEmails(),
                dto.notificationSettings().serviceChatEmails()
            ),
            dto.useTheSameEmail(),
            dto.leadGeneratedNotification(),
            dto.serviceChatGeneratedNotification(),
            dto.tags().stream().map(ObjectId::new).collect(Collectors.toList())
        );
    }
}