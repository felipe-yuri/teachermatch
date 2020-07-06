package br.com.eductus.teachermatch.controlllers;

import org.springframework.stereotype.Controller;

import br.com.eductus.teachermatch.entities.FormMentores;

@Controller
public class PontosMentores {
	
	private int capacidadeMentorar = 0;
	private boolean notasAtendem = false;
	
	public boolean validarRequisitos(FormMentores form) {
		if (Float.parseFloat(form.getQuestao8A()) < 620) {
			return notasAtendem = false;
		}
		if (Float.parseFloat(form.getQuestao8B()) < 750) {
			return notasAtendem = false;
		}
		if (Float.parseFloat(form.getQuestao8C()) < 630) {
			return notasAtendem = false;
		}
		if (Float.parseFloat(form.getQuestao8D()) < 660) {
			return notasAtendem = false;
		}
		if (Float.parseFloat(form.getQuestao8E()) < 840) {
			return notasAtendem = false;
		}
		return notasAtendem = true;
	}
	
	public int calcularCapacidadeMentorar(FormMentores form) {
		
		if ("2".equals(form.getQuestao3A())) {
			capacidadeMentorar = 5;
		}
		if (Float.parseFloat(form.getQuestao3B()) >= 5 && notasAtendem == true && "1".equals(form.getQuestao1())) {
			capacidadeMentorar = 4;
		}
		else if (Float.parseFloat(form.getQuestao3B()) >= 5 && notasAtendem == true ) {
			capacidadeMentorar = 3;
		}
		else if (Float.parseFloat(form.getQuestao3B()) >= 5 && notasAtendem == false ) {
			capacidadeMentorar = 2;
		}
		else if (Float.parseFloat(form.getQuestao3B()) < 5 && notasAtendem == true ) {
			capacidadeMentorar = 2;
		}
		else if (Float.parseFloat(form.getQuestao3B()) < 5 && notasAtendem == false) {
			capacidadeMentorar = 1;
		}
		else {
			capacidadeMentorar = 1;
		}
		
		return capacidadeMentorar;
	}
	
	public int calcularPontos(FormMentores form) {
		validarRequisitos(form);
		calcularCapacidadeMentorar(form);
		return capacidadeMentorar + Integer.parseInt(form.getQuestao2());
	}

}
