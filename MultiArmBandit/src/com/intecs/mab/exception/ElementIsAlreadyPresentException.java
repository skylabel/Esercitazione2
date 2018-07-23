package com.intecs.mab.exception;

public class ElementIsAlreadyPresentException extends Exception {

	
	public ElementIsAlreadyPresentException(int code) {
		super(String.valueOf(code));
	}

	public ElementIsAlreadyPresentException(String string) {
		super(String.valueOf(string));
	}
	
}
