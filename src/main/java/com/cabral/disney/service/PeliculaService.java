package com.cabral.disney.service;

import com.cabral.disney.dto.PeliculaDTO;
import com.cabral.disney.exception.PeliculaNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PeliculaService {
    List<PeliculaDTO> getAllPeliculas();

    PeliculaDTO getPeliculaById(Long id) throws PeliculaNotFoundException;
}
