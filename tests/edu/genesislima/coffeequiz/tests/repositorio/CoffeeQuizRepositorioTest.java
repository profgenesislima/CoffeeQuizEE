package edu.genesislima.coffeequiz.tests.repositorio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import edu.genesislima.coffeequiz.model.CoffeeQuiz;
import edu.genesislima.coffeequiz.repositorio.CoffeeQuizRepositorio;

public class CoffeeQuizRepositorioTest {

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	

	public boolean testaRespostaCorreta(CoffeeQuiz pergunta, CoffeeQuiz resposta) {
		return pergunta.getResposta().equals(resposta.getRespostaCorreta());
	}
	
	
	public CoffeeQuizRepositorio registrarQuizTeste() throws CoffeeQuizException {
		CoffeeQuizRepositorio repo = new CoffeeQuizRepositorio();
		CoffeeQuiz coffeeQuiz = new CoffeeQuiz();
		coffeeQuiz.setId(1);
		coffeeQuiz.setPergunta("Sou servido apenas com o mais puro café."
				+ "sem qualquer mistura com leite ou outro ingrediente. Possuo uma bela "
				+ "espuma que se forma sobre mim. Esta espuma é originária do "
				+ "próprio grão moído, e é produzida no momento da extração. Que tipo de café sou eu? ");
		Map<String,String> respostas = new HashMap<String,String>(); 
		 respostas.put("a", "capuccino");
		 respostas.put("b", "mocha");
		 respostas.put("c", "pingado");
		 respostas.put("d", "macchiato");
		 respostas.put("e", "expresso");
		 
		 coffeeQuiz.setRespostaCorreta("e");
		 
		coffeeQuiz.setAssertivas(respostas);
		
		coffeeQuiz.setResposta("e");
		
		
		
		
		repo.salvarCoffeeQuiz(coffeeQuiz);
		
		CoffeeQuiz cafeComLeite = new CoffeeQuiz();
		cafeComLeite.setId(2);
		cafeComLeite.setPergunta("Não guardo segredos. Sou servido na mesma proporção"
				+ "que meu companheiro, fervido e aquecido. Que tipo de café sou eu? ");
		Map<String,String> respostas1 = new HashMap<String,String>(); 
		 respostas1.put("a", "capuccino");
		 respostas1.put("b", "mocha");
		 respostas1.put("c", "pingado");
		 respostas1.put("d", "macchiato");
		 respostas1.put("e", "expresso");
		 
		 cafeComLeite.setRespostaCorreta("c");
		 
		 cafeComLeite.setAssertivas(respostas1);
		
		repo.salvarCoffeeQuiz(cafeComLeite);
		
		
		
		
		return repo;
	}
	
	@Test	
	public void deveSalvarCoffeeQuiz() throws Exception {
		CoffeeQuizRepositorio repo  = registrarQuizTeste();
		assertNotNull(repo.pegaQuizPorId(repo.listarCoffeeQuizzes().size()-1));
		
		
	}
	
	@Test	
	public void quantidadeRespostasDeveSerIgual5() throws CoffeeQuizException {
		
		assertEquals(verificaQuantidadeRespostas(registrarQuizTeste().pegaQuizPorId(1)), true);

	}
	
	@Test	
	public void deveRemoverCoffeeQuiz() throws CoffeeQuizException {
		exceptionRule.expect(CoffeeQuizException.class);
		exceptionRule.expectMessage("Nenhum Quiz Encontrado para este Id!");
		CoffeeQuizRepositorio repo = registrarQuizTeste();
		repo.removerCoffeeQuiz(repo.pegaQuizPorId(2).getId());            
        repo.pegaQuizPorId(2);
		
		
	}
	
	
	
	@Test	
	public void deveListarCoffeeQuiz() throws CoffeeQuizException {
		
		assertTrue(registrarQuizTeste().listarCoffeeQuizzes().size() >= 0);
		
		
		
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
}
