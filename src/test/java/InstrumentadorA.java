

import static org.junit.Assert.*;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.jrj.plugin.vraptor4.activerecord.JPA;
import br.com.jrj.plugin.vraptor4.activerecord.JPABase;
import br.com.jrj.plugin.vraptor4.activerecord.JPQL;
import br.com.jrj.plugin.vraptor4.activerecord.Model;
import br.com.jrj.plugin.vraptor4.activerecord.agent.ActiveRecordAgent;
import br.com.jrj.plugin.vraptor4.activerecord.agent.ActiveRecordAgentLoader;
import br.com.jrj.plugin.vraptor4.activerecord.agent.ChangeCodeModel;
import br.com.jrj.plugin.vraptor4.activerecord.exceptions.JPAQueryException;
import br.com.jrj.plugin.vraptor4.activerecord.models.Pessoa;

@RunWith(Arquillian.class)
public class InstrumentadorA {
	 @Deployment
	    public static JavaArchive createDeployment() {
	         
	        JavaArchive jar =  ShrinkWrap.create(JavaArchive.class)
		            .addClass(JPA.class)
		            .addClass(JPABase.class)
		            .addClass(JPQL.class)
		            .addClass(Model.class)
		            .addClass(JPAQueryException.class)	
		            .addClass(ActiveRecordAgentLoader.class)
		            .addClass(ActiveRecordAgent.class)		
		            .addClass(ChangeCodeModel.class)		            
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
	public void test() throws Exception {
		ClassLoader.getSystemClassLoader().loadClass("br.com.jrj.plugin.vraptor4.activerecord.models.Pessoa");
		ActiveRecordAgentLoader.instance().loadAgent();
	//	assertEquals(null, Pessoa.findAll());
	}
	
	

}
