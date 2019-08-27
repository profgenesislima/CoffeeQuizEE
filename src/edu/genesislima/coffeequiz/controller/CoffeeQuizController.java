package edu.genesislima.coffeequiz.controller;

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
		
	public CoffeeQuizServiceLocal getCoffeeQuizBean() {
		return coffeeQuizBean;
	}
	
	public String cadastrarCoffeeQuiz() {		
		FacesContext	facesContext	= FacesContext.getCurrentInstance();
		if(coffeeQuiz!=null) {
		this.coffeeQuizBean.cadastrarCoffeeQuiz(coffeeQuiz);		
		facesContext.addMessage(null,new	FacesMessage("Quiz gravado com sucesso!"));

		return "principal?faces-redirect=true";
		}
		facesContext.addMessage(null,new FacesMessage("Houve um problema ao gravar o quiz! "
				+ "Por favor, tente novamente!"));

		return "cadastrar-coffee-quiz";
	}
}



