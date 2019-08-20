package edu.genesislima.coffeequiz.dao;

import javax.ejb.Local;

@Local
public interface ContadorDao {

	public void salvar(Integer entity);
	
	public Integer pegaUltimoValorSalvo();
}
