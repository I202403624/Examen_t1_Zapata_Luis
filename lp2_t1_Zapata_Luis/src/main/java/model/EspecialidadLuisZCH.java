package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "tbl_especialidad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EspecialidadLuisZCH {
	
	
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_especialidad")
	    private Integer idEspecialidad;

	    @Column(name = "titulo", nullable = false, length = 50)
	    private String titulo;
	

}
