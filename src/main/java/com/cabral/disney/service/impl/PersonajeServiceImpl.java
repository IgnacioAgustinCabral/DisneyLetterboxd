package com.cabral.disney.service.impl;

import com.cabral.disney.dto.PersonajeDTO;
import com.cabral.disney.entity.Personaje;
import com.cabral.disney.exception.PersonajeNotFoundException;
import com.cabral.disney.mapper.PersonajeMapper;
import com.cabral.disney.repository.PersonajeRepository;
import com.cabral.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonajeServiceImpl implements PersonajeService {
    private PersonajeRepository personajeRepository;

    @Autowired
    public PersonajeServiceImpl(PersonajeRepository personajeRepository) {
        this.personajeRepository = personajeRepository;
    }

    @Override
    public List<Personaje> getAllPersonajes() {
        return this.personajeRepository.findAll();
    }

    @Override
    public PersonajeDTO createPersonaje(PersonajeDTO personajeDTO) {
        Personaje newPersonaje = this.personajeRepository.save(PersonajeMapper.mapToEntity(personajeDTO));

        return PersonajeMapper.mapToDTO(newPersonaje);
    }

    @Override
    public PersonajeDTO getPersonajeById(Long id) throws PersonajeNotFoundException {
        Personaje personaje = this.personajeRepository.findById(id).orElseThrow(() -> new PersonajeNotFoundException("Personaje could not be found"));

        return PersonajeMapper.mapToDTO(personaje);
    }

    @Override
    public PersonajeDTO updatePersonaje(Long id, PersonajeDTO personajeDTO) throws PersonajeNotFoundException {
        Personaje personaje = this.personajeRepository.findById(id).orElseThrow(() -> new PersonajeNotFoundException("Personaje could not be found"));
        personaje.setEdad(personajeDTO.getEdad());
        personaje.setHistoria(personajeDTO.getHistoria());
        personaje.setImagen(personajeDTO.getImagen());
        personaje.setPeso(personajeDTO.getPeso());
        personaje.setNombre(personajeDTO.getNombre());

        Personaje savedPersonaje = this.personajeRepository.save(personaje);

        return PersonajeMapper.mapToDTO(savedPersonaje);
    }
}
