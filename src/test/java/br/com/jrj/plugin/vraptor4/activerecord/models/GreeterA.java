package br.com.jrj.plugin.vraptor4.activerecord.models;

import java.io.File;

import javax.inject.Inject;
 

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
 
@RunWith(Arquillian.class)
public class GreeterA {
     
	   @Deployment
	    public static JavaArchive createDeployment() {
	        return ShrinkWrap.create(JavaArchive.class)
	            .addClass(Greeter.class)
	            .addClass(PhraseBuilder.class)
	            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	    }

	    @Inject
	    Greeter greeter;

	    @Test
	    public void should_create_greeting() {
	        Assert.assertEquals("Hello, Earthling!",
	            greeter.createGreeting("Earthling"));
	        greeter.greet(System.out, "Earthling");
	    }
}