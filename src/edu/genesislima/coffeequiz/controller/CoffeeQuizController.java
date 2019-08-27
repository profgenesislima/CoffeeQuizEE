package edu.genesislima.coffeequiz.controller;

import java.util.Arrays;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import edu.genesislima.coffeequiz.model.CoffeeQuiz;
import edu.genesislima.coffeequiz.service.CoffeeQuizServiceLocal;

@Named
@RequestScoped
public class CoffeeQuizController {

	@EJB
	private CoffeeQuizServiceLocal coffeeQuizBean;
	
	
	private CoffeeQuiz coffeeQuiz = new CoffeeQuiz();
		
	String[] assertivas = new String[5];
	String respostaCorreta;
	String pergunta;
	
	public CoffeeQuizServiceLocal getCoffeeQuizBean() {
		return coffeeQuizBean;
	}
	
	public String cadastrarCoffeeQuiz() {		
		FacesContext	facesContext	= FacesContext.getCurrentInstance();
		if(coffeeQuiz!=null) {
			coffeeQuiz.setAssertivas(Arrays.asList(assertivas));
			coffeeQuiz.setRespostaCorreta(respostaCorreta);
		this.coffeeQuizBean.cadastrarCoffeeQuiz(coffeeQuiz);		
		facesContext.addMessage(null,new	FacesMessage("Quiz gravado com sucesso!"));

		return "index/?faces-redirect=true";
		}
		facesContext.addMessage(null,new FacesMessage("Houve um problema ao gravar o quiz! "
				+ "Por favor, tente novamente!"));

		return "cadastrar-coffee-quiz";
	}

	public CoffeeQuiz getCoffeeQuiz() {
		return coffeeQuiz;
	}

	public String[] getAssertivas() {
		return assertivas;
	}

	public void setRespostaCorreta(String respostaCorreta) {
		this.respostaCorreta = respostaCorreta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	

	public String getPergunta() {
		return pergunta;
	}

	public String getRespostaCorreta() {
		return respostaCorreta;
	}
	
	
}



