package com.myorg.javacourse;

import java.util.Date;
@SuppressWarnings("deprecation")

public class Stock {
	
   private int day;
   private int month;
   private int year; 

	private String  symbol;
	private float ask;
	private float bid;
	
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
	public void setdate() {
		this.day = date.getDate();
		this.month=date.getMonth();
		this.year=date.getYear();
}
	private Date date= new Date();
	
	  public String getHtmlDescription()
	    {
	    	String result1 = "<b>Stock symbol</b>: " + getSymbol() + " <b>Bid</b>: " + getBid() + " <b> ask </b>: " + getAsk()  
	    			+ " <b>Date</b>: "+ day + "/" + (month+1) + "/" + (year+1900); 
			return result1;
	    }
	    
	
}