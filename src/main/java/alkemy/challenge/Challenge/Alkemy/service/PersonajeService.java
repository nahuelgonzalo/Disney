package alkemy.challenge.Challenge.Alkemy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alkemy.challenge.Challenge.Alkemy.dto.PersonajeDTO;
import alkemy.challenge.Challenge.Alkemy.model.Personaje;
import alkemy.challenge.Challenge.Alkemy.repository.PersonajeRepository;

@Service
@Transactional
public class PersonajeService {
	
	@Autowired
	PersonajeRepository personajeRepository;
	
	public List<PersonajeDTO> searchPersonaje(Map<String, String> queryParam){
		List<PersonajeDTO> result = new ArrayList<>();
		PersonajeDTO personaje1 = PersonajeDTO.builder().nombre("Nahuel").imagen("FOTO").build();
		PersonajeDTO personaje2 = PersonajeDTO.builder().nombre("Juan").imagen("FOTO").build();
		PersonajeDTO personaje3 = PersonajeDTO.builder().nombre("Gabriela").imagen("FOTO").build();
		result.add(personaje1);
		result.add(personaje2);
		result.add(personaje3);
		return result;
	}
	
	public Boolean createPersonaje(PersonajeDTO personajeDTO) {
		Personaje personaje = personajeDTO.buildEntity();
		personajeRepository.saveAndFlush(personaje);
		return true;
	}
	
	public Boolean updatePersonaje(Long id, PersonajeDTO personajeDTO) {
		Personaje personaje = personajeDTO.buildIdEntity(id);
		personajeRepository.saveAndFlush(personaje);
		return true;
	}
	
	public Boolean deletePersonaje(Long id) {
		personajeRepository.deleteById(id);
		return true;
	}
	
	public PersonajeDTO getPersonaje(Long id) {
		Personaje personajeEntity = personajeRepository.getById(id);
		return PersonajeDTO.from(personajeEntity);
	}
	
}
