package com.myorg.javacourse.servlet;

import java.io.IOException;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myorg.javacourse.model.Stock;
import com.myorg.javacourse.model.Portfolio;
import com.myorg.javacourse.service.PortfolioManager;

/**
 * an instance of PortfolioServlet that print to screen 
* @author LironMikaMor
 *
 */
public class PortfolioServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		resp.setContentType("text/html");
		

		
		PortfolioManager portfolioManager = new PortfolioManager();//
		Portfolio myPortfolio = portfolioManager.getPortfolio();//
		
		resp.getWriter().println("<h3><b>Before any change </b></h3>");
		resp.getWriter().println(myPortfolio.getHtmlString());
		
		myPortfolio.sellStock("AAL",-1);
		
		resp.getWriter().println("<h3><b>After selling AAL stocks </b></h3> ");
		resp.getWriter().println(myPortfolio.getHtmlString());
			
		myPortfolio.removeStock("CAAS");
		
		resp.getWriter().println("<h3><b>After removing CAAS stocks </b> </h3>");
		resp.getWriter().println(myPortfolio.getHtmlString());
		
	}
}
