package edu.genesislima.coffeequiz.bean.singleton;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.DependsOn;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.genesislima.coffeequiz.model.Contador;


@Singleton
@DependsOn("CoffeeQuizContaJogadores")
public class CoffeeQuizContaJogadoresLogger {

	@Inject
	CoffeeQuizContaJogadores contaJogadores;
	
	@PersistenceContext
	EntityManager entityManager;
	
	private final Logger LOGGER  = Logger.getLogger("CoffeeQuizPlayerCount.class");
	
	@Schedule(dayOfWeek="*", hour = "23", minute = "59", info = "Registra jogadores diariamente.")
    private void registraJogadoresPorDia() {   	 
   	 LOGGER.log(Level.INFO, LocalDate.now().
     format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+""
     + " Quantidade de jogadores diários: "+contaJogadores.getTotaldeJogadores());
    }
	
	
	
	
	@PreDestroy
    private void registraJogadoresOnLine() {
   	 entityManager.persist(new Contador(contaJogadores.
     getTotaldeJogadores(), Date.valueOf(LocalDate.now())));
    
    }
	
	public int pegaTotalJogadores() {
		return entityManager.createQuery("select sum(c.total)"
		 + " from Contador c ", Integer.class).getSingleResult();
	}
	
	 //LOGGER.log(Level.INFO, LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+""
	   	 		//+ " Quantidade de jogadores ao finalizar a aplicação "+contaJogadores.getTotaldeJogadores());
	
	
	
	
	
     @PostConstruct
     private void inicializaLog() {
//    	 LOGGER.log(Level.INFO, LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+""
//    	 		+ " Quantidade de jogadores ao inicializar a aplicação "+contaJogadores.getTotaldeJogadores());
    	 
     }
	
     
     
}
