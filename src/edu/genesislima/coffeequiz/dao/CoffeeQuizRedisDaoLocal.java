package edu.genesislima.coffeequiz.dao;

import java.util.List;

import edu.genesislima.coffeequiz.model.CoffeeQuiz;

public interface CoffeeQuizRedisDaoLocal {

	List<CoffeeQuiz> pegaQuizCache(Integer hashCode);
	void guardaQuizCache(Integer beanHashCode, List<CoffeeQuiz> respondidas);
}
