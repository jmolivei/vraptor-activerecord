package br.com.jrj.plugin.vraptor4.activerecord.instrumentacao;

import static org.junit.Assert.*;

import java.io.File;

import javassist.ClassPool;
import javassist.CtClass;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.jrj.plugin.vraptor4.activerecord.JPA;
import br.com.jrj.plugin.vraptor4.activerecord.JPABase;
import br.com.jrj.plugin.vraptor4.activerecord.JPQL;
import br.com.jrj.plugin.vraptor4.activerecord.Model;
import br.com.jrj.plugin.vraptor4.activerecord.agent.ActiveRecordAgent;
import br.com.jrj.plugin.vraptor4.activerecord.exceptions.JPAQueryException;
import br.com.jrj.plugin.vraptor4.activerecord.models.Pessoa;


public class InstrumentadorTest {


	@Test
	public void test() throws Exception {
//		instrumentacao.start();
//		Thread.sleep(6000L);
		assertEquals(null, Pessoa.findAll());
		
		
	}
	
	

}
