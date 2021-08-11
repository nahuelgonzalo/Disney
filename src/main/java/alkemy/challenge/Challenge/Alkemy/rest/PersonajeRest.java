package alkemy.challenge.Challenge.Alkemy.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import alkemy.challenge.Challenge.Alkemy.dto.PersonajeDTO;
import alkemy.challenge.Challenge.Alkemy.service.PersonajeService;

@RestController
@RequestMapping("/characters")
public class PersonajeRest {
	
	@Autowired
	PersonajeService personajeService;
	
	@GetMapping
	public ResponseEntity<List<PersonajeDTO>> listarPersonajes(
			@RequestParam(value = "nombre", required = false) Optional<String> nombre,
			@RequestParam(value = "edad", required = false) Optional<String> edad,
			@RequestParam(value = "peso", required = false) Optional<String> peso,
			@RequestParam(value = "peliculas", required = false) Optional<String> peliculas){
			
		Map<String, String> queryParam = buildQueryMap(nombre, edad, peso, peliculas);
		List<PersonajeDTO> result = personajeService.searchPersonaje(queryParam);
		return ResponseEntity.ok(result);
	}
	
	
	@PostMapping
	public ResponseEntity<PersonajeDTO> crearPersonaje(@RequestBody PersonajeDTO personaje){
		if(personajeService.createPersonaje(personaje)) {
			return ResponseEntity.status(HttpStatus.CREATED).body(personaje);
		}
		return ResponseEntity.status(400).body(personaje);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PersonajeDTO> modificarPersonaje(@PathParam("id") Long id,
			@RequestBody PersonajeDTO personaje){
		if(personajeService.updatePersonaje(id, personaje)) {
			return ResponseEntity.ok(personaje);
			}
		return ResponseEntity.status(400).body(personaje);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPersonaje(@PathParam("id") Long id){
		if (personajeService.deletePersonaje(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(400).build();
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<PersonajeDTO> getPersonaje(@PathParam("id") Long id){
		PersonajeDTO personaje = personajeService.getPersonaje(id);
		return ResponseEntity.ok(personaje);
	}
	
	private Map<String, String> buildQueryMap(Optional<String> oNombre, Optional<String> oEdad, Optional<String> oPeso, Optional<String> oPelicula){
		Map<String, String> queryParam = new HashMap<>();
		oNombre.ifPresent(nombre -> queryParam.put("nombre", nombre));
		oEdad.ifPresent(edad -> queryParam.put("edad", edad));
		oPeso.ifPresent(peso -> queryParam.put("peso", peso));
		oPelicula.ifPresent(pelicula -> queryParam.put("pelicula", pelicula));
		return queryParam;
	}
}
