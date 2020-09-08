package br.com.eductus.teachermatch.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormMentores implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String email;
	
	private String nome;
	private String telefone;
	private String nascimento;
	private String estado;
	private String cidade;
	private String questao1;
	private String questao2;
	private String questao3A;
	private String questao3B;
	private String questao3C;
	private String questao4;
	private String questao5;
	private String questao6;
	private String questao7;
	private String questao8A; 
	private String questao8B;
	private String questao8C;
	private String questao8D;
	private String questao8E;
	private String questao9;
	private String questao10A; 
	private String questao10B; 
	private String questao11; 
	private String questao12; 
	private String questao13; 
}
