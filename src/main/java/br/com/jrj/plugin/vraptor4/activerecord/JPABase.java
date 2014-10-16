package br.com.jrj.plugin.vraptor4.activerecord;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class JPABase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6342336874415883587L;
	
	@Inject
	private EntityManager em;
	
	protected void _save() {
		em.persist(this);
	}
	
	public EntityManager em() {
		return em;
	}
}
