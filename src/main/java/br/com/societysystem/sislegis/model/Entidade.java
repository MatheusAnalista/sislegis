package br.com.societysystem.sislegis.model;

import java.io.Serializable;

public abstract class Entidade<ID> implements Serializable {

	private static final long serialVersionUID = -6514595694124983739L;

	public abstract ID getId();

}
