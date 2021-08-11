package alkemy.challenge.Challenge.Alkemy.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)

public class Pelicula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long peliculaId;
	
	@Column
	private String imagen;
	
	@Column
	private String titulo;
	
	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "MM-dd-yyyy")	
	private LocalDate fechaCreacion;
	
	@Column
	private Long calificacion;
	
	@ManyToMany (mappedBy = "peliculas", fetch = FetchType.LAZY)
	private List<Personaje> personajes;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "genero_id", nullable=false)
	private  Genero genero;

}
