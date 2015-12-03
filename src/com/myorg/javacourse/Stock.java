package com.myorg.javacourse;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Stock {

	
	private String  symbol;
	private float ask;
	private float bid;
	private int recommendation;
	private int stockQuantity;
	private final static int BUY =0, SELL =1,REMOVE =2,HOLD =3;

	SimpleDateFormat dateFormat=new SimpleDateFormat ("MM/dd/yyyy");
	
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
	
	  public String getHtmlDescription()
	    {
	    	String result1 = "<b>Stock symbol</b>: " + getSymbol() + " <b>Bid</b>: " + getBid() + " <b> ask </b>: " + getAsk()  
	    			+ " <b>Date</b>: "+ dateFormat.format(getdate())+ "<br>";
			return result1;
	    }

}