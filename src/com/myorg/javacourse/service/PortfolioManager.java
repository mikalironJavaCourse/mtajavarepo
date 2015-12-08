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
	 * @return
	 */
	
	public Portfolio getPortfolio()
	{	
		String title =new String("Liron Mika and Mor Portfolio");
		Portfolio portfolio = new Portfolio(title);
		
		Date date=(new Date("11/15/2014"));
		
		Stock stock1 = new Stock(13.1f,12.4f,"PIH",date);
		portfolio.addStock(stock1);
		
		Stock stock2 = new Stock(5.78f,5.5f,"AAL",date);
		portfolio.addStock(stock2);
		
		Stock stock3 = new Stock(32.2f,31.5f,"CAAS",date);
		portfolio.addStock(stock3);
		
		return portfolio;
	}
	
}
