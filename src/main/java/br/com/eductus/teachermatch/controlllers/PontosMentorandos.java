package br.com.eductus.teachermatch.controlllers;

import br.com.eductus.teachermatch.entities.FormMentorandos;

public class PontosMentorandos {
	
	public int calcularPontuacao(FormMentorandos form) {
		int temEad = Integer.parseInt(form.getQuestao1());
		int concluiuEnsinoMedio = Integer.parseInt(form.getQuestao2A());
		int estudaEmEscolaPublica = Integer.parseInt(form.getQuestao4());
		int fazCursinho = Integer.parseInt(form.getQuestao5());
		int temDisponibilidade = Integer.parseInt(form.getQuestao10());
		int nivelDificuldade = Integer.parseInt(form.getQuestao12());
		
		if (temEad == 1) {temEad = 1;}
		else {temEad = 0;}
		
		if (concluiuEnsinoMedio == 1) {concluiuEnsinoMedio = 1;}
		else {concluiuEnsinoMedio = 1;}
		
		if (estudaEmEscolaPublica == 4) {estudaEmEscolaPublica = 2;}
		else {estudaEmEscolaPublica = 0;}
		
		if (fazCursinho == 2) {fazCursinho = 1;}
		else {fazCursinho = 0;}
		
		if (temDisponibilidade == 1) {temDisponibilidade = 1;}
		else {temDisponibilidade = 0;}
		
		
		return temEad + concluiuEnsinoMedio + estudaEmEscolaPublica + fazCursinho + temDisponibilidade + nivelDificuldade;
	}

}
