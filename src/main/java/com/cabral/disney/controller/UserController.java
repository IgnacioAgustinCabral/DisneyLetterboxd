package com.cabral.disney.controller;

import com.cabral.disney.exception.MovieNotFoundException;
import com.cabral.disney.models.User;
import com.cabral.disney.service.LikeMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private LikeMovieService likeMovieService;

    @Autowired
    public UserController(LikeMovieService likeMovieService) {
        this.likeMovieService = likeMovieService;
    }

    @PostMapping("/like-movie/{movieId}")
    public ResponseEntity<Map<String, String>> likeMovie(@PathVariable Long movieId, @AuthenticationPrincipal User userDetails) throws MovieNotFoundException {
        String movieTitle = this.likeMovieService.likeMovie(movieId, userDetails.getId());

        Map<String, String> response = new HashMap<>();

        response.put("message", "You've liked " + movieTitle);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/remove-like/{movieId}")
    public ResponseEntity<?> removeLike(@PathVariable Long movieId, @AuthenticationPrincipal User userDetails) throws MovieNotFoundException {
        String movieTitle = this.likeMovieService.removeLike(movieId,userDetails.getId());

        Map<String, String> response = new HashMap<>();

        response.put("message", "You've removed " + movieTitle + " from your liked movies");

        return ResponseEntity.ok(response);
    }
}