package com.imas.axsos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.imas.axsos.models.Dojo;
import com.imas.axsos.repositories.DojoRepository;

@Service
public class DojoService {
	private final DojoRepository dojoRepository;
    
    public DojoService(DojoRepository dojoRepository) {
        this.dojoRepository = dojoRepository;
    }
    public List<Dojo> allDojos() {
        return dojoRepository.findAll();
    }
    
    public Dojo createDojo(Dojo b) {
        return dojoRepository.save(b);
    }
    public Dojo findDojo(Long id) {
        Optional<Dojo> optionalDojo = dojoRepository.findById(id);
        if(optionalDojo.isPresent()) {
            return optionalDojo.get();
        } else {
            return null;
        }
    }

}