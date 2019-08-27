package edu.genesislima.coffeequiz.bean.stateless;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

import edu.genesislima.coffeequiz.model.CoffeeQuiz;
import edu.genesislima.coffeequiz.repositorio.CoffeeQuizRepositorio;
import edu.genesislima.coffeequiz.service.CoffeeQuizServiceLocal;
import edu.genesislima.coffeequiz.service.CoffeeQuizServiceRemote;
import edu.genesislima.coffeequiz.tests.repositorio.CoffeeQuizException;

@Stateless
public class CoffeeQuizBean implements CoffeeQuizServiceLocal,CoffeeQuizServiceRemote {

	private CoffeeQuizRepositorio coffeeQuizRepositorio = new CoffeeQuizRepositorio();

    @PostConstruct
	private void inicializarListaCoffeeQuiz() {    	
		CoffeeQuiz coffeeQuiz = new CoffeeQuiz();
		coffeeQuiz.setId(1);
		coffeeQuiz.setPergunta("É um café aguado. "
				+ "Ótimo para para tomar em proporções maiores.");
		List<String> respostas = new ArrayList<String>(); 
		 respostas.add("capuccino");
		 respostas.add("mocha");
		 respostas.add("pingado");
		 respostas.add("macchiato");
		 respostas.add("americano");		 
		 coffeeQuiz.setRespostaCorreta("americano");		 
		coffeeQuiz.setAssertivas(respostas);			
		try {
			coffeeQuizRepositorio.salvarCoffeeQuiz(coffeeQuiz);
		} catch (CoffeeQuizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @PreDestroy
    private void limparListaCoffeeQuiz() {
    	coffeeQuizRepositorio.getQuizzes().clear();
    }
    
	
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

	@Override
	public List<CoffeeQuiz> listarTodos() {
		
		return coffeeQuizRepositorio.listarCoffeeQuizzes();
	}
    

//	@PostConstruct
//	private void inicializarListaCoffeeQuiz() throws CoffeeQuizException {    	
//		CoffeeQuiz coffeeQuiz = new CoffeeQuiz();
//		coffeeQuiz.setId(1);
//		coffeeQuiz.setPergunta("Sou servido apenas com o mais puro café."
//				+ "sem qualquer mistura com leite ou outro ingrediente. Possuo uma bela "
//				+ "espuma que se forma sobre mim. Esta espuma é originária do "
//				+ "próprio grão moído, e é produzida no momento da extração. Que tipo de café sou eu? ");
//		List<String> respostas = new ArrayList<String>(); 
//		 respostas.add("capuccino");
//		 respostas.add("mocha");
//		 respostas.add("pingado");
//		 respostas.add("macchiato");
//		 respostas.add("expresso");		 
//		 coffeeQuiz.setRespostaCorreta("expresso");		 
//		coffeeQuiz.setAssertivas(respostas);			
//		coffeeQuizRepositorio.salvarCoffeeQuiz(coffeeQuiz);
//		
//		
//	}
}
