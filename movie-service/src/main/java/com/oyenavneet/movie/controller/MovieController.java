package com.oyenavneet.movie.controller;

import com.oyenavneet.movie.dto.MovieDetails;
import com.oyenavneet.movie.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDetails> getMovie(@PathVariable Integer movieId){
        var movieDetails = this.movieService.getMovie(movieId);
        return ResponseEntity.ok(movieDetails);
    }

    @PostMapping
    public ResponseEntity<MovieDetails> saveMovie(@RequestBody MovieDetails request) {
        var movieDetails = this.movieService.saveMovie(request);
        return ResponseEntity.ok(movieDetails);
    }
}
