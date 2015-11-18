package com.myorg.javacourse;
import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class MtaProServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		MathCalc mCalc = new MathCalc(50, 30, 50, 20, 13);
		
		resp.getWriter().println(mCalc.getResult());
	}
}

