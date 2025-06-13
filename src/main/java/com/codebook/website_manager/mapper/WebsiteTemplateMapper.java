package com.codebook.website_manager.mapper;

import com.codebook.website_manager.dto.WebsiteTemplateDto;
import com.codebook.website_manager.entity.WebsiteTemplate;
import org.bson.types.ObjectId;

import java.util.stream.Collectors;

public class WebsiteTemplateMapper {

    public static WebsiteTemplateDto toDto(WebsiteTemplate entity) {
        return new WebsiteTemplateDto(
            entity.id().toString(),
            entity.createdById().toString(),
            entity.companyId().toString(),
            entity.createdTimestamp(),
            entity.businessCategory(),
            entity.businessSubcategory(),
            entity.showChatboxOnMobile(),
            entity.showChatboxOnDesktop(),
            entity.avatarType(),
            entity.postAgentResponseMessageEnabled(),
            entity.useGlobalNotificationSettings(),
            entity.queueMessageId().toString(),
            entity.eyeCatcherId().toString(),
            entity.chatWidgetId().toString(),
            entity.assistantId().toString(),
            entity.emailNotifications(),
            entity.pushNotifications(),
            new WebsiteTemplateDto.NotificationSettings(
                entity.notificationSettings().allEmails(),
                entity.notificationSettings().leadEmails(),
                entity.notificationSettings().serviceChatEmails()
            ),
            entity.useTheSameEmail(),
            entity.leadGeneratedNotification(),
            entity.serviceChatGeneratedNotification(),
            entity.tags().stream().map(ObjectId::toString).collect(Collectors.toList()),
            entity.smartResponses().stream().map(ObjectId::toString).collect(Collectors.toList())
        );
    }

    public static WebsiteTemplate toEntity(WebsiteTemplateDto dto) {
        return new WebsiteTemplate(
            dto.id() != null ? new ObjectId(dto.id()) : null,
            new ObjectId(dto.createdById()),
            new ObjectId(dto.companyId()),
            dto.createdTimestamp(),
            dto.businessCategory(),
            dto.businessSubcategory(),
            dto.showChatboxOnMobile(),
            dto.showChatboxOnDesktop(),
            dto.avatarType(),
            dto.postAgentResponseMessageEnabled(),
            dto.useGlobalNotificationSettings(),
            new ObjectId(dto.queueMessageId()),
            new ObjectId(dto.eyeCatcherId()),
            new ObjectId(dto.chatWidgetId()),
            new ObjectId(dto.assistantId()),
            dto.emailNotifications(),
            dto.pushNotifications(),
            new WebsiteTemplate.NotificationSettings(
                dto.notificationSettings().allEmails(),
                dto.notificationSettings().leadEmails(),
                dto.notificationSettings().serviceChatEmails()
            ),
            dto.useTheSameEmail(),
            dto.leadGeneratedNotification(),
            dto.serviceChatGeneratedNotification(),
            dto.tags().stream().map(ObjectId::new).collect(Collectors.toList()),
            dto.smartResponses().stream().map(ObjectId::new).collect(Collectors.toList())
        );
    }
}