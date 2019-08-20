package edu.genesislima.coffeequiz.bean.stateful;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import edu.genesislima.coffeequiz.dao.CoffeeQuizDaoLocal;
import edu.genesislima.coffeequiz.model.CoffeeQuiz;

@Stateful(name="JogarCoffeeQuiz")
public class CoffeeQuizJogar {
	
	@EJB
	CoffeeQuizDaoLocal coffeeQuizBean;
		
	private List<CoffeeQuiz> quizzes = coffeeQuizBean.listarTodos();
	
	private List<CoffeeQuiz> respondidas = new ArrayList<CoffeeQuiz>();
	
	private int score;
	
	public CoffeeQuiz pegaQuiz() {
		double randomQuiz = Math.random();
		randomQuiz = randomQuiz * quizzes.size() + 1;
		CoffeeQuiz coffeeQuiz = quizzes.get((int)randomQuiz);
		removeQuizDaLista(quizzes,coffeeQuiz);
		return coffeeQuiz;
	}
		
	
	public void respondeQuiz(CoffeeQuiz coffeeQuiz) {
		this.respondidas.add(coffeeQuiz);
	}
	
	public void corrigeQuiz() {		
		for(CoffeeQuiz resposta: respondidas) {
			if(resposta.getRespostaCorreta().equalsIgnoreCase(resposta.getResposta())) {
				score++;
			}
	     // quizzes.stream().forEach((q-> q.getRespostaCorreta().equals(coffeeQuiz.getRespostaCorreta()))); 
	   
		}
		 
	}
	
	private void removeQuizDaLista(List<CoffeeQuiz> lista, CoffeeQuiz coffeeQuiz) {
		lista.remove(coffeeQuiz);
	}


	public int getScore() {
		return score;
	}
	
	
	
	

}
