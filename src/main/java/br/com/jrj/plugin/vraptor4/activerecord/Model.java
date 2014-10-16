package br.com.jrj.plugin.vraptor4.activerecord;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Model extends JPABase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4008319660111068101L;

	public void save() {
		_save();
	}
}
	