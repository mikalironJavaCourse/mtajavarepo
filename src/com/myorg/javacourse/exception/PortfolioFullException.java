package com.myorg.javacourse.exception;
import org.algo.exception.PortfolioException;
@SuppressWarnings("serial")
public class PortfolioFullException extends PortfolioException {
	
	public PortfolioFullException(String string){
		super(string);
	}

	public PortfolioFullException() {
		super();
	}
	
}
