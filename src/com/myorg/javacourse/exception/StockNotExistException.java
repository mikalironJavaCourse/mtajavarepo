package com.myorg.javacourse.exception;

public class StockNotExistException extends Exception{

	public  StockNotExistException (String str)
	{
		super(str);
	}
	public  StockNotExistException ()
	{
		super();
	}
}
