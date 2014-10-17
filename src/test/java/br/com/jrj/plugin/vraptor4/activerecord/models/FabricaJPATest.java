package br.com.jrj.plugin.vraptor4.activerecord.models;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;

import br.com.jrj.plugin.vraptor4.activerecord.FabricaJPA;

public class FabricaJPATest {

	 @Deployment
	    public static JavaArchive createDeployment() {
	         
	        JavaArchive jar =  ShrinkWrap.create(JavaArchive.class)
		            .addClass(FabricaJPA.class)
		            .addClass(Pessoa.class)
	            .addAsManifestResource(
new File("src/test/resources/META-INF/persistence-test.xml"),
"persistence.xml")
	            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	            ;
	         
	        System.out.println(jar.toString(true));
	         
	        return jar;
	    }
	
	 
	@Test
	public void test() {
			EntityManager em = FabricaJPA.getEntityManager();

	        assertNotNull(em);
	}
	
	
	
}
