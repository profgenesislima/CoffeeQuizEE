package edu.genesislima.coffeequiz.dao;

import javax.ejb.Local;

import edu.genesislima.coffeequiz.model.Contador;

@Local
public interface ContadorDao {

	public void salvar(Integer entity);
	
	public Contador pegaUltimoContador();
}
