package alkemy.challenge.Challenge.Alkemy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alkemy.challenge.Challenge.Alkemy.dto.PeliculaDTO;
import alkemy.challenge.Challenge.Alkemy.model.Pelicula;
import alkemy.challenge.Challenge.Alkemy.repository.PeliculaRepository;

@Service
public class PeliculaService {

	@Autowired
	PeliculaRepository peliculaRepository;
	
	public List<PeliculaDTO> searchPelicula(Map<String, String> queryParam){
		List<PeliculaDTO> result = new ArrayList<>();
		LocalDate date1 = LocalDate.parse("2018-10-30");
		LocalDate date2 = LocalDate.parse("2019-11-20");
		LocalDate date3 = LocalDate.parse("2016-12-10");
		PeliculaDTO pelicula1 = PeliculaDTO.builder().imagen("FOTO").titulo("Rapido y Furioso").fechaCreacion(date1).build();
		PeliculaDTO pelicula2 = PeliculaDTO.builder().imagen("FOTO").titulo("Kong").fechaCreacion(date2).build();
		PeliculaDTO pelicula3 = PeliculaDTO.builder().imagen("FOTO").titulo("Spiderman").fechaCreacion(date3).build();
		result.add(pelicula1);
		result.add(pelicula2);
		result.add(pelicula3);
		return result;
	}
	
	public Boolean createPelicula(PeliculaDTO peliculaDTO) {
		Pelicula pelicula = peliculaDTO.buildEntity();
		peliculaRepository.saveAndFlush(pelicula);
		return true;
	}
	
	public Boolean updatePelicula(Long id, PeliculaDTO peliculaDTO) {
		Pelicula pelicula = peliculaDTO.buildIdEntity(id);
		peliculaRepository.saveAndFlush(pelicula);
		return true;
	}
	
	public Boolean deletePelicula(Long id) {
		peliculaRepository.deleteById(id);
		return true;
	}
	
	public PeliculaDTO getPelicula(Long id) {
		Pelicula peliculaEntity = peliculaRepository.getById(id);
		return PeliculaDTO.from(peliculaEntity);
	}
}
