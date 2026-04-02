package com.oyenavneet.recommendation.service;

import com.oyenavneet.recommendation.dto.RecommendationEvents;
import com.oyenavneet.recommendation.mapper.RecommendationMapper;
import com.oyenavneet.recommendation.repository.CustomerGenreRepository;
import com.oyenavneet.streamflix.events.CustomerGenreUpdatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerGenreRepository customerGenreRepository;
    private final ApplicationEventPublisher eventPublisher;

    public CustomerService(CustomerGenreRepository customerGenreRepository, ApplicationEventPublisher eventPublisher) {
        this.customerGenreRepository = customerGenreRepository;
        this.eventPublisher = eventPublisher;
    }

    public void updateGenre(CustomerGenreUpdatedEvent genreUpdatedEvent) {
        var entity = RecommendationMapper.toCustomerGenre(genreUpdatedEvent);
        this.customerGenreRepository.save(entity);
        this.eventPublisher.publishEvent(new RecommendationEvents.PersonalizedEvent(genreUpdatedEvent.customerId()));
    }
}
