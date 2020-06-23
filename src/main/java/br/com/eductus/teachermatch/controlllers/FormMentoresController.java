package br.com.eductus.teachermatch.controlllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import br.com.eductus.teachermatch.entities.Cidades;

@Controller
public class FormMentoresController {
	
	@GetMapping(value = "/carregarCidades/{uf}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Cidades carregarCidades(@PathVariable String uf){
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/"+uf+"/distritos?orderBy=nome";
		Cidades listaCidades = restTemplate.getForObject(url, Cidades.class); 
		return listaCidades;
	}

}
