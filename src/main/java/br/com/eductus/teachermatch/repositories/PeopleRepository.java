package br.com.eductus.teachermatch.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.eductus.teachermatch.entities.People;

public interface PeopleRepository extends CrudRepository<People, Long>{
	
	List<People> findByName(String name);

}
