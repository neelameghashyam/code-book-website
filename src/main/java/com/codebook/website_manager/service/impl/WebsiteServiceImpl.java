package com.codebook.website_manager.service.impl;

import com.codebook.website_manager.dto.WebsiteDto;
import com.codebook.website_manager.entity.Website;
import com.codebook.website_manager.mapper.WebsiteMapper;
import com.codebook.website_manager.repository.WebsiteRepository;
import com.codebook.website_manager.service.WebsiteService;
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
public class WebsiteServiceImpl implements WebsiteService {

    private static final Logger logger = LoggerFactory.getLogger(WebsiteServiceImpl.class);
    private final WebsiteRepository repository;

    public WebsiteServiceImpl(WebsiteRepository repository) {
        this.repository = repository;
    }

    @Override
    public WebsiteDto getWebsite(String id) {
        logger.info("Fetching Website with ID: {}", id);
        Website website = repository.findById(new ObjectId(id))
            .orElseThrow(() -> {
                logger.error("Website not found with ID: {}", id);
                return new RuntimeException("Website not found with ID: " + id);
            });
        return WebsiteMapper.toDto(website);
    }

    @Override
    public PagedModel<WebsiteDto> getAllWebsites(Pageable pageable) {
        logger.info("Fetching all Websites, page: {}, size: {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<Website> page = repository.findAll(pageable);
        List<WebsiteDto> dtos = page.getContent().stream()
            .map(WebsiteMapper::toDto)
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
    public WebsiteDto createWebsite(@Valid WebsiteDto dto) {
        logger.info("Creating Website: {}", dto);
        Website entity = WebsiteMapper.toEntity(dto);
        Website savedEntity = repository.save(entity);
        return WebsiteMapper.toDto(savedEntity);
    }

    @Override
    public WebsiteDto updateWebsite(@Valid WebsiteDto dto) {
        logger.info("Updating Website with ID: {}", dto.id());
        Website existing = repository.findById(new ObjectId(dto.id()))
            .orElseThrow(() -> {
                logger.error("Website not found with ID: {}", dto.id());
                return new RuntimeException("Website not found with ID: " + dto.id());
            });
        Website updatedEntity = new Website(
            existing.id(),
            existing.companyId(),
            dto.domain(),
            dto.apiToken(),
            dto.apiTopic(),
            dto.showChatboxOnMobile(),
            dto.showChatboxOnDesktop(),
            dto.avatarType(),
            dto.zapierApiKey(),
            existing.queueMessageId(),
            existing.businessCategoryTemplateId(),
            existing.websiteTemplateId(),
            existing.eyeCatcherId(),
            existing.chatWidgetId(),
            dto.postAgentResponseMessageEnabled(),
            dto.useGlobalNotificationSettings(),
            dto.chatboxActive(),
            dto.emailNotifications(),
            dto.pushNotifications(),
            existing.chatBotId(),
            new Website.NotificationSettings(
                dto.notificationSettings().allEmails(),
                dto.notificationSettings().leadEmails(),
                dto.notificationSettings().serviceChatEmails()
            ),
            dto.useTheSameEmail(),
            dto.leadGeneratedNotification(),
            dto.serviceChatGeneratedNotification(),
            existing.tags()
        );
        Website savedEntity = repository.save(updatedEntity);
        return WebsiteMapper.toDto(savedEntity);
    }

    @Override
    public void deleteWebsite(String id) {
        logger.info("Deleting Website with ID: {}", id);
        repository.deleteById(new ObjectId(id));
    }
}
