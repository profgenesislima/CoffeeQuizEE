package edu.genesislima.ejb.example;

import javax.ejb.Local;

@Local
public interface CalculadoraBeanLocal {
	
	int soma(int a, int b);
}
