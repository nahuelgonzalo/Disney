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

import alkemy.challenge.Challenge.Alkemy.dto.PeliculaDTO;
import alkemy.challenge.Challenge.Alkemy.service.PeliculaService;


@RestController
@RequestMapping("/movies")
public class PeliculaRest {
	@Autowired
	PeliculaService peliculaService;
	
	@GetMapping
	public ResponseEntity<List<PeliculaDTO>> listarPeliculas(
			@RequestParam(value = "titulo", required = false) Optional<String> titulo,
			@RequestParam(value = "genero", required = false) Optional<String> genero,
			@RequestParam(value = "fechaCreacion", required = false) Optional<String> fechaCreacion){
			
		Map<String, String> queryParam = buildQueryMap(titulo, genero, fechaCreacion);
		List<PeliculaDTO> result = peliculaService.searchPelicula(queryParam);
		return ResponseEntity.ok(result);
	}
	
	
	@PostMapping
	public ResponseEntity<PeliculaDTO> crearPelicula(@RequestBody PeliculaDTO pelicula){
		if(peliculaService.createPelicula(pelicula)) {
			return ResponseEntity.status(HttpStatus.CREATED).body(pelicula);
		}
		return ResponseEntity.status(400).body(pelicula);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PeliculaDTO> modificarPelicula(@PathParam("id") Long id,
			@RequestBody PeliculaDTO pelicula){
		if(peliculaService.updatePelicula(id, pelicula)) {
			return ResponseEntity.ok(pelicula);
			}
		return ResponseEntity.status(400).body(pelicula);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPelicula(@PathParam("id") Long id){
		if (peliculaService.deletePelicula(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(400).build();
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<PeliculaDTO> getPelicula(@PathParam("id") Long id){
		PeliculaDTO pelicula = peliculaService.getPelicula(id);
		return ResponseEntity.ok(pelicula);
	}
	
	private Map<String, String> buildQueryMap(Optional<String> oTitulo, Optional<String> oGenero, Optional<String> oFechaCreacion){
		Map<String, String> queryParam = new HashMap<>();
		oTitulo.ifPresent(titulo -> queryParam.put("titulo", titulo));
		oGenero.ifPresent(genero -> queryParam.put("genero", genero));
		oFechaCreacion.ifPresent(fechaCreacion -> queryParam.put("fechaCreacion", fechaCreacion));
		return queryParam;
	}
}
