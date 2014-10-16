package br.com.jrj.plugin.vraptor4.activerecord;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPA {
	@Produces
	@ApplicationScoped
	public EntityManagerFactory criaEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("default");
	}


	@Produces
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
