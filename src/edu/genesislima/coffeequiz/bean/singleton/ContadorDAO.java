package edu.genesislima.coffeequiz.bean.singleton;

import javax.ejb.Singleton;

import edu.genesislima.coffeequiz.dao.ContadorDao;
@Singleton
public class ContadorDAO implements ContadorDao {

	
	private int totaldeJogadores = 0;
	
	@Override
	public void salvar(Integer quantidade) {
		totaldeJogadores  = quantidade;

	}

	@Override
	public Integer pegaUltimoValorSalvo() {		
		return totaldeJogadores;
	}

}
