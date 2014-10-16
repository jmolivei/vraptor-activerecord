package br.com.jrj.plugin.vraptor4.activerecord.models;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.jrj.plugin.vraptor4.activerecord.JPA;
import br.com.jrj.plugin.vraptor4.activerecord.JPABase;
import br.com.jrj.plugin.vraptor4.activerecord.Model;

@RunWith(Arquillian.class)
public class PessoaA {

	 @Deployment
	    public static WebArchive createDeployment() {
	        File[] lib = Maven.resolver()
	                .resolve("org.jboss.weld.servlet:weld-servlet:1.1.9.Final")
	                .withTransitivity().as(File.class);
	         
	        WebArchive jar =  ShrinkWrap.create(WebArchive.class)
	            .addClass(JPA.class)
	            .addClass(JPABase.class)
	            .addClass(Model.class)
	            .addClass(Pessoa.class)
	            .addAsManifestResource("arquillian.xml")
	            .addAsManifestResource(
new File("src/test/resources/META-INF/persistence-teste.xml"),
"persistence.xml")
	            .addAsLibraries(lib)
	            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
	            .setWebXML("web.xml")
	            ;
	         
	        System.out.println(jar.toString(true));
	         
	        return jar;
	    }
	
	
	@Inject
	EntityManager em;
	 
	@Test
	public void test() {
	   
	        Pessoa pessoa = new Pessoa();
	        pessoa.setNome("JOAO LUIS MOREIRA");
	        pessoa.save();
	        System.out.println(pessoa.getId());

	        Pessoa p = (Pessoa) pessoa.em().find(Pessoa.class, 1L);
	        System.out.println(pessoa.getId());

	        assertNotNull(p);
	}
	
	

}
