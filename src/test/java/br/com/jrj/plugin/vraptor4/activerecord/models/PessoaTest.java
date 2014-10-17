package br.com.jrj.plugin.vraptor4.activerecord.models;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.AssertTrue;

import junit.framework.Assert;

import org.hibernate.AssertionFailure;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.jrj.plugin.vraptor4.activerecord.JPA;
import br.com.jrj.plugin.vraptor4.activerecord.JPABase;
import br.com.jrj.plugin.vraptor4.activerecord.Model;

@RunWith(Arquillian.class)
public class PessoaTest {

	 @Deployment
	    public static JavaArchive createDeployment() {
	         
	        JavaArchive jar =  ShrinkWrap.create(JavaArchive.class)
	            .addClass(JPA.class)
	            .addClass(JPABase.class)
	            .addClass(Model.class)
	            .addClass(Pessoa.class)
	            .addAsManifestResource(
new File("src/test/resources/META-INF/persistence-test.xml"),
"persistence.xml")
	            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	            ;
	         
	        System.out.println(jar.toString(true));
	         
	        return jar;
	    }
	
	@Inject
	Pessoa pessoa;
	
	@Inject
	Pessoa pessoaAux;
	
	@Test
	public void insertTest() {
		
				   
	        pessoa.setNome("JOAO LUIS MOREIRA");
	        pessoa.save();
	 
	        System.out.println(pessoa.getId());

	        Pessoa p = (Pessoa) pessoa.em().find(Pessoa.class, 1L);
	        System.out.println(pessoa.getId());

	        assertNotNull(p);
	}
	
	@Test
	public void deleteTest() {
		

	        pessoaAux =  pessoa.em().find(Pessoa.class, 1L);
	        pessoaAux.setEm(pessoa.em());
	        
	        pessoaAux.delete();
	        
	        Pessoa  p = (Pessoa) pessoa.em().find(Pessoa.class, 1L);
	        
	        Assert.assertEquals(null, p);
	}
	
	

}
