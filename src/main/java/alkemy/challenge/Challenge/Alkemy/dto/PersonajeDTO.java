package alkemy.challenge.Challenge.Alkemy.dto;


import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import alkemy.challenge.Challenge.Alkemy.model.Pelicula;
import alkemy.challenge.Challenge.Alkemy.model.Personaje;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PersonajeDTO {
	
	private String nombre;
	
	private String imagen;
	
	private Long edad;
	
	private Long peso;
	
	private String historia;
	
	private List<PeliculaDTO> peliculas;
	
	public Personaje buildEntity() {
		return Personaje.builder()
				.nombre(this.nombre)
				.imagen(this.imagen)
				.edad(this.edad)
				.peso(this.peso)
				.historia(this.historia)
				.build();
	}
	
	public Personaje buildIdEntity(Long id) {
		return Personaje.builder()
				.personajeId(id)
				.nombre(this.nombre)
				.imagen(this.imagen)
				.edad(this.edad)
				.peso(this.peso)
				.historia(this.historia)
				.build();
	}
	
	public static PersonajeDTO from(Personaje entity) {
		return PersonajeDTO.builder()
				.nombre(entity.getNombre())
				.imagen(entity.getImagen())
				.edad(entity.getEdad())
				.peso(entity.getPeso())
				.historia(entity.getHistoria())
				.build();
	}
	
	public static PersonajeDTO fromWithPeliculas(Personaje entity) {
		PersonajeDTO personajeDTO = from(entity);
		personajeDTO.setPeliculas(getPeliculas(entity.getPeliculas()));
		return personajeDTO;
	}
	
	private static List<PeliculaDTO> getPeliculas(List<Pelicula> entities){
		return entities.stream().map(pelicula -> PeliculaDTO.from(pelicula)).collect(Collectors.toList());
	}
	
	
}
