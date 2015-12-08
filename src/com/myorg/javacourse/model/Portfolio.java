package com.myorg.javacourse.model;

/**
 * an instance portfolio arrays of stocks
 * @author LironMikaMor
 *
 */
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
	
	/**
	 * constructor of portfolio
	 * @param title
	 */
	 public  Portfolio(String title)
	 {
		 stocks = new Stock[MAX_PORTFOLIO_SIZE];
		 setTitle(title);
	 }
	 
	 /**
	  * copy constructor  of portfolio
	  * @param portfolio
	  */
	 
	 public  Portfolio(Portfolio portfolio)
	 {

		 this(portfolio.getTitle());
		 
		 for (int i=0 ; i < portfolio.portfolioSize ; i++)
		 {
			 this.addStock( new Stock(portfolio.getStocks()[i]));
			 
		 }
	 }
	 /**
	  * method add stock to array of stocks
	  * @param stock
	  */
	public void addStock(Stock stock)
	{
		if(portfolioSize < MAX_PORTFOLIO_SIZE){
			stocks[portfolioSize] = stock;
			portfolioSize++;
		}
		else
			return;
	}
	
	public Stock[] getStocks()
	{
		return stocks;
	}
	 
	/**
	 * method that return string of stocks 
	 * @return
	 */
	public String getHtmlString()
	{	
		 String answer = new String ("<h1>" + getTitle() + "</h1>");
		
		 for (int i=0 ; i < portfolioSize ; i++)
		 {
			 answer += stocks[i].getHtmlDescription()+ "<br>";
		 }
		 return answer;
	}
/**
 * remove the first index from stock 
 */
	public void removeIndexZero(Portfolio portfolio) {
		for (int i=0;i < portfolio.portfolioSize ; i++)
		{
			portfolio.stocks[i]=portfolio.stocks[i+1];
		}
		portfolio.portfolioSize--;
	}
	/**
	 * return the size of portfolio
	 * @return
	 */
	public  int getPortfolioSize(Portfolio portfolio) {
		return portfolio.portfolioSize;
	}
}