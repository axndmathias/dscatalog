package com.axnd.dscatalog.services.exceptions;

public class ResourceNotFoundExecption extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundExecption(String msg) {
		super(msg);

	}
}
