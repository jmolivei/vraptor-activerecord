

import static org.junit.Assert.assertNotNull;

import java.io.File;

import javax.inject.Inject;
import javax.persistence.EntityManager;

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
import br.com.jrj.plugin.vraptor4.activerecord.models.Pessoa;

@RunWith(Arquillian.class)
public class JPAA {

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
	EntityManager em;
	 
	@Test
	public void test() {
		
	        assertNotNull(em);
	}
	
	

}
