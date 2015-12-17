package com.myorg.javacourse.model;

import com.google.appengine.api.socket.SocketServicePb.GetSocketOptionsRequest;

/**
 * an instance portfolio arrays of stocks
 * @author LironMikaMor
 *
 */
public class Portfolio {
	final static int MAX_PORTFOLIO_SIZE =5;	
	enum ALGO_RECOMMENDATION{ BUY , SELL , REMOVE , HOLD };
	private int portfolioSize=0;
	private Stock [] stocks;
	private float balance;
	String title;		
	
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
	 
	 public String getTitle() 
	 {
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	public Stock[] getStocks()
	{
		return stocks;
	}
	
	public void setStocks(Stock[] stocks) 
	{
		this.stocks = stocks;
	}
	
	public void setPortfolioSize(int portfolioSize) 
	{
		this.portfolioSize = portfolioSize;
	}

	public int getPortfolioSize() {
		return portfolioSize;
	}

	public static int getMaxPortfolioSize() 
	{
		return MAX_PORTFOLIO_SIZE;
	}
	
	public float getBalance ()
	{
		return this.balance;
	}
	
	public void setBalance (float balance)
	{
		 this.balance=balance;
	}
	 
	 /**
	 * method add stock to array of stocks
	 * @param stock
	 */
	public void addStock(Stock stock)
	{	
		if( portfolioSize > MAX_PORTFOLIO_SIZE )
		{
			System.out.println("Can’t add new stock, portfolio can have only" + MAX_PORTFOLIO_SIZE + "stocks");
		}
		
		else if(portfolioSize != 0)
		{
			for (int i=0 ; i <  portfolioSize ; i++)
			{
				if ( stocks[i].getSymbol().equals(stock.getSymbol()))
				{
					System.out.println("This stock is already exist");
					return;
				}
			}	
			stock.setStockQuantity(0);
			stocks[portfolioSize] = stock;
			portfolioSize++;
		}
		else // portfolioSize =0
		{
			stock.setStockQuantity(0);
			stocks[portfolioSize] = stock;
			portfolioSize++;
		}
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
		 	answer +=  "Total Portfolio Value: " + getTotalValue() + " $, " + " Total Stocks Value: "+
		    getStocksValue() + " $, " + " Balance: " + getBalance() +" $";
		 
		 return answer;
	}
    /**
     * remove the first index from stock 
     */
	public void removeIndexZero(Portfolio portfolio) 
	{
		for (int i=0;i < portfolio.portfolioSize ; i++)
		{
			portfolio.stocks[i]=portfolio.stocks[i+1];
		}
		portfolio.portfolioSize--;
	}
	/**
	 * update my balance
	 * @param symbol
	 * @return
	 */
	
	public void updateBalance (float amount)
	{
		if( (amount+this.balance) < 0)
		{
			System.out.println(" Error amount can't be negetive ");
		}
		else
		{
			this.balance = this.balance + amount;
		}
	}
	
	/**
	 * 
	 *Method that remove stock 
	 * @return boolean
	 */
	public boolean removeStock (String symbol)
	{
		boolean  success = true;
		boolean  fail = false;
		boolean  ifSold=false;
		int sellItAll = -1;
		
		for (int i=0 ; i < portfolioSize ; i++)
		{
			if(stocks[i].getSymbol().equals(symbol))
			{
				ifSold = sellStock(stocks[i].getSymbol(), sellItAll);
				
				if(ifSold == true)
				{		
					for(int j=i ; j < portfolioSize ;j++)
					{
						stocks[j] = stocks[j+1];
					}
					portfolioSize--;
					return success; //true
				}
				else
				{
					return fail; //false
				}
			}
		}
		return fail; //There isnt any symbol
	}
	
	/**
	 * sell stock from portfolio 
	 * @param symbol
	 * @param quantity
	 * @return
	 */
	
	public boolean sellStock(String symbol ,int quantity)
	{
		boolean isItWorked=false;
		int sellItAll = -1;
		for (int i=0 ; i<portfolioSize ; i++)
		{
			if(stocks[i].getSymbol().equals(symbol))
			{
				if(quantity == sellItAll)
				{
					updateBalance (stocks[i].getStockQuantity() * stocks[i].getBid());
					stocks[i].setStockQuantity(0);
					 isItWorked=true ;
				}
				
				else if( quantity < sellItAll )
				{
					System.out.println("Sorry there isnt an option");
					isItWorked = false;				
				}
				
				else if (stocks[i].getStockQuantity() < quantity )
				{
					System.out.println("Not enough stocks to sell");
					isItWorked =  false;	
				}
				
				else
				{
					updateBalance (quantity * stocks[i].getBid());
					stocks[i].setStockQuantity(stocks[i].getStockQuantity() - quantity);
					isItWorked = true;
				}
			}
		}
		return isItWorked;
	}

	/**
	 * Method that buy stocks
	 *
	 * @return boolean
	 */
	
	public boolean buyStock( Stock stock,int quantity)
	{
		float sumOfQuantity;
		
		for(int i=0 ; i < portfolioSize ; i++)
		{
			if(stocks[i].getSymbol().equals(stock.getSymbol()))
			{
				if( balance < stocks[i].getAsk()*quantity)
				{
					System.out.println("Not enough money to complete the purchase");
					return false;
				}
		
				else if ( quantity == -1 )
				{
					sumOfQuantity = balance/(stocks[i].getAsk());
					updateBalance(-(sumOfQuantity * stocks[i].getAsk()));
					stocks[i].setStockQuantity((int) (stocks[i].getStockQuantity() + sumOfQuantity));
					return true;
				}
			
				else 
				{
					updateBalance(-( stocks[i].getAsk() * quantity));
					stocks[i].setStockQuantity(stocks[i].getStockQuantity() + quantity);
					return  true;
				}
			}
		}
		
		if(MAX_PORTFOLIO_SIZE > portfolioSize)
		{
			addStock(stock);
			updateBalance(-(stocks[portfolioSize].getAsk() * quantity));						
			stocks[portfolioSize].setStockQuantity(quantity);
			return true;			
		}		
		return false;			
	}
	
	/**
	 * return Stocks Value 
	 */
	
	public float getStocksValue ()
	{
		float  stocksValue = 0;
		
		for(int i=0 ; i< portfolioSize ; i++)
		{
			stocksValue += stocks[i].getBid() * stocks[i].getStockQuantity();
		}
		return stocksValue;
	}
	
	/**
	 * return Total Value 
	 */
	
	public float getTotalValue ()
	{
		return (getBalance() + getStocksValue());
	}
}