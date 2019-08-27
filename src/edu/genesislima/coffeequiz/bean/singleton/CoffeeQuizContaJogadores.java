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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.genesislima.coffeequiz.dao.ContadorDao;
import edu.genesislima.coffeequiz.model.Contador;


@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class CoffeeQuizContaJogadores {
     
	@EJB
	private ContadorDao contadorDAO;
	
	@PersistenceContext
	EntityManager entityManager;
	
	private void reiniciarContador() {
		contadordeJogadoresOnline = 0;
	}
	
	@PostConstruct
	private void inicializarContador() {
		reiniciarContador();
		//totaldeJogadores = contadorDAO.pegaUltimoContador().getTotal();
	
	}
	
	@PreDestroy
	private void finalizarContador() {
		contadorDAO.salvar(totaldeJogadores);
	}
	
	
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

	public int getTotaldeJogadores() {
		return totaldeJogadores;
	}

	
	
	
}
