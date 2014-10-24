package br.com.jrj.plugin.vraptor4.activerecord.models;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.jrj.plugin.vraptor4.activerecord.FabricaJPA;


public class FabricaJPAA {


	 
	@Test
	public void test() {
			EntityManager em = FabricaJPA.getEntityManager();

	        assertNotNull(em);
	}
	
	
	
}
