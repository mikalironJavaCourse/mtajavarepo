package com.myorg.javacourse.model;

import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;
import com.myorg.javacourse.exception.BalanceException;
import com.myorg.javacourse.exception.PortfolioFullException;
import com.myorg.javacourse.exception.StockAlreadyExistsException;
import com.myorg.javacourse.exception.StockNotExistException;

/**
 * an instance portfolio arrays of stocks
 * @author LironMikaMor
 *
 */
public class Portfolio implements PortfolioInterface {
	
	final static int MAX_PORTFOLIO_SIZE =5;	
	public enum ALGO_RECOMMENDATION{ BUY , SELL , REMOVE , HOLD };//
	private int portfolioSize=0;
	private StockInterface [] stocks;
	private float balance;
	String title;		
	
	/**
	 * constractor of portfolio
	 */
	public Portfolio(){
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
	}
	
	/**
	 * copy constractor overload
	 * @param stock
	 */
	public Portfolio (StockInterface [] stock){
		this();
		for (int i=0; i< stock.length; i++){
			stocks[i] = stock[i];
		}
		portfolioSize=stock.length;
		
	}

	public Portfolio(int portfolioSize, Stock[] stocks, float balance,
			String title) {
		super();
		this.portfolioSize = portfolioSize;
		this.stocks = stocks;
		this.balance = balance;
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
	 * @throws StockNotExistException 
	 * @throws StockAlreadyExistsException 
	 * @throws PortfolioFullException 
	  */
	 
	 public  Portfolio(Portfolio portfolio) throws PortfolioFullException, StockAlreadyExistsException, StockNotExistException
	 {
		 this(portfolio.getTitle());
		 
		 for (int i=0 ; i < portfolio.portfolioSize ; i++)
		 {
			 this.addStock( new Stock((Stock) portfolio.getStocks()[i]));
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

	public StockInterface[] getStocks()
	{
		return (StockInterface[]) stocks;
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
	
	public  void setBalance (float balance) {
		
			this.balance=balance;
	}
	 
	 /**
	 * method add stock to array of stocks
	 * @param stock
	 */
	public void addStock(Stock stock) throws PortfolioFullException,StockAlreadyExistsException,StockNotExistException
	{	
		if(stock.getAsk()==0)
		{
			throw new StockNotExistException();
		}
		if( portfolioSize >= MAX_PORTFOLIO_SIZE )
		{
			throw new PortfolioFullException ();
		}
		
		else if(portfolioSize != 0)
		{
			for (int i=0 ; i <  portfolioSize ; i++)
			{
				if ( stocks[i].getSymbol().equals(stock.getSymbol()))
				{
					throw new StockAlreadyExistsException ();
					
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
			 answer += ((Stock) stocks[i]).getHtmlDescription()+ "<br>";
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
	
	public void updateBalance (float amount) throws BalanceException
	{
		if( (amount+this.balance) < 0)
		{
			throw new BalanceException(" Error amount can't be negetive ");
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
	public void removeStock(String symbol) throws StockNotExistException, Exception

	{
		int sellItAll = -1;
		
		for (int i=0 ; i < portfolioSize ; i++)
		{
			if(stocks[i].getSymbol().equals(symbol))
			{
				 sellStock(stocks[i].getSymbol(), sellItAll);
					
					for(int j=i ; j < portfolioSize ;j++)
					{
						stocks[j] = stocks[j+1];
					}
					portfolioSize--;
					return;
			}
		}
		throw new StockNotExistException();
	}
	
	/**
	 * sell stock from portfolio 
	 * @param symbol
	 * @param quantity
	 * @return
	 */
	
	public void sellStock(String symbol ,int quantity) throws Exception
	{
		int sellItAll = -1;
		for (int i=0 ; i<portfolioSize ; i++)
		{
			if(stocks[i].getSymbol().equals(symbol))
			{
				if(quantity == sellItAll)
				{
					updateBalance (((Stock) stocks[i]).getStockQuantity() * stocks[i].getBid());
					((Stock) stocks[i]).setStockQuantity(0);
				}
				
				else if( quantity < sellItAll )
				{
					throw new Exception("Sorry there isnt an option");
				}
				
				else if (((Stock) stocks[i]).getStockQuantity() < quantity )
				{
					throw new Exception("Not enough stocks to sell");
				}
				
				else
				{
					updateBalance (quantity * stocks[i].getBid());
					((Stock) stocks[i]).setStockQuantity(((Stock) stocks[i]).getStockQuantity() - quantity);
				}
			}
		}
	}

	/**
	 * Method that buy stocks
	 *
	 * @return boolean
	 * @throws StockNotExistException 
	 * @throws StockAlreadyExistsException 
	 */
	
	public void buyStock( Stock stock,int quantity) throws BalanceException,PortfolioFullException, StockAlreadyExistsException, StockNotExistException
	{
		float sumOfQuantity;
		
		for(int i=0 ; i < portfolioSize ; i++)
		{
			if(stocks[i].getSymbol().equals(stock.getSymbol()))
			{
				if( balance < stocks[i].getAsk()*quantity)
				{
					throw new BalanceException();
				}
		
				else if ( quantity == -1 )
				{
					sumOfQuantity = balance/(stocks[i].getAsk());
					updateBalance(-(sumOfQuantity * stocks[i].getAsk()));
					((Stock) stocks[i]).setStockQuantity((int) (((Stock) stocks[i]).getStockQuantity() + sumOfQuantity));
					return;
				}
			
				else 
				{
					updateBalance(-( stocks[i].getAsk() * quantity));
					((Stock) stocks[i]).setStockQuantity(((Stock) stocks[i]).getStockQuantity() + quantity);
					return;
				}
			}
		}
		
		if(MAX_PORTFOLIO_SIZE > portfolioSize)
		{
			addStock(stock);
			updateBalance(-(stocks[portfolioSize].getAsk() * quantity));						
			((Stock) stocks[portfolioSize]).setStockQuantity(quantity);
			return;			
		}		
		throw new PortfolioFullException();	
	}
	
	/**
	 * return Stocks Value 
	 */
	
	public float getStocksValue ()
	{
		float  stocksValue = 0;
		
		for(int i=0 ; i< portfolioSize ; i++)
		{
			stocksValue += stocks[i].getBid() * ((Stock) stocks[i]).getStockQuantity();
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
	
	/**
	 * method that find stock 
	 * @param symbol
	 * @return
	 */
	public  StockInterface findStock (String symbol)
	{
		for(int i=0; i < portfolioSize; i++)
		{
			if(stocks[i].getSymbol().equals(symbol))
			{
				return stocks[i];
			}
		}
		return null;
	}
}