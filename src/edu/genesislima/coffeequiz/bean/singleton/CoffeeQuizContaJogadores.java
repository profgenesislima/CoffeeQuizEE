package edu.genesislima.coffeequiz.bean.singleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import edu.genesislima.coffeequiz.dao.ContadorDao;

@Singleton(name = "CoffeeQuizContaJogadores")
@Startup
public class CoffeeQuizContaJogadores {

	@EJB
	private ContadorDao contadorDAO;
	
	private int contadordeJogadoresOnline = 0;
	private int totaldeJogadores = 0;
	
	@Lock(LockType.WRITE)
	public void incrementaContador() {
		contadordeJogadoresOnline++;
		totaldeJogadores++;
	}
	
	@Lock(LockType.READ)
	public int contajogadoresOnLine() {
		return contadordeJogadoresOnline;		
	}
	
	public void reiniciarContador() {
		contadordeJogadoresOnline = 0;
	}
	
	
	public int getTotaldeJogadores() {
		return totaldeJogadores;
	}

	@PostConstruct
	private void inicializarContador() {
		reiniciarContador();
		totaldeJogadores = contadorDAO.pegaUltimoValorSalvo();
	
	}
	
	@PreDestroy
	private void finalizarContador() {
		contadorDAO.salvar(totaldeJogadores);
	}
}
