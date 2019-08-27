package edu.genesislima.coffeequiz.bean.stateful;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import edu.genesislima.coffeequiz.bean.singleton.CoffeeQuizContaJogadores;
import edu.genesislima.coffeequiz.model.CoffeeQuiz;
import edu.genesislima.coffeequiz.service.CoffeeQuizServiceLocal;

@Local({CoffeeQuizJogarLocal.class})
@Remote({CoffeeQuizJogarRemote.class})
@Stateful
public class CoffeeQuizJogar {	
	
	@EJB
	CoffeeQuizContaJogadores contadorDeJogadoresBean;
	
	@PostConstruct
	private void inicializaListaQuiz() {
		contadorDeJogadoresBean.incrementaContador();
		this.quizzes = coffeeQuizBean.listarTodos();
		this.respondidas = new ArrayList<CoffeeQuiz>();
	}
	
	@PreDestroy
	private void limpaListaQuiz() {		
		this.quizzes.clear();
		this.respondidas.clear();
	}
	
	@PrePassivate	
	private void abandonaJogo() {
		System.out.println("O jogador respondeu "+quizzes.size()+" perguntas e foi tomar um café! :)");
	}
	
	@PostActivate
	private void retornaJogo(){
		System.out.println("O jogador respondeu "+quizzes.size()+" e voltou do café! :p");
	}
	
	@Remove
	public void encerraJogo() {
		System.out.println("O total de pontos do jogador foi "+score);
	}
	
	
	@EJB
	CoffeeQuizServiceLocal coffeeQuizBean;

	private List<CoffeeQuiz> quizzes;
	
	private List<CoffeeQuiz> respondidas;
	
	private int score;
	
	
	public CoffeeQuiz pegaQuiz() {
		double randomQuiz = Math.random();
		randomQuiz = randomQuiz * quizzes.size() + 1;
		CoffeeQuiz coffeeQuiz = quizzes.get((int)randomQuiz);		
		return coffeeQuiz;
	}
		
	
	public void respondeQuiz(CoffeeQuiz coffeeQuiz) {
		this.respondidas.add(coffeeQuiz);
		corrigeQuiz();		
		removeQuizDaLista(quizzes,coffeeQuiz);
	}
	
	public void corrigeQuiz() {		
		for(CoffeeQuiz resposta: respondidas) {
			if(resposta.getRespostaCorreta().equalsIgnoreCase(resposta.getResposta())) {
				score++;
			}	   
		}
		 
	}
	
	private void removeQuizDaLista(List<CoffeeQuiz> lista, CoffeeQuiz coffeeQuiz) {
		lista.remove(coffeeQuiz);
	}


	public int getScore() {
		return score;
	}
	
	
	

}
