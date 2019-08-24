package edu.genesislima.coffeequiz.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;

import edu.genesislima.coffeequiz.model.CoffeeQuiz;

@Stateful
public class CoffeeQuizRedisDao {

	Map<Integer,List<CoffeeQuiz>> cache = new HashMap<Integer,List<CoffeeQuiz>>();
	
	public List<CoffeeQuiz> pegaQuizCache(Integer hashCode){
		return cache.get(hashCode);
	}
	
	public void salvaQuizCache(Integer beanHashCode, List<CoffeeQuiz> respondidas) {
		cache.put(beanHashCode, respondidas);
	}
}
