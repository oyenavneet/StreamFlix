package com.oyenavneet.recommendation.messaging;

import com.oyenavneet.recommendation.service.CustomerService;
import com.oyenavneet.recommendation.service.MovieService;
import com.oyenavneet.streamflix.events.CustomerGenreUpdatedEvent;
import com.oyenavneet.streamflix.events.MovieAddedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class EventConsumerConfig {

    private static final Logger logger = LoggerFactory.getLogger(EventConsumerConfig.class);

    @Bean
    public Consumer<CustomerGenreUpdatedEvent> genreUpdatedEventConsumer(CustomerService customerService) {
        return withLogging(customerService::updateGenre);
    }


    @Bean
    public Consumer<MovieAddedEvent> movieAddedEventConsumer(MovieService movieService) {
        return withLogging(movieService::addMovie);
    }

    private <T> Consumer<T> withLogging(Consumer<T> consumer) {
        return t -> {
            logger.info("received {}", t);
            consumer.accept(t);
        };
    }
}
