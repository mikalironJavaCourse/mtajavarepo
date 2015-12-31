package com.myorg.javacourse.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.algo.model.StockInterface;

import com.myorg.javacourse.model.Portfolio.ALGO_RECOMMENDATION;

/**
 * an instance of stock that include variables
 * @author LironMilkaMor
 *
 */
public class Stock implements StockInterface {
	
	Portfolio.ALGO_RECOMMENDATION recommendation;	
	private String  symbol;
	private float ask;
	private float bid;
	private int stockQuantity;
	
	
	public Stock(){
	}

	
	/**
	 * constructor of Stock
	 * @param ask
	 * @param bid
	 * @param symbol
	 * @param date
	 */
	
	public Stock(float ask,float bid,String symbol,Date date)
	{
		setAsk(ask);
		setBid(bid);
		setSymbol(symbol);
		setdate(date);
	}
	
	/**
	 * copy constructor of Stock
	 * @param stock
	 */
	
	public Stock(Stock stock)
	{
		this(stock.getAsk() ,stock.getBid(),stock.getSymbol(),stock.getdate());
		this.recommendation= stock.getRecommendation();
		this.stockQuantity= stock.getStockQuantity();
	}
	

	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(Portfolio.ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity)
	{
		this.stockQuantity = stockQuantity;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public float getAsk() {
		return ask;
	}
	
	public void setAsk(float ask) {
		this.ask = ask;
	}
	
	public float getBid() {
		return bid;
	}
	
	public void setBid(float bid) {
		this.bid = bid;
	}
  
	public Date getdate() {
		return date;
	}
	
	public void setdate(Date date) {
		this.date=date;
	}
	
	private Date date= new Date();
		
	/**
	 * method that return information about stock
	 * @return
	 */
	
	  public String getHtmlDescription()
	    {
		  SimpleDateFormat dateFormat=new SimpleDateFormat ("MM/dd/yyyy");
		  String result1 = "<b>Stock symbol</b>: " + getSymbol() + " <b>Bid</b>: " + getBid() + " <b> ask </b>: " + getAsk()  
	    			+ " <b>Date</b>: "+ dateFormat.format(getdate())+ "<b>" + " <b>Quantity</b>: "+ getStockQuantity();
			return result1;
	    }  
}