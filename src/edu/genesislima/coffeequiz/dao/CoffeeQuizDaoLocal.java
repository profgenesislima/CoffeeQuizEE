package edu.genesislima.coffeequiz.dao;

import javax.ejb.Local;

import edu.genesislima.coffeequiz.model.CoffeeQuiz;

@Local
public interface CoffeeQuizDaoLocal {
	 public void cadastrarCoffeeQuiz(CoffeeQuiz coffeeQuiz);
	 public CoffeeQuiz consultarCoffeeQuiz(int coffeeQuizId) throws Exception;
	 public void removerCoffeeQuiz(int coffeeQuizId);
	 public void atualizarCoffeeQuiz(CoffeeQuiz coffeeQuiz);
	
}
