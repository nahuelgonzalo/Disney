package alkemy.challenge.Challenge.Alkemy.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import alkemy.challenge.Challenge.Alkemy.model.Genero;
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
public class PeliculaDTO {
	
	private String imagen;
	
	private String titulo;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "MM-dd-yyyy")	
	private LocalDate fechaCreacion;
	
	private Long calificacion;
	
	private List<PersonajeDTO> personajes;
	
	private  Genero genero;
	
	public Pelicula buildEntity() {
		return Pelicula.builder()
				.imagen(this.imagen)
				.titulo(this.titulo)
				.fechaCreacion(this.fechaCreacion)
				.calificacion(this.calificacion)
				.build();
	}
	
	public Pelicula buildIdEntity(Long id) {
		return Pelicula.builder()
				.peliculaId(id)
				.imagen(this.imagen)
				.titulo(this.titulo)
				.fechaCreacion(this.fechaCreacion)
				.calificacion(this.calificacion)
				.build();
	}
	
	public static PeliculaDTO from(Pelicula entity) {
		return PeliculaDTO.builder()
				.imagen(entity.getImagen())
				.titulo(entity.getTitulo())
				.fechaCreacion(entity.getFechaCreacion())
				.calificacion(entity.getCalificacion())
				.build();
	}
	
	public static PeliculaDTO fromWithPersonajes(Pelicula entity) {
		PeliculaDTO peliculaDTO = from(entity);
		peliculaDTO.setPersonajes(getPersonajes(entity.getPersonajes()));
		return peliculaDTO;
	}
	
	private static List<PersonajeDTO> getPersonajes(List<Personaje> entities){
		return entities.stream().map(personaje -> PersonajeDTO.from(personaje)).collect(Collectors.toList());
	}
}
