package br.com.jrj.plugin.vraptor4.activerecord.instrumentacao;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.security.CodeSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.jrj.plugin.vraptor4.activerecord.agent.ActiveRecordAgent;

import com.sun.tools.attach.VirtualMachine;

/**
 * @author ganeshs
 *
 */
public class ActiveRecordAgentLoader {

	private static final Logger logger = LoggerFactory.getLogger(ActiveRecordAgentLoader.class);
	
	private static final ActiveRecordAgentLoader loader = new ActiveRecordAgentLoader();
	
	private ActiveRecordAgentLoader() {
	}
	
	public static ActiveRecordAgentLoader instance() {
		return loader;
	}

    public void loadAgent() {
        logger.info("dynamically loading javaagent");
        String nameOfRunningVM = ManagementFactory.getRuntimeMXBean().getName();
        int p = nameOfRunningVM.indexOf('@');
        String pid = nameOfRunningVM.substring(0, p);
        
        try {
            VirtualMachine vm = VirtualMachine.attach(pid);
            CodeSource codeSource = ActiveRecordAgent.class.getProtectionDomain().getCodeSource();
         //   File file = new File(codeSource.getLocation().toURI().getPath());
         //   String path = "";
            vm.loadAgent("C:/Desenvolvimento/workspace/vraptor4-activerecord/lib/vraptor4-activerecord-agent-0.0.1-SNAPSHOT.jar","");
            vm.detach();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
