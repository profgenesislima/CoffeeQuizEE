package edu.genesislima.coffeequiz.bean.stateful;

import javax.ejb.Remote;

import edu.genesislima.coffeequiz.model.CoffeeQuiz;

@Remote
public interface CoffeeQuizJogarRemote {

	public CoffeeQuiz pegaQuiz(); 
	public void corrigeQuiz();
	public void respondeQuiz(CoffeeQuiz coffeeQuiz);
	 
}



