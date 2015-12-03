package com.myorg.javacourse.model;

import com.myorg.javacourse.Stock;

public class Portfolio {
	final static int MAX_PORTFOLIO_SIZE =5;
	private int portfolioSize=0;
	private Stock [] stocks;
	String title;
	 
	 public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	 
	 public Portfolio()
	 {
		 stocks=new Stock[MAX_PORTFOLIO_SIZE];
	 }
	 
	public void addStock(Stock stock)
	{
		if(portfolioSize < MAX_PORTFOLIO_SIZE){
			stocks[portfolioSize] = stock;
			portfolioSize++;
		}
		else
			return;
	}
	
	public Stock[] getStocks ()
	{
		return stocks;
	}
	 
	public String getHtmlString()
	{	
		 String answer = new String ("<h1>" + getTitle() + "</h1>");
		
		 for (int i=0 ; i < portfolioSize ; i++)
		 {
			 answer += stocks[i].getHtmlDescription()+ "<br>";
		 }
		 return answer;
	}
}
