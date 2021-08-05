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


@Entity
public class Personaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
