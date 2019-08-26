package edu.genesislima.coffeequiz.bean.singleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import edu.genesislima.coffeequiz.dao.ContadorDao;


@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class CoffeeQuizContaJogadores {
     
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

	@Lock(LockType.READ)
	public int getTotaldeJogadores() {
		return totaldeJogadores;
	}
	
	@EJB
	private ContadorDao contadorDAO;
	
	private void reiniciarContador() {
		contadordeJogadoresOnline = 0;
	}
	
	

	@PostConstruct
	private void inicializarContador() {
		reiniciarContador();
		totaldeJogadores = contadorDAO.pegaUltimoContador().getTotal();
	
	}
	
	@PreDestroy
	private void finalizarContador() {
		contadorDAO.salvar(totaldeJogadores);
	}
}
