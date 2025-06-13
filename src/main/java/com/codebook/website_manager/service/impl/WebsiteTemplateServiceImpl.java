
package com.codebook.website_manager.service.impl;

import com.codebook.website_manager.dto.WebsiteTemplateDto;
import com.codebook.website_manager.entity.WebsiteTemplate;
import com.codebook.website_manager.mapper.WebsiteTemplateMapper;
import com.codebook.website_manager.repository.WebsiteTemplateRepository;
import com.codebook.website_manager.service.WebsiteTemplateService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebsiteTemplateServiceImpl implements WebsiteTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(WebsiteTemplateServiceImpl.class);
    private final WebsiteTemplateRepository repository;

    public WebsiteTemplateServiceImpl(WebsiteTemplateRepository repository) {
        this.repository = repository;
    }

    @Override
    public WebsiteTemplateDto getWebsiteTemplate(String id) {
        logger.info("Fetching WebsiteTemplate with ID: {}", id);
        WebsiteTemplate template = repository.findById(new ObjectId(id))
            .orElseThrow(() -> {
                logger.error("Website Template not found with ID: {}", id);
                return new RuntimeException("Website Template not found with ID: " + id);
            });
        return WebsiteTemplateMapper.toDto(template);
    }

    @Override
    public PagedModel<WebsiteTemplateDto> getAllWebsiteTemplates(Pageable pageable) {
        logger.info("Fetching all WebsiteTemplates, page: {}, size: {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<WebsiteTemplate> page = repository.findAll(pageable);
        List<WebsiteTemplateDto> dtos = page.getContent().stream()
            .map(WebsiteTemplateMapper::toDto)
            .collect(Collectors.toList());

        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(
            page.getSize(),
            page.getNumber(),
            page.getTotalElements(),
            page.getTotalPages()
        );

        return PagedModel.of(dtos, metadata);
    }

    @Override
    public WebsiteTemplateDto createWebsiteTemplate(@Valid WebsiteTemplateDto dto) {
        logger.info("Creating WebsiteTemplate: {}", dto);
        WebsiteTemplate entity = WebsiteTemplateMapper.toEntity(dto);
        WebsiteTemplate savedEntity = repository.save(entity);
        return WebsiteTemplateMapper.toDto(savedEntity);
    }

    @Override
    public WebsiteTemplateDto updateWebsiteTemplate(@Valid WebsiteTemplateDto dto) {
        logger.info("Updating WebsiteTemplate with ID: {}", dto.id());
        WebsiteTemplate existing = repository.findById(new ObjectId(dto.id()))
            .orElseThrow(() -> {
                logger.error("Website Template not found with ID: {}", dto.id());
                return new RuntimeException("Website Template not found with ID: " + dto.id());
            });
        WebsiteTemplate updatedEntity = new WebsiteTemplate(
            existing.id(),
            existing.createdById(),
            existing.companyId(),
            existing.createdTimestamp(),
            dto.businessCategory(),
            dto.businessSubcategory(),
            dto.showChatboxOnMobile(),
            dto.showChatboxOnDesktop(),
            dto.avatarType(),
            dto.postAgentResponseMessageEnabled(),
            dto.useGlobalNotificationSettings(),
            existing.queueMessageId(),
            existing.eyeCatcherId(),
            existing.chatWidgetId(),
            existing.assistantId(),
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
            existing.tags(),
            existing.smartResponses()
        );
        WebsiteTemplate savedEntity = repository.save(updatedEntity);
        return WebsiteTemplateMapper.toDto(savedEntity);
    }

    @Override
    public void deleteWebsiteTemplate(String id) {
        logger.info("Deleting WebsiteTemplate with ID: {}", id);
        repository.deleteById(new ObjectId(id));
    }
}
