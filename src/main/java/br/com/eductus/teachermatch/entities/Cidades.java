package br.com.eductus.teachermatch.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cidades {
	
	private Long id;
	private String nome;
	private String municipio;
}
