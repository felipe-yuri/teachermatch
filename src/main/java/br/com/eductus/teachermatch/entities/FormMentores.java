package br.com.eductus.teachermatch.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	private String nome;
	private String telefone;
	private String nascimento;
	private String estado;
	private String cidade;
	private String questao1;
	private Integer questao2;
	private String questao3;
	private String questao4;
	private String questao5;
	private String questao8A; 
	private String questao8B;
	private String questao8C;
	private String questao8D;
	private String questao8E;
	private String questao8F;
	private String questao8G;
	private String questao9;
	private String questao10A; 
	private String questao10B; 
	private String questao11; 
	private boolean questao12A; 
	private boolean questao12B; 
	private boolean questao12C; 
	private boolean questao13A; 
	private boolean questao13B; 
	private boolean questao13C; 
	private boolean questao13D; 
	private boolean questao13E; 
}
