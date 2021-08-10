package alkemy.challenge.Challenge.Alkemy.dto;


import java.util.List;

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
	
	private List<Pelicula> peliculas;
	
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
	
	
}
