package edu.genesislima.coffeequiz.bean;

import javax.ejb.Stateless;

import edu.genesislima.coffeequiz.dao.CoffeeQuizDaoLocal;
import edu.genesislima.coffeequiz.dao.CoffeeQuizDaoRemote;
import edu.genesislima.coffeequiz.model.CoffeeQuiz;
import edu.genesislima.coffeequiz.repositorio.CoffeeQuizRepositorio;
import edu.genesislima.coffeequiz.tests.repositorio.CoffeeQuizException;

@Stateless
public class CoffeeQuizBean implements CoffeeQuizDaoLocal,CoffeeQuizDaoRemote {

	
//	@PersistenceContext
//	private EntityManager entityManager;
	private CoffeeQuizRepositorio coffeeQuizRepositorio = new CoffeeQuizRepositorio();
	
	public void cadastrarNovoQuiz(CoffeeQuiz coffeeQuiz) {
		if (coffeeQuiz.getId() == 0) {
			cadastrarCoffeeQuiz(coffeeQuiz);
			} else {
			atualizarCoffeeQuiz(coffeeQuiz);
			}
	}

	@Override
	public void cadastrarCoffeeQuiz(CoffeeQuiz coffeeQuiz) {
		try {
			coffeeQuizRepositorio.salvarCoffeeQuiz(coffeeQuiz);
		} catch (CoffeeQuizException e) {		 
			e.printStackTrace();
		}
		
	}

	@Override
	public CoffeeQuiz consultarCoffeeQuiz(int coffeeQuizId) throws CoffeeQuizException {
		
		return coffeeQuizRepositorio.pegaQuizPorId(coffeeQuizId);
	}

	@Override
	public void removerCoffeeQuiz(int coffeeQuizId) {
		coffeeQuizRepositorio.removerCoffeeQuiz(coffeeQuizId);
		
	}

	@Override
	public void atualizarCoffeeQuiz(CoffeeQuiz coffeeQuiz) {
		coffeeQuizRepositorio.atualizarCoffeeQuiz(coffeeQuiz);
		
	}
    
    
	
	
}
