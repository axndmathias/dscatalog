package com.axnd.dscatalog.services.exceptions;

public class EntityNotFoundExecption extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntityNotFoundExecption(String msg) {
		super(msg);

	}
}
