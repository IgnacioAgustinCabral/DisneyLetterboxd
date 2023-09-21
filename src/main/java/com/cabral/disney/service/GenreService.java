package com.cabral.disney.service;

import com.cabral.disney.exception.GenreNotFoundException;
import com.cabral.disney.payload.response.GenreResponse;

import java.util.List;

public interface GenreService {
    List<GenreResponse> getAllGenres();

    GenreResponse getGenreById(Long id) throws GenreNotFoundException;
}