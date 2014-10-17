package br.com.jrj.plugin.vraptor4.activerecord;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JPABase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6342336874415883587L;

	@Inject
	private EntityManager entityManager;
	
	public EntityManager getEm() {
		return entityManager;
	}

	protected void _save() {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();

		entityManager.persist(this);

		tx.commit();
	}

	protected void _delete() {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();

		entityManager.remove(this);

		tx.commit();
	}

	protected boolean _create() {
		if (!entityManager.contains(this)) {
			_save();
			return true;
		}
		return false;
	}

	protected void _refresh() {
    	entityManager.refresh(this);
    }

	protected void _merge() {
    	entityManager.merge(this);
    }
}
