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
	
	private static EntityManager em;
	
	public JPA() {
		
	}
	
	@Produces
	@ApplicationScoped
	public EntityManagerFactory criaEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("default");
	}


	@Produces
	@RequestScoped
	public  EntityManager
	criaEntityManager(EntityManagerFactory factory) {
		em = factory.createEntityManager();
		return em;
	}

 	public void fechaManager(@Disposes EntityManager manager) {
		manager.close();
	}

	public void
	fechaFactory(@Disposes EntityManagerFactory factory) {
		factory.close();
	}  

	public static EntityManager em() {
		// TODO Auto-generated method stub
		return em;
		
	}
	
	
}
