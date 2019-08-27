package edu.genesislima.coffeequiz.repositorio;

import java.util.ArrayList;
import java.util.List;

import edu.genesislima.coffeequiz.model.CoffeeQuiz;
import edu.genesislima.coffeequiz.tests.repositorio.CoffeeQuizException;

public class CoffeeQuizRepositorio {

	private List<CoffeeQuiz> quizzes = new ArrayList<>();
	
	public void salvarCoffeeQuiz(CoffeeQuiz coffeeQuiz) throws CoffeeQuizException {
		if(verificaQuantidadeRespostas(coffeeQuiz))
		quizzes.add(coffeeQuiz);
	}
	
	public void atualizarCoffeeQuiz(CoffeeQuiz coffeeQuiz) {
		for(int i=0; i< quizzes.size(); i++) {
			
			if(quizzes.get(i).getId() ==  coffeeQuiz.getId()) {
			   	quizzes.get(i).setPergunta(coffeeQuiz.getPergunta());
			   	quizzes.get(i).setAssertivas(coffeeQuiz.getAssertivas());

			}
		}
	}
	
	public void removerCoffeeQuiz(int id) {
		for(int i=0; i < quizzes.size(); i++) {
			if(quizzes.get(i).getId() == id) {			
				quizzes.remove(i);
			}
		}
	}
	
	public List<CoffeeQuiz> listarCoffeeQuizzes(){
		return quizzes;
	}
	
	public CoffeeQuiz pegaQuizPorId(int id) throws CoffeeQuizException {
		for(int i=0; i < quizzes.size(); i++) {
			if(id == quizzes.get(i).getId())
				return quizzes.get(i);
		}		
		throw new CoffeeQuizException("Nenhum Quiz Encontrado para este Id!");
	}
	
	private boolean verificaQuantidadeRespostas(CoffeeQuiz coffeeQuiz) throws CoffeeQuizException {
		
		if(coffeeQuiz.getAssertivas().size() == 5) {
			return true;
		}
		
		if(coffeeQuiz.getAssertivas().size() < 5 || coffeeQuiz.getAssertivas().size() > 5) {			
			throw new CoffeeQuizException("Você deve fornecer 5 respostas para o Quiz. "
					+ "Somente "+coffeeQuiz.getAssertivas().size()+" respostas foram fornecidas."
					);			
		}
		return false;
	}

	public List<CoffeeQuiz> getQuizzes() {
		return quizzes;
	}
	
	

	
}
