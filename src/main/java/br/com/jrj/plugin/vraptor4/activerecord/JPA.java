package br.com.jrj.plugin.vraptor4.activerecord;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JPA {
	
	public JPA() {
		
	}
	
	@Produces
	@ApplicationScoped
	public EntityManagerFactory criaEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("default");
	}


	@Produces
	@RequestScoped
	public EntityManager
	criaEntityManager(EntityManagerFactory factory) {
		
		return factory.createEntityManager();
	}

	public void fechaManager(@Disposes EntityManager manager) {
		manager.close();
	}

	public void
	fechaFactory(@Disposes EntityManagerFactory factory) {
		factory.close();
	}
	
	
}
