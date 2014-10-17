package br.com.jrj.plugin.vraptor4.activerecord;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FabricaJPA {
	
	private static FabricaJPA instance;
	
	private static EntityManager entityManager=null;
	
   public static EntityManager getEntityManager() {
		 if (instance == null) {
	         instance = new FabricaJPA();
		     EntityManagerFactory factory = Persistence.createEntityManagerFactory("default"); 
		     entityManager = factory.createEntityManager();
		 }
		 return entityManager;
		  	  	
		 
	   }

}
