package edu.genesislima.coffeequiz.service;

import java.util.List;

import javax.ejb.Local;

import edu.genesislima.coffeequiz.model.CoffeeQuiz;

@Local
public interface CoffeeQuizServiceLocal {
	 public void cadastrarCoffeeQuiz(CoffeeQuiz coffeeQuiz);
	 public CoffeeQuiz consultarCoffeeQuiz(int coffeeQuizId) throws Exception;
	 public void removerCoffeeQuiz(int coffeeQuizId);
	 public void atualizarCoffeeQuiz(CoffeeQuiz coffeeQuiz);
	 public List<CoffeeQuiz> listarTodos();
	
}
