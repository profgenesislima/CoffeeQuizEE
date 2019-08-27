package edu.genesislima.coffeequiz.bean.stateful;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	private final Logger LOGGER  = Logger.getLogger("CoffeeQuizJogar.class");
	
	@PostConstruct
	private void inicializaListaQuiz() {
		contadorDeJogadoresBean.incrementaContador();
		this.quizzes = coffeeQuizBean.listarTodos();
		this.respondidas = new ArrayList<CoffeeQuiz>();}
	
	@PreDestroy
	private void limpaListaQuiz() {		
		this.quizzes.clear();
		this.respondidas.clear();}
	
	@PrePassivate	
	private void abandonaJogo() {
		LOGGER.log(Level.INFO, LocalDate.now().
		format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+""
    	+ " O jogador respondeu "+quizzes.size()+" e parou de jogar às "
    	+pegaHoraAtual());}
	
	@PostActivate
	private void retornaJogo(){
		LOGGER.log(Level.INFO, LocalDate.now()
		.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+""
    	+ " O jogador respondeu "+quizzes.size()+" e retornou ao jogo às "
    	+pegaHoraAtual());}
	
	@Remove
	public void encerraJogo() {
		LOGGER.log(Level.INFO, LocalDate.now()
		.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+""
    	+ " O jogador respondeu "+quizzes.size()+" e abandonou o jogo às "
    	+pegaHoraAtual());}
	
	
	@EJB
	CoffeeQuizContaJogadores contadorDeJogadoresBean;
	
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
	
	private String pegaHoraAtual() {
		return +LocalTime.now().getHour()+":"
		    	+LocalTime.now().getMinute()+":"
		    	+LocalTime.now().getSecond();
	}
	

}
