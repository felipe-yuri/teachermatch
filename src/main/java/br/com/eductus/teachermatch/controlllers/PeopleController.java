package br.com.eductus.teachermatch.controlllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.eductus.teachermatch.entities.People;
import br.com.eductus.teachermatch.repositories.PeopleRepository;

@Controller
public class PeopleController {
	
	@Autowired
	private PeopleRepository repository;
	
	@GetMapping(value = "/")
	public String index() {
		return "index";
	}
	
	@GetMapping(value = "/register")
	public String register() {
		return "register";
	}
	
	@GetMapping(value = "/people")
	public String listPeople(Model model) {
		Iterable<People> listPeople = repository.findAll();
		model.addAttribute("listPeople", listPeople);
		return "listPeople";
	}
	
	@PostMapping(value = "/people/save")
	public String savePeople(
			@RequestParam String name,
			@RequestParam String cpf,
			@RequestParam String email,
			@RequestParam int age,
			@RequestParam boolean isteacher,
			Model model) {
		
		cpf = cpf.replaceAll("[./-]", "");
		People people = new People(null, Long.parseLong(cpf), name, email, age, isteacher);
		repository.save(people);
		
		return listPeople(model);
		
	}
	
	@GetMapping(value = "/people/{name}")
	public ResponseEntity<List<People>> findByName(@PathVariable String name){
		List<People> listPeople = new ArrayList<People>();
		listPeople = repository.findByName(name);
		return ResponseEntity.ok().body(listPeople);
	}
}
