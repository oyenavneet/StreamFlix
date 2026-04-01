package com.oyenavneet.movie.service;

import com.oyenavneet.movie.dto.MovieDetails;
import com.oyenavneet.movie.exception.MovieNotFoundException;
import com.oyenavneet.movie.mapper.MovieMapper;
import com.oyenavneet.movie.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ApplicationEventPublisher eventPublisher;

    public MovieService(MovieRepository movieRepository, ApplicationEventPublisher eventPublisher) {
        this.movieRepository = movieRepository;
        this.eventPublisher = eventPublisher;
    }

    public MovieDetails getMovie(Integer movieId) {
        return this.movieRepository.findById(movieId)
                .map(MovieMapper::toMovieDetails)
                .orElseThrow(() -> new MovieNotFoundException(movieId));
    }


    @Transactional
    public MovieDetails saveMovie(MovieDetails movieDetails) {

        var movie = this.movieRepository.save(MovieMapper.toMovie(movieDetails));
        this.eventPublisher.publishEvent(MovieMapper.toMovieAddedEvent(movie));
        return MovieMapper.toMovieDetails(movie);
    }
}
