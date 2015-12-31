package com.myorg.javacourse.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.algo.service.ServiceManager;

import com.myorg.javacourse.service.PortfolioManager;

@SuppressWarnings("serial")
public class InitServlet extends HttpServlet{

	@Override
	public void init() throws ServletException {
		super.init();
		
		PortfolioManager pm = new PortfolioManager();
		ServiceManager.setPortfolioManager(pm);
				
	}
}
