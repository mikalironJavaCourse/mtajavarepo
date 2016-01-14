package com.myorg.javacourse.exception;

@SuppressWarnings("serial")
public class StockAlreadyExistsException extends Exception {
	
	public StockAlreadyExistsException(String string){
		super(string);
	}

	public StockAlreadyExistsException(){
		super();
	}
}
