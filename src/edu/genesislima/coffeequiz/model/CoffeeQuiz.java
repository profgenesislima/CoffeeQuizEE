package edu.genesislima.coffeequiz.model;

import java.util.List;

public class CoffeeQuiz {

	private int id;
	private String pergunta;
	private List<String> assertivas;
	private String resposta;
	private String respostaCorreta;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPergunta() {
		return pergunta;
	}
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}


	public List<String> getAssertivas() {
		return assertivas;
	}
	public void setAssertivas(List<String> assertivas) {
		this.assertivas = assertivas;
	}
	public String getRespostaCorreta() {
		return respostaCorreta;
	}
	public void setRespostaCorreta(String respostaCorreta) {
		this.respostaCorreta = respostaCorreta;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoffeeQuiz other = (CoffeeQuiz) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
	
	
}
