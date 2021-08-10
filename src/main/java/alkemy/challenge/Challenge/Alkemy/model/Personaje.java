package alkemy.challenge.Challenge.Alkemy.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
public class Personaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long personajeId;
	
	@Column
	private String imagen;
	
	@Column
	private String nombre;
	
	@Column
	private Long edad;
	
	@Column
	private Long peso;
	
	@Column
	private String historia;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "personaje_pelicula",
	joinColumns = @JoinColumn(name = "personaje_id"),
	inverseJoinColumns = @JoinColumn(name = "pelicula_id"))
	private List<Pelicula> peliculas;

	
	
	
	
}
