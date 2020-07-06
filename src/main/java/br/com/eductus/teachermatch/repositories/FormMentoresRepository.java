package br.com.eductus.teachermatch.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.eductus.teachermatch.entities.FormMentores;

public interface FormMentoresRepository  extends CrudRepository<FormMentores, Long>{
	List<FormMentores> findByEmail(String email);
}
