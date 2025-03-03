package edu.genesislima.coffeequiz.tests.repositorio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
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
		coffeeQuiz.setPergunta("Sou servido apenas com o mais puro caf�."
				+ "sem qualquer mistura com leite ou outro ingrediente. Possuo uma bela "
				+ "espuma que se forma sobre mim. Esta espuma � origin�ria do "
				+ "pr�prio gr�o mo�do, e � produzida no momento da extra��o. Que tipo de caf� sou eu? ");
		ArrayList<String> respostas = new ArrayList<String>(); 
		 respostas.add("capuccino");
		 respostas.add("mocha");
		 respostas.add("pingado");
		 respostas.add("macchiato");
		 respostas.add("expresso");
		 
		 coffeeQuiz.setRespostaCorreta("expresso");
		 
		coffeeQuiz.setAssertivas(respostas);
		
		coffeeQuiz.setResposta("e");
		
		
		
		
		repo.salvarCoffeeQuiz(coffeeQuiz);
		
		CoffeeQuiz cafeComLeite = new CoffeeQuiz();
		cafeComLeite.setId(2);
		cafeComLeite.setPergunta("N�o guardo segredos. Sou servido na mesma propor��o"
				+ "que meu companheiro, fervido e aquecido. Que tipo de caf� sou eu? ");
		ArrayList<String> respostas1 = new ArrayList<String>(); 
		 respostas1.add("capuccino");
		 respostas1.add("mocha");
		 respostas1.add("pingado");
		 respostas1.add("macchiato");
		 respostas1.add("expresso");
		 
		 cafeComLeite.setRespostaCorreta("pingado");
		 
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
			throw new CoffeeQuizException("Voc� deve fornecer 5 respostas para o Quiz. "
					+ "Somente "+coffeeQuiz.getAssertivas().size()+" respostas foram fornecidas."
					);			
		}
		return false;
	}
}
