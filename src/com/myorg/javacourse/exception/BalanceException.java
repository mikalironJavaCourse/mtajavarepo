package com.myorg.javacourse.exception;


@SuppressWarnings("serial")
public class BalanceException extends Exception  {

	public BalanceException(String string) {
		super(string);

	}
	
	public BalanceException() {
		super();

	}
}
