package com.myorg.javacourse.service;

import java.util.Date;


import com.myorg.javacourse.model.Portfolio;
import com.myorg.javacourse.model.Stock;

/**
 * class of portfolioManager that set variebels in stock 
 * and concatenate into portfolio
 * 
 * 
 * @author LironMikaMor
 *
 */

public class PortfolioManager {

	@SuppressWarnings("deprecation")

	/**
	 * method of Portfolio that set variables in stock and add to portfolio 
	 */
	
	public Portfolio getPortfolio()
	{
		Portfolio myPortfolio = new Portfolio("Exercise 7 portfolio");	
		myPortfolio.setBalance(10000);
			
		String title =new String("Liron Mika and Mor Portfolio");
		Portfolio portfolio = new Portfolio(title);
		
		Date date=(new Date("11/15/2014"));
		Date date2=(new Date("12/15/2014"));
		
		Stock stock1 = new Stock(13.1f,12.4f,"PIH",date);
		portfolio.addStock(stock1);
		
		Stock stock2 = new Stock(5.78f,5.5f,"AAL",date);
		portfolio.addStock(stock2);
		
		Stock stock3 = new Stock(32.2f,31.5f,"CAAS",date);
		portfolio.addStock(stock3);
		
		Stock stock4 = new Stock(10.0f,8.5f,"PIH",date2);
		myPortfolio.addStock(stock4);
		
		Stock stock5 = new Stock(30.0f,25.5f,"AAL",date2);
		myPortfolio.addStock(stock5);
		
		Stock stock6 = new Stock(20.0f,15.5f,"CAAS",date2);
		myPortfolio.addStock(stock6);
			
		myPortfolio.buyStock(stock4,20);
		myPortfolio.buyStock(stock5,30);
		myPortfolio.buyStock(stock6,40);
		
		myPortfolio.sellStock("AAL",-1);
					
		myPortfolio.removeStock("CAAS");
								
		return myPortfolio;
	}
}
