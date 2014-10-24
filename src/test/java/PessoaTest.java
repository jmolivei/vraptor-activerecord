

import static org.junit.Assert.assertNotNull;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import br.com.jrj.plugin.vraptor4.activerecord.JPA;
import br.com.jrj.plugin.vraptor4.activerecord.JPABase;
import br.com.jrj.plugin.vraptor4.activerecord.Model;
import br.com.jrj.plugin.vraptor4.activerecord.models.Pessoa;


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

	@Test
	public void insertTest() {
		
				   
	        pessoa.setNome("JOAO LUIS MOREIRA");
	        pessoa.save();
	 
	        System.out.println(pessoa.getId());

	        List<Pessoa> pessoas =  Pessoa.findAll();

	        assertNotNull(pessoas);
	}
	
	@Test
	public void deleteTest() {
		

			pessoa =  Pessoa.find("id", 1L).first();
	        
 			pessoa.delete();
	        
	        List<Pessoa> pessoas =  Pessoa.findAll();
        
	        Assert.assertEquals(0, pessoas.size());
	}
	
	

}
