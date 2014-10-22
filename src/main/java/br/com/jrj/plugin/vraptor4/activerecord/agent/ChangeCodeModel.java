package br.com.jrj.plugin.vraptor4.activerecord.agent;

import java.io.ByteArrayInputStream;
import java.lang.annotation.Annotation;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

//this class will be registered with instrumentation agent
public class ChangeCodeModel implements ClassFileTransformer {
	public byte[] transform(ClassLoader loader, String className,
			Class classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		byte[] byteCode = classfileBuffer;

		// since this transformer will be called when all the classes are
		// loaded by the classloader, we are restricting the instrumentation
		// using if block only for the Lion class

			try {
				ClassPool classPool = ClassPool.getDefault();
				CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(
						classfileBuffer));
				
				System.out.println("Verificando......" + ctClass.getName());
				
		        if (!ctClass.subtypeOf(classPool.get("br.com.jrj.plugin.vraptor4.activerecord.JPABase"))) {
		            return byteCode;
		        }
		        
		        System.out.println("Verificando2......" + ctClass.getName());

		        // Enhance only JPA entities
		      //  if (!hasAnnotation(ctClass, "javax.persistence.Entity")) {
		      //      return byteCode;
		      //  }
		        
				
		        System.out.println("Instrumenting......" + ctClass.getName());
		        
		        String entityName = ctClass.getName();

		    	ctClass.defrost();
		        
		        // count
		        CtMethod count = CtMethod.make("public static long count() { return br.com.jrj.plugin.vraptor4.activerecord.JPQL.instance.count(\"" + entityName + "\"); }", ctClass);
		        ctClass.addMethod(count);

		        // count2
		        CtMethod count2 = CtMethod.make("public static long count(String query, Object[] params) { return br.com.jrj.plugin.vraptor4.activerecord.JPQL.instance.count(\"" + entityName + "\", query, params); }", ctClass);
		        ctClass.addMethod(count2);

		        // findAll
		        CtMethod findAll = CtMethod.make("public static java.util.List findAll() { return br.com.jrj.plugin.vraptor4.activerecord.JPQL.instance.findAll(\"" + entityName + "\"); }", ctClass);
		        ctClass.addMethod(findAll);


		        // create
		     //   CtMethod create = CtMethod.make("public static br.com.jrj.plugin.vraptor4.activerecord.JPABase create(String name, play.mvc.Scope.Params params) { return br.com.jrj.plugin.vraptor4.activerecord.JPQL.instance.create(\"" + entityName + "\", name, params); }", ctClass);
		     //   ctClass.addMethod(create);
		
				byteCode = ctClass.toBytecode();
				ctClass.detach();
				ctClass.toClass();
				System.out.println("Instrumentation complete " +  ctClass.getName() + " .");
			} catch (Throwable ex) {
				System.out.println("Exception: " + ex);
				ex.printStackTrace();
			}

		return byteCode;
	}
	
	
	 protected boolean hasAnnotation(CtClass ctClass, String annotation) throws ClassNotFoundException {
	        for (Object object : ctClass.getAvailableAnnotations()) {
	            Annotation ann = (Annotation) object;
	            if (ann.annotationType().getName().equals(annotation)) {
	                return true;
	            }
	        }
	        return false;
	    }
}

