package edu.genesislima.coffeequiz.bean.singleton;

import java.sql.Date;
import java.time.LocalDate;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.genesislima.coffeequiz.dao.ContadorDao;
import edu.genesislima.coffeequiz.model.Contador;
@Singleton
public class ContadorDAO implements ContadorDao {

	
	//private int totaldeJogadores = 0;
	
	@PersistenceContext
	EntityManager entitymanager;
	
	@Override
	public void salvar(Integer quantidade) {		
		entitymanager.persist(new Contador(quantidade,Date.valueOf(LocalDate.now())));

	}

	@Override
	public Contador pegaUltimoContador() {	
		
		return entitymanager.createQuery("select c from Contador c order by c.id desc",Contador.class).setMaxResults(1).getResultList().get(0);
	}

}
