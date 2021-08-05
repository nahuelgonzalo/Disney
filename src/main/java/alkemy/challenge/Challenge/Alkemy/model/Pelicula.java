package alkemy.challenge.Challenge.Alkemy.model;

import java.sql.Date;
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

@Entity
public class Pelicula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String imagen;
	
	@Column
	private String titulo;
	
	@Column
	private Date  fechaCreacion;
	
	@Column
	private Long calificacion;
	
	@ManyToMany (mappedBy = "peliculas", fetch = FetchType.LAZY)
	private List<Personaje> personajes;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "genero_id", nullable=false)
	private  Genero genero;

}
