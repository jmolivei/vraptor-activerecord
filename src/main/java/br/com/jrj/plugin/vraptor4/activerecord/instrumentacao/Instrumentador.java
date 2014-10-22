package br.com.jrj.plugin.vraptor4.activerecord.instrumentacao;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class Instrumentador {
	
	public void start() throws Exception {
		ActiveRecordAgentLoader.instance().loadAgent();
	}

}
