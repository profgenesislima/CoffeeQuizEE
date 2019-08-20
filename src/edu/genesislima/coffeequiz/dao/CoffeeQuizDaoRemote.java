package edu.genesislima.coffeequiz.dao;

import java.util.List;

import javax.ejb.Remote;

import edu.genesislima.coffeequiz.model.CoffeeQuiz;

@Remote
public interface CoffeeQuizDaoRemote {
	 public void cadastrarCoffeeQuiz(CoffeeQuiz CoffeeQuiz);
	 public CoffeeQuiz consultarCoffeeQuiz(int coffeeQuizId) throws Exception;
	 public void removerCoffeeQuiz(int coffeeQuizId);
	 public void atualizarCoffeeQuiz(CoffeeQuiz coffeeQuiz);
	 public List<CoffeeQuiz> listarTodos();

}
