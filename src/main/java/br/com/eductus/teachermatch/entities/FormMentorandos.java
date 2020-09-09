package br.com.eductus.teachermatch.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FormMentorandos implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String email;     
	
	private String nome;      
	private String telefone;  
	private String nascimento;
	private String estado;    
	private String cidade;    
	private String questao1;  
	private String questao1A;  
	private String questao2A;
	private String questao2B;
	private String questao3;  
	private String questao4;  
	private String questao5;  
	private String questao6;  
	private String questao7;  
	private String questao8;  
	private String questao9;  
	private String questao10; 
	private String questao11;
	private String questao12;
	private String questao13;     
	private String questao14;   
	private String questao15;   

}
