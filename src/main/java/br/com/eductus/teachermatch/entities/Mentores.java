package br.com.eductus.teachermatch.entities;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mentores {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Instant dataHora;
	private String mentor;
	private String email;
	private String whatapp;
	private String disciplina;
	private int capacidadeMentorar;
	private int cargaHorariaDisponível;
	private char turno;

}
