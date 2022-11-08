package com.imas.axsos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.imas.axsos.models.Ninja;
import com.imas.axsos.repositories.NinjaRepository;

@Service
public class NinjaService {
	public final NinjaRepository ninjaRepository;
	
	public NinjaService(NinjaRepository ninjaRepository) {
		this.ninjaRepository=ninjaRepository;
	}
	
	public List<Ninja> allNinjas() {
        return ninjaRepository.findAll();
    }
	public Ninja createNinja(Ninja b) {
        return ninjaRepository.save(b);
    }
	
	public Ninja findNinja(Long id) {
        Optional<Ninja> optionalNinja = ninjaRepository.findById(id);
        if(optionalNinja.isPresent()) {
            return optionalNinja.get();
        } else {
            return null;
        }
    }

}