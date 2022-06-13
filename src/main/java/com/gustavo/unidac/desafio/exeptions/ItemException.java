package com.gustavo.unidac.desafio.exeptions;

public class ItemException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ItemException() {
		super("Item in use by another user.");
	}
}