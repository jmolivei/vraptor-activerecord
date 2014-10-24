package br.com.jrj.plugin.vraptor4.activerecord.instrumentacao;

import javax.enterprise.context.ApplicationScoped;

import br.com.jrj.plugin.vraptor4.activerecord.agent.ActiveRecordAgentLoader;


@ApplicationScoped
public class Instrumentador {
	
	public void start() throws Exception {
		ActiveRecordAgentLoader.instance().loadAgent();
	}

}
