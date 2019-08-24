package edu.genesislima.coffeequiz.bean.stateful;

import javax.ejb.Local;

import edu.genesislima.coffeequiz.model.CoffeeQuiz;

@Local
public interface CoffeeQuizJogarLocal {

    public CoffeeQuiz pegaQuiz();
	public void corrigeQuiz();
	public void respondeQuiz(CoffeeQuiz coffeeQuiz);
}






