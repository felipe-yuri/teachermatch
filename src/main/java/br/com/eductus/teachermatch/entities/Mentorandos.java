package br.com.eductus.teachermatch.entities;

import java.util.Date;

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
public class Mentorandos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date dataHora;
	private String mentorando;
	private String email;
	private String telefone;
	private String disciplina;
	private String nivelDificuldade;
	private int pontuacao;
	private String turno;

}
