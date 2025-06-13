
    package com.codebook.website_manager.service.impl;

    import com.codebook.website_manager.dto.WebsiteGreetingDto;
    import com.codebook.website_manager.entity.WebsiteGreeting;
    import com.codebook.website_manager.mapper.WebsiteGreetingMapper;
    import com.codebook.website_manager.repository.WebsiteGreetingRepository;
    import com.codebook.website_manager.service.WebsiteGreetingService;
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
    public class WebsiteGreetingServiceImpl implements WebsiteGreetingService {

        private static final Logger logger = LoggerFactory.getLogger(WebsiteGreetingServiceImpl.class);
        private final WebsiteGreetingRepository repository;

        public WebsiteGreetingServiceImpl(WebsiteGreetingRepository repository) {
            this.repository = repository;
        }

        @Override
        public WebsiteGreetingDto getWebsiteGreeting(String greetingId) {
            logger.info("Fetching WebsiteGreeting with ID: {}", greetingId);
            WebsiteGreeting greeting = repository.findById(new ObjectId(greetingId))
                .orElseThrow(() -> {
                    logger.error("Website Greeting not found with ID: {}", greetingId);
                    return new RuntimeException("Website Greeting not found with ID: " + greetingId);
                });
            return WebsiteGreetingMapper.toDto(greeting);
        }

        @Override
        public PagedModel<WebsiteGreetingDto> getAllWebsiteGreetings(Pageable pageable) {
            logger.info("Fetching all WebsiteGreetings, page: {}, size: {}", pageable.getPageNumber(), pageable.getPageSize());
            Page<WebsiteGreeting> page = repository.findAll(pageable);
            List<WebsiteGreetingDto> dtos = page.getContent().stream()
                .map(WebsiteGreetingMapper::toDto)
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
        public WebsiteGreetingDto createWebsiteGreeting(@Valid WebsiteGreetingDto dto) {
            logger.info("Creating WebsiteGreeting: {}", dto);
            WebsiteGreeting entity = WebsiteGreetingMapper.toEntity(dto);
            WebsiteGreeting savedEntity = repository.save(entity);
            return WebsiteGreetingMapper.toDto(savedEntity);
        }

        @Override
        public WebsiteGreetingDto updateWebsiteGreeting(@Valid WebsiteGreetingDto dto) {
            logger.info("Updating WebsiteGreeting with ID: {}", dto.greetingId());
            WebsiteGreeting existing = repository.findById(new ObjectId(dto.greetingId()))
                .orElseThrow(() -> {
                    logger.error("Website Greeting not found with ID: {}", dto.greetingId());
                    return new RuntimeException("Website Greeting not found with ID: " + dto.greetingId());
                });
            WebsiteGreeting updatedEntity = new WebsiteGreeting(
                existing.greetingId(),
                dto.targetUrl(),
                (int) dto.time().getEpochSecond()
            );
            WebsiteGreeting savedEntity = repository.save(updatedEntity);
            return WebsiteGreetingMapper.toDto(savedEntity);
        }

        @Override
        public void deleteWebsiteGreeting(String greetingId) {
            logger.info("Deleting WebsiteGreeting with ID: {}", greetingId);
            repository.deleteById(new ObjectId(greetingId));
        }
    }
