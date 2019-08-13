package edu.genesislima.ejb.example;

import javax.ejb.Stateless;

@Stateless
public class CalculadoraBean implements CalculadoraBeanLocal{

	@Override
	public int soma(int a, int b) {		
		return a+b;
	}

}
