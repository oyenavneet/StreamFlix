package com.oyenavneet.movie.messaging;

import com.oyenavneet.streamflix.events.MovieAddedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class MovieEventPublisher {
    private static final String MOVIE_EVENTS_OUT = "movie-events-out";
    private static final Logger logger = LoggerFactory.getLogger(MovieEventPublisher.class);
    private final StreamBridge streamBridge;

    public MovieEventPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }


    @EventListener
    public void onMovieAdded(MovieAddedEvent movieAddedEvent) {
        var message = MessageBuilder.withPayload(movieAddedEvent)
                .setHeader(KafkaHeaders.KEY, movieAddedEvent.movieId())
                .build();
        streamBridge.send(MOVIE_EVENTS_OUT, message);
        logger.info("Movie published: {}", message);

    }
}
