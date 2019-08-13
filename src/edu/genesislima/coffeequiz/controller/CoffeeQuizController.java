package edu.genesislima.coffeequiz.controller;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import edu.genesislima.coffeequiz.bean.CoffeeQuizBean;
import edu.genesislima.coffeequiz.dao.CoffeeQuizDaoLocal;
import edu.genesislima.coffeequiz.model.CoffeeQuiz;

@Named
@RequestScoped
public class CoffeeQuizController {

	@EJB
	private CoffeeQuizDaoLocal coffeeQuizBean;
		
	public CoffeeQuizDaoLocal getCoffeeQuizBean() {
		return coffeeQuizBean;
	}
	
	public String cadastrarCoffeeQuiz(CoffeeQuiz coffeeQuiz) {
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



